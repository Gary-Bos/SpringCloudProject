package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    /**
     * applicationContext.xml <bean id="" class=""> </bean>
     * LoadBalanced 负载均衡
     * @return
     */
    @Bean
    // 使用自定义负载均衡规则 @LoadBalanced
    // @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new  RestTemplate();
    }
}
