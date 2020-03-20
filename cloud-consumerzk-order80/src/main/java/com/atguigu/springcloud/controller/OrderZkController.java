package com.atguigu.springcloud.controller;

import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderZkController {

    public static final String INVOKE_URL="http://cloud-provider-payment";

    @Reference
    private RestTemplate restTemplate;

    /**
     * http://localhost/consumer/payment/zk
     *
     * @return
     */
    @RequestMapping("/consumer/payment/zk")
    public String paymentInfo(){
        return restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
    }
}
