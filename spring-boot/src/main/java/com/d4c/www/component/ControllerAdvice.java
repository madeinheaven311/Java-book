package com.d4c.www.component;

import cn.hutool.json.JSONUtil;
import com.d4c.www.entity.result.Result;
import com.d4c.www.entity.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

/**
 * @author JoeZhou
 */
@Slf4j
@RestControllerAdvice(basePackages = {"com.d4c.www.controller"})
public class ControllerAdvice implements ResponseBodyAdvice<Object> {


    @ExceptionHandler(Exception.class)
    public Object exception(Exception e) {
        log.error("全局异常: " + e.getMessage());
        return new Result(ResultCode.FAILED, e.getMessage());
    }



    /**
     * 该方法在控制方法响应数据前执行，且仅在该方法返回true时，才会进入 beforeBodyWrite 方法进行下一步操作
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        log.info("进入 supports 方法");
        return true;
    }

    /**
     * 对返回值进行统一封装后，再进行响应
     */
    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        log.info("进入 beforeBodyWrite 方法");

        // 若Jackson使用的是字符串转换器，则使用JSON封装一下再直接返回字符串，避免爆发转换异常
        if (aClass == StringHttpMessageConverter.class) {
            return JSONUtil.toJsonStr(o);
        } else {
            // 若控制方法本身已经返回了Result类型，则无需再次封装为Result
            if (o instanceof Result) {
                return o;
            }

            // 若返回值不是Result类型，则使用Result封装后返回
            return new Result(o);
        }

    }
}
