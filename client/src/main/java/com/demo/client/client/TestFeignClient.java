package com.demo.client.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(serviceId = "spring-client")
@RequestMapping(value = "/sample")
public interface TestFeignClient {

    @RequestMapping(method = RequestMethod.GET,value = "/sayHi/{name}",consumes = "application/json")
    String getName(@PathVariable("name") String name); // @PathVariable 需指定參數名稱
//    参数没有用@RequestParam注解修饰，然后发送请求，会发现被调用的服务一直报Request method 'POST' not supported，
//    我们明明使用的是GET方法，为什么被调用服务认为是POST方法了，
//    原因是当userName没有被@RequestParam注解修饰时，会自动被当做request body来处理。
//    只要有body，就会被feign认为是post请求，所以整个服务是被当作带有request
//    parameter和body的post请求发送出去的。
}
