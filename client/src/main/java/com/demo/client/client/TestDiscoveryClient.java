package com.demo.client.client;

import com.demo.client.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TestDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public Emp getEmp(Integer empNo){
        RestTemplate restTemplate = new RestTemplate();

        List<ServiceInstance> instances = discoveryClient.getInstances("spring-client");

        if(instances.size()==0)return null;

        String serviceUrl = String.format("%s/sample/Emp/%s",instances.get(0).getUri().toString(), empNo);

        ResponseEntity<Emp> restExchange =
                restTemplate.exchange(serviceUrl,HttpMethod.GET,null,Emp.class,empNo);

        return restExchange.getBody();
    }

    public String getName(String name){
        RestTemplate restTemplate = new RestTemplate();

        List<ServiceInstance> instances = discoveryClient.getInstances("spring-client");

        if(instances.size()==0)return null;

        String serviceUrl = String.format("%s/sample/sayHi/%s",instances.get(0).getUri().toString(), name);

        ResponseEntity<String> restExchange =
                restTemplate.exchange(serviceUrl,HttpMethod.GET,null,String.class,name);

        return restExchange.getBody();
    }
}
