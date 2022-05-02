package com.example.demo.login.service;

import com.example.demo.dao.Dao;
import com.example.demo.dto.LoginUserForm;
import com.example.demo.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final Dao dao;

    public User login(LoginUserForm loginUserForm){

        User search = dao.search(loginUserForm.getUserId());

        if(search == null){

            return null;

        } else if(!search.getUserPassWord().equals(loginUserForm.getUserPassWord())) {

           return null;

        }

        return search;
    }
}
