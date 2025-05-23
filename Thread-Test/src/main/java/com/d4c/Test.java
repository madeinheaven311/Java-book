package com.d4c;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    private Test() {
    }

    private volatile static Test instance;

    public static Test getInstance() {

        if (instance == null) {
            synchronized (Test.class) {
                if (instance == null) {
                    instance = new Test();
                }
            }
        }
        return instance;
    }

}
