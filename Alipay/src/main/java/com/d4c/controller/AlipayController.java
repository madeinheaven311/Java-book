package com.d4c.controller;


import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.d4c.config.AlipayConfig;
import com.d4c.entity.AjaxResult;
import com.d4c.entity.AliPayFaceToFaceModel;
import com.d4c.entity.AliPayResultModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController()
@Slf4j
@RequestMapping("alipay")
public class AlipayController {

    @Autowired
    private AlipayConfig aliPayConfig;

    private AlipayClient getAlipayClient() {
        return new DefaultAlipayClient(
                aliPayConfig.getOpenApiDomain(),
                aliPayConfig.getAppId(),
                aliPayConfig.getPrivateKey(),
                AlipayConstants.FORMAT_JSON,
                AlipayConstants.CHARSET_UTF8,
                aliPayConfig.getAlipayPublicKey(),
                aliPayConfig.getSignType()
        );
    }

    @PostMapping("/getTrade")
    private AlipayTradePrecreateRequest getTradePreCreateRequest(AliPayFaceToFaceModel aliPayFaceToFaceModel) {
        //实例化具体API对应的request类，类名称和接口名称对应,当前调用接口名称：alipay.trade.precreate（统一收单线下交易预创建（扫码支付）
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();//设置业务参数
        model.setOutTradeNo(aliPayFaceToFaceModel.getOutTradeNo());//商户订单号，商户自定义，需保证在商户端不重复，如：20200612000001
        model.setSubject(aliPayFaceToFaceModel.getSubject());//订单标题
        model.setTotalAmount(aliPayFaceToFaceModel.getTotalAmount());//订单金额，精确到小数点后两位
        model.setBody(aliPayFaceToFaceModel.getBody());//订单描述

        request.setBizModel(model);
        /*
         异步通知地址，以http或者https开头的，商户外网可以post访问的异步地址，用于接收支付宝返回的支付结果，如果未收到该通知可参考该文档进行确认：https://opensupport.alipay.com/support/helpcenter/193/201602475759
         */
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());

