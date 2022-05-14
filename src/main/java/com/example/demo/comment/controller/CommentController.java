package com.example.demo.comment.controller;


import com.example.demo.comment.service.CommentService;
import com.example.demo.dto.Comment;
import com.example.demo.dto.LoginUserForm;
import com.example.demo.dto.testdto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    //@PostMapping("/comment")
    public List<Comment> CommentSee(){

        return null;

    }

    @PostMapping("/commentdelete")
    public String CommentDelete(@RequestBody testdto testdto){

        log.info("잘왓니? = {} ",testdto);
        //delete에서2가지
        //serivice에서 삭제
        //service에서 조회후 다시 주기.


        return "ok";
    }

    @PostMapping("/commentwrite")
    public String CommentWrite(@RequestBody String userid){

        log.info("잘왓니? = {} ",userid);



        return userid;
    }



    @PostMapping("/commenttest")
    public List<LoginUserForm> Commenttest(){

        List<LoginUserForm> loginUserForms = new ArrayList<>();

        LoginUserForm loginUserForm1 = new LoginUserForm();
        LoginUserForm loginUserForm2 = new LoginUserForm();
        LoginUserForm loginUserForm3 = new LoginUserForm();
        LoginUserForm loginUserForm4 = new LoginUserForm();
        LoginUserForm loginUserForm5 = new LoginUserForm();

        loginUserForm1.setUserId("하하1");
        loginUserForm1.setUserPassWord("비밀번호지롱1");

        loginUserForm2.setUserId("하하2");
        loginUserForm2.setUserPassWord("비밀번호지롱2");

        loginUserForm3.setUserId("하하3");
        loginUserForm3.setUserPassWord("비밀번호지롱3");

        loginUserForm4.setUserId("하하4");
        loginUserForm4.setUserPassWord("비밀번호지롱4");

        loginUserForm5.setUserId("하하5");
        loginUserForm5.setUserPassWord("비밀번호지롱5");

        loginUserForms.add(loginUserForm1);
        loginUserForms.add(loginUserForm2);
        loginUserForms.add(loginUserForm3);
        loginUserForms.add(loginUserForm4);
        loginUserForms.add(loginUserForm5);

        return loginUserForms;

    }


}
