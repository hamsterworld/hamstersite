package com.example.demo.login.controller;

import com.example.demo.dto.LoginUserForm;
import com.example.demo.dto.User;
import com.example.demo.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String Login(@ModelAttribute("User") LoginUserForm loginUserForm){

        return "login";

    }


    @PostMapping("/login")
    public String PostLogin(@Validated @ModelAttribute("User") LoginUserForm loginUserForm,
                            BindingResult bindingResult,
                            HttpServletRequest request){

        if(bindingResult.hasErrors()){

            log.info("BindingResult = {} ", bindingResult);

            return "login";
        }

        User user = loginService.login(loginUserForm);

        if(user != null){

            log.info("로그인 성공된 아이디 = {} ", user);

            HttpSession session = request.getSession();

            session.setAttribute("LoginUser",user);

        } else {

            bindingResult.reject("LoginFail",null);

            return "login";

        }

        return "redirect:/";

    }

}