        return request;
    }

    /**
     * 支付宝预下单
     */
    @PostMapping("/order")
    public AjaxResult aliPayPreorder(@RequestBody AliPayFaceToFaceModel aliPayFaceToFaceModel) {
        log.info("支付宝预下单，商户订单号：{}",aliPayFaceToFaceModel.getOutTradeNo());
        try {
            AlipayClient alipayClient = getAlipayClient();
            AlipayTradePrecreateRequest tradePreCreateRequest = getTradePreCreateRequest(aliPayFaceToFaceModel);
            AlipayTradePrecreateResponse response = alipayClient.execute(tradePreCreateRequest);
            log.info("支付宝预下单接口调用成功，返回参数：{}",response.getBody());
            // System.out.println(response.getBody());
            if(StringUtils.equalsAny(response.getCode(),"10000")){
                AliPayResultModel resultModel=new AliPayResultModel();
                resultModel.setOutTradeNo(response.getOutTradeNo());
                resultModel.setQrCode(response.getQrCode());
                return AjaxResult.success(resultModel);
            }else{
                return AjaxResult.error("获取支付二维码失败，错误信息："+response.getSubMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("下单失败");
        }
    }

    /**
     * 交易状态查询
     * 可以查看以下帮助文档:
     * 判断交易是否成功：https://opensupport.alipay.com/support/helpcenter/195/201602516393?ant_source=zsearch
     * 状态ACQ.TRADE_NOT_EXIST（交易不存在）https://opensupport.alipay.com/support/helpcenter/89/201602475600?ant_source=zsearch
     */
    @PostMapping("/query")
    public AjaxResult queryTrade(AliPayFaceToFaceModel aliPayFaceToFaceModel) {
        try {
            log.info("调用支付宝交易状态查询接口，单号：{}",aliPayFaceToFaceModel.getOutTradeNo());
            AlipayClient alipayClient = getAlipayClient();
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//实例化具体API对应的request类，类名称和接口名称对应,当前调用接口名称：alipay.trade.query（统一收单线下交易查询）
            AlipayTradeQueryModel model = new AlipayTradeQueryModel();
            // 注：交易号（TradeNo）与订单号（OutTradeNo）二选一传入即可，如果2个同时传入，则以交易号为准
            //支付接口传入的商户订单号。如：2020061601290011200000140004 **/
            model.setOutTradeNo(aliPayFaceToFaceModel.getOutTradeNo());
            // 异步通知/查询接口返回的支付宝交易号，如：2020061622001473951448314322 **/
            request.setBizModel(model);
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            log.info("调用支付宝交易状态查询接口成功，返回参数：{}",response.getBody());
            if(response.isSuccess()){
                AliPayResultModel resultModel=new AliPayResultModel();
                resultModel.setOutTradeNo(response.getOutTradeNo());
                resultModel.setTradeStatus(response.getTradeStatus());
                resultModel.setTradeNo(response.getTradeNo());
                return AjaxResult.success(resultModel);
            }else{
                log.info("调用支付宝交易状态查询接口失败，失败信息：{}",response.getSubMsg());
                return AjaxResult.error("支付宝订单查询失败："+response.getSubMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("订单查询异常！");
        }
    }


    /**
     * 交易退款接口
     *
     * @param aliPayFaceToFaceModel 参数实体
     * @author: zm
     * @date: 2024/10/8 11:05
     * @rerturn: java.lang.String
     */
    @PostMapping("/refund")
    public AjaxResult payTradeRefund(AliPayFaceToFaceModel aliPayFaceToFaceModel) {
        if(aliPayFaceToFaceModel == null || StringUtils.isEmpty(aliPayFaceToFaceModel.getOutTradeNo())){
            log.error("订单号为空，请检查参数");
            return AjaxResult.error("订单号为空");
        }
        try {
            log.info("调用支付宝退款接口，单号：{}",aliPayFaceToFaceModel.getOutTradeNo());
            AlipayClient alipayClient = getAlipayClient();
            AlipayTradeRefundRequest refundRequest=new AlipayTradeRefundRequest();
            AlipayTradeRefundModel refundModel =new AlipayTradeRefundModel();
            //商户订单号
            refundModel.setOutTradeNo(aliPayFaceToFaceModel.getOutTradeNo());
            //退款金额
            refundModel.setRefundAmount(aliPayFaceToFaceModel.getTotalAmount());
            //退款描述
            refundModel.setRefundReason("正常退款");
            refundRequest.setBizModel(refundModel);
            AlipayTradeRefundResponse response=alipayClient.execute(refundRequest);
            log.info("支付宝交易退款接口调用成功，返回参数：{}",response.getBody());
            if (!response.isSuccess())  {
                log.error("支付宝交易退款接口调用失败，单号：{}",aliPayFaceToFaceModel.getOutTradeNo());
                return AjaxResult.error("退款失败，错误信息："+response.getSubMsg());
            }
            AliPayResultModel resultModel=new AliPayResultModel();
            resultModel.setOutTradeNo(response.getOutTradeNo());
            resultModel.setRefundFee(response.getRefundFee());
            resultModel.setTradeNo(response.getTradeNo());
            resultModel.setFundChange(response.getFundChange());
            return AjaxResult.success(resultModel);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("退款异常！");
        }
    }

    /**
     * 支付宝支付回调
     * @param request 请求
     * @param response 响应
     * @author: zm
     * @date: 2024/10/10
     * @rerturn: void
     */

    @PostMapping("/ZFBcallbackAction.do")
    public void notifyUrl(HttpServletRequest request, HttpServletResponse response){
        try {
            log.info("接收到支付回调信息");
            //获取支付宝公钥
            String aliPayPublicKey = aliPayConfig.getAlipayPublicKey();
            PrintWriter out;
            out = response.getWriter();
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();
            //循环遍历支付宝请求过来的参数存入到params中
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            //异步验签：切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
            boolean flag = AlipaySignature.rsaCheckV1(params, aliPayPublicKey, "utf-8","RSA2");
            if (flag){
                log.info("验签成功，返回参数：{}",params.toString());
                AliPayResultModel resultModel=new AliPayResultModel();
                resultModel.setOutTradeNo(params.get("out_trade_no"));
                resultModel.setTradeStatus(params.get("trade_status"));
                resultModel.setTradeNo(params.get("trade_no"));
                resultModel.setBody(params.get("body"));
                //TODO 调用业务接口

                out.write("success");

            }else {
                log.info("支付宝验签失败，请联系工作人员");
                //验签失败该接口被别人调用
                out.write("支付宝异步回调验签失败，请留意");
            }
            out.flush();
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
