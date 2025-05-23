package com.d4c.www.springBootTest;


import com.d4c.www.SpringBootApp;
import com.d4c.www.dto.Cat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
public class TestCat {

    @Autowired
    private Cat cat = new Cat();

    @Test
    public void testCat(){
        cat.setCatAge(11);
        cat.setCatName("mimi");

        System.out.println(cat);
    }

}
