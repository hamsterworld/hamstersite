package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequestMapping
public class test {

    @GetMapping("/test")
    public String test(HttpServletRequest request){

        log.info("URL = {} " , request.getRequestURI());
        return "test";

    }

}
