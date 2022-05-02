package com.example.demo.join.service;

import com.example.demo.dao.Dao;
import com.example.demo.dao.DaoImpl;
import com.example.demo.dto.JoinUserForm;
import com.example.demo.dto.User;
import com.example.demo.join.controller.JoinController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;


@SpringBootTest
class JoinServiceTest {

    @Autowired
    JoinController joinController;
    @Autowired
    JoinService joinService;

    @Test
    void save() {

        JoinUserForm joinUserForm = new JoinUserForm();
        joinUserForm.setUserId("hamster");
        joinUserForm.setUserPassWord("123456");
        joinUserForm.setConfirmedUserPassWord("123456");
        joinUserForm.setUserName("햄스터");
        joinUserForm.setNickName("귀여운게죄라면난사형");

        joinService.save(joinUserForm);

        User search = joinService.search(joinUserForm.getUserId());

        assertThat(joinUserForm.getNickName()).isEqualTo(search.getNickName());

    }




}