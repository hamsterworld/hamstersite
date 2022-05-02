package com.example.demo.doublecheck.service;

import com.example.demo.dao.Dao;
import com.example.demo.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoubleCheckService {

    private final Dao dao;

    public boolean check(String UserId){

        User search = dao.search(UserId);

        return search != null ? true : false ;

    }

}
