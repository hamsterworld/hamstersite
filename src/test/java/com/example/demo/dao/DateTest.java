package com.example.demo.dao;

import com.example.demo.Mapper.DtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Slf4j
@SpringBootTest
public class DateTest {

    @Autowired
    DtoMapper mapper;


    @Test
    void DateTest(){

        Timestamp date = mapper.date();

        log.info("현재 시간 = {} ", date);

    }

}
