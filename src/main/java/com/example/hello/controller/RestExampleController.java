package com.example.hello.controller;

import com.example.hello.config.MomentoConfig;
import com.example.hello.config.TupuConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class RestExampleController {
    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private TupuConfig tupuConfig;

    @Autowired
    private MomentoConfig momentoConfig;

    @GetMapping("/getTupuConfig")
    public TupuConfig getTupuConfig() {
        return tupuConfig;
    }

    @GetMapping("/getMomentoConfig")
    public MomentoConfig getMomentoConfig() {
        return momentoConfig;
    }

    @GetMapping("/get-restTemplate")
    public String getRestTemplate() {
//        restTemplate.getForObject("http://localhost:8080/v1/user/10", String.class);
//        LinkedMultiValueMap<String, String> request = new LinkedMultiValueMap<>();
//        request.set("name", "RestTemplate");
//        request.set("tel", "8767876789");
//        request.set("email", "restTemplate@hello.world");
//        restTemplate.postForObject("http://localhost:8080/v1/user", request, Map.class);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8081/v1/user/11", String.class);
        System.out.println("获取响应的状态:" + responseEntity.getStatusCode());
        System.out.println("获取响应的数据:" + responseEntity.getBody());
        return null;
    }
}
