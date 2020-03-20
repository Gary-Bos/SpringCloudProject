package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String SERVER_PORT; // 端口号

    /**PostMan:http://localhost:8001/payment/create?serial=尚硅谷002
     * 数据插入
     * @param payment
     * @return
     */
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int number = paymentService.create(payment);
        log.info("**********插入结果"+number);
        if (number > 0){
            return new CommonResult(200,"数据插入成功,SERVER_PORT："+SERVER_PORT,number);
        }else{
            return new CommonResult(500,"数据插入失败",null);
        }
    }

    /**
     * URL：http://localhost:8001/payment/get/34
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null){
            return new CommonResult(200,"查询成功,SERVER_PORT："+SERVER_PORT,payment);
        }else{
            return new CommonResult(500,"查询失败",null);
        }
    }
}
