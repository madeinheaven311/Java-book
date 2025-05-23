package com.d4c.www.config;

import com.d4c.www.entity.Duck;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DuckConfig {

    @Bean
    public Duck duck(){
        Duck duck = new Duck();
        duck.setName("唐老鸭");
        duck.setAge(6);
        return duck;
    }

    @Bean
    public Duck duck2(){
        Duck duck = new Duck();
        duck.setName("丑小鸭");
        duck.setAge(9);
        return duck;
    }
}
