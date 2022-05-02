package com.example.demo.login.service;

import com.example.demo.dao.Dao;
import com.example.demo.dao.DaoImpl;
import com.example.demo.dto.LoginUserForm;
import com.example.demo.dto.User;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import javax.sql.DataSource;

import static com.example.demo.ConnectionInfo.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
//import static org.assertj.core.api.Assertions.assertThat;


class LoginServiceTest {

    LoginService loginService;

    @BeforeEach
    void beforeEach(){

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        Dao dao = new DaoImpl(dataSource);

        loginService = new LoginService(dao);

    }

    @Test
    @DisplayName("정상 로그인")
    void login() {

        LoginUserForm loginUserForm = new LoginUserForm();
        loginUserForm.setUserId("ewfe");
        loginUserForm.setUserPassWord("4863527");

        User login = loginService.login(loginUserForm);

        assertThat(loginUserForm.getUserId()).isEqualTo(login.getUserId());

    }

    @Test
    @DisplayName("회원이 없을때")
    void NotFoundLogin() {


        LoginUserForm loginUserForm = new LoginUserForm();
        loginUserForm.setUserId("ewfwef");
        loginUserForm.setUserPassWord("4863527");

        User login = loginService.login(loginUserForm);

        assertThat(login).isNull();

    }

    @Test
    @DisplayName("비밀번호가 틀렸을때")
    void NotMatchedPw() {


        LoginUserForm loginUserForm = new LoginUserForm();
        loginUserForm.setUserId("ewfe");
        loginUserForm.setUserPassWord("4863");

        User login = loginService.login(loginUserForm);

        assertThat(login).isNull();

    }


}