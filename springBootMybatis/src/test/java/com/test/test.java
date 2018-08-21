package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.service.EmpService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
	
    @Autowired
    private EmpService empService;
	
    @Test
    public void testSayHello() {
        System.out.println(empService.getAll());
    }
}
