package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    /**
     * 服务发现 获取服务信息
     */
    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/peyment/discover")
    public Object getDiscover(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("************element为："+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return discoveryClient;
    }
}
