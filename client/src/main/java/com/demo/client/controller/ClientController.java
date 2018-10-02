package com.demo.client.controller;

import com.demo.client.client.RibbonClient;
import com.demo.client.client.TestDiscoveryClient;
import com.demo.client.client.TestFeignClient;
import com.demo.client.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private TestDiscoveryClient testDiscoveryClient;

//    @Autowired
//    private RibbonClient ribbonClient;

    @Autowired
    private TestFeignClient testFeignClient;

    @RequestMapping(value = "/emp/{empno}",method = RequestMethod.GET)
    public Emp getEmp(@PathVariable("empno")Integer empno){
        System.out.println("empno : "+empno);
        return testDiscoveryClient.getEmp(empno);
    }

    @RequestMapping(value = "/test/{name}",method = RequestMethod.GET)
    public String getTest(@PathVariable String name){
        System.out.println("name : "+name);
        return testDiscoveryClient.getName(name);

    }

//    @RequestMapping(value = "/testRest/{name}",method = RequestMethod.GET)
//    public String getTestRestTemplate(@PathVariable String name){
//        return ribbonClient.getName(name);
//    }

    @RequestMapping(value = "/testFeign/{name}",method = RequestMethod.GET)
    public String getTestFeign(@PathVariable String name){
        System.out.println("use Feign");
        return testFeignClient.getName(name);
    }
}
