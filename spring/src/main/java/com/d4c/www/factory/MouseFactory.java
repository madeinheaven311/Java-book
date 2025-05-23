package com.d4c.www.factory;

import com.d4c.www.entity.Mouse;

public class MouseFactory {

    public MouseFactory() {
        System.out.println("构造器: MouseFactory()");
    }

    /** 静态方法 */
    public static Mouse getInstance() {
        return new Mouse("Mickey");
    }

}
