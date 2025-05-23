package com.d4c.www.util;

import lombok.SneakyThrows;
import org.junit.Test;

public class BuildUtilTest {


    @Test
    @SneakyThrows
    public void testutil(){
        BuildUtil buildUtil = new BuildUtil();
        System.out.println("随机名字是："+BuildUtil.buildNickName());
        System.out.println("随机账户id是："+BuildUtil.buildUuid());
        System.out.println("验证码是："+BuildUtil.buildVerificationCode(4));

    }
}
