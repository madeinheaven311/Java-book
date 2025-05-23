package com.d4c.www.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@Schema(name = "订单", description = "用于记录订单信息")
public class OrderDTO implements Serializable {
    @Schema(description = "序号", example = "0")
    private Long id;

    @Schema(description = "编号",  example = "icc9527")
    private String sn;

    @Schema(description = "总共金额",  example = "1000")
    private Double totalAmount;

    @Schema(description = "实际支付金额", example = "900")
    private Double payAmount;

    @Schema(description = "支付方式",  example = "0")
    private Long payType;

    @Schema(description = "订单信息",  example = "梳子")
    private String info;

    @Schema(description = "状态",  example = "0")
    private Long state;

    @Schema(description = "会员序号",  example = "1")
    private Long fkMemberId;

    @Schema(description = "优惠卷序号",  example = "1")
    private Long fkCouponsId;

    @Schema(description = "用户名",  example = "赵四")
    private String username;

    @Schema(description = "创建时间",  example = "1111")
    private Date created;

    @Schema(description = "更新时间",  example = "1111")
    private Date updated;
    
}
