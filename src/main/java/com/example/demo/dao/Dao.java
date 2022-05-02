package com.example.demo.dao;

import com.example.demo.dto.User;




public interface Dao {

    //회원 저장
    public User save(User user);

    //회원 조회
    public User search(String UserId);

}
