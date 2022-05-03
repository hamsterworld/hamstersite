package com.example.demo.join.service;

import com.example.demo.dao.Dao;
import com.example.demo.dto.JoinUserForm;
import com.example.demo.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Slf4j
@RequiredArgsConstructor
public class JoinService {

    private final Dao dao;
    //private final SQLExceptionTranslator translator;

    @Transactional
    public void saveTransfer(JoinUserForm user) {


          //save(user);


        if(user.getNickName().equals("test"))
            throw new IllegalStateException("으하하 트렌젝션오류 나버렷~");

    }


    public void save(JoinUserForm user) throws SQLException{

        log.info("user = {}", user);

        User user1 = new User();
        user1.setUserId(user.getUserId());
        user1.setUserName(user.getUserName());
        user1.setUserPassWord(user.getUserPassWord());
        user1.setNickName(user.getNickName());

        log.info("user = {} ", user1);

        dao.save(user1);

    }


    public User search(String UserId){

        log.info("UserId = {} ",UserId);

        User search = dao.search(UserId);

        return search;

    }

}
