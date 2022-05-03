package com.example.demo.mybatistest;


import com.example.demo.Mapper.DtoMapper;
import com.example.demo.dto.Board;
import com.example.demo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@SpringBootTest
public class mybatistest {

    @Autowired
    DtoMapper mapper;


    @Test
    public void MapperTest(){

        List<User> members = mapper.findAll();

        log.info("members = {} ",members);

        assertThat(members).isNotNull();
    }

    @Test
    @DisplayName("갯수 세기")
    void BoardCount(){

        Map map = new HashMap<>();

        map.put("start",1);
        map.put("end",10);

        List<Board> boards = mapper.boardCount(map);

        log.info("게시물 = {} ",boards);

        assertThat(boards).isNotNull();

    }

}
