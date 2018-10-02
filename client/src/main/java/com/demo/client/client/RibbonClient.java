package com.demo.client.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RibbonClient {

    @Autowired
    private RestTemplate restTemplate;

    public String getName(String name){
        ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        "http://spring-client/sample/sayHi/{name}",  // spring-client 服務名稱
                        HttpMethod.GET,
                        null,String.class,name
                );
        return restExchange.getBody();
    }
}
