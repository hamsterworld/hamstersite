package com.example.demo.dao;

import com.example.demo.dto.User;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static com.example.demo.ConnectionInfo.*;
import static org.assertj.core.api.Assertions.assertThat;



class DaoImplTest {


    @Test
    public void save() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");

        DaoImpl repository = new DaoImpl(dataSource);

        User user = new User();
        user.setUserId("ssoboro");
        user.setUserPassWord("4863527");
        user.setUserName("오인석");
        user.setNickName("햄스터세상");

        User user1 = repository.save(user);

        System.out.println(user1);

        assertThat(user1).isNotNull();

    }


    @Test
    public void search() {



    }



}