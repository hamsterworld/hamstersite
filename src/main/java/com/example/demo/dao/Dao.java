package com.example.demo.dao;

import com.example.demo.dto.Board;
import com.example.demo.dto.User;

import java.util.ArrayList;


public interface Dao {

    //회원 저장
    public User save(User user);

    //회원 조회
    public User search(String UserId);

    //게시판 전체 조회
    public ArrayList<Board> boardsearch();

}
