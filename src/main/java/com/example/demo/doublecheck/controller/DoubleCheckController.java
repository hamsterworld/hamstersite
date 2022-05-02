package com.example.demo.doublecheck.controller;

import com.example.demo.doublecheck.service.DoubleCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DoubleCheckController {

    private final DoubleCheckService doubleCheckService;

    @PostMapping("/ajax/UserIdDoubleCheck")
    public String DoubleCheck(@RequestParam String UserId, Model model){

        boolean check = doubleCheckService.check(UserId);

        model.addAttribute("check",check);

        return "/";

    }



}
