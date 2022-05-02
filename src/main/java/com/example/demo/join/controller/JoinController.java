package com.example.demo.join.controller;


import com.example.demo.dto.JoinUserForm;
import com.example.demo.dto.User;
import com.example.demo.join.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@Controller
@RequiredArgsConstructor
@Slf4j
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String Join(@ModelAttribute("User") JoinUserForm user){

        return "join";

    }

    @PostMapping("/join")
    public String PostJoin(@Validated  @ModelAttribute("User") JoinUserForm user,
                           BindingResult bindingResult){

        if(!user.getConfirmedUserPassWord().equals(user.getUserPassWord())){

            bindingResult.reject("NotMatchedPassWord",null,null);

        }

        if(bindingResult.hasErrors()){

            log.info("bindingResult = {} ",bindingResult);

            return "join";

        }

        log.info("user = {}",user);

        log.info("joinservice = {} ", joinService);

        joinService.save(user);

        return "redirect:/";

    }


}
