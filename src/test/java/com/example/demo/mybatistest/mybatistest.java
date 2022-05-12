package com.example.demo.mybatistest;


import com.example.demo.Mapper.DtoMapper;
import com.example.demo.dto.Board;
import com.example.demo.dto.BoardUser;
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
import static org.junit.jupiter.api.Assertions.assertTrue;


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

        List<BoardUser> BoardUser = mapper.boardCount(map);

        log.info("게시물 = {} ",BoardUser);

        assertThat(BoardUser).isNotNull();

    }
    
    @Test
    @DisplayName("제대로 들어가는지 확인하기")
    void InsertBoard(){
        Board board = new Board();
        
        board.setBoardView(0L);
        board.setBoardTitle("으하하");
        board.setBoardContent("반갑다. 이건 글쓰기내용이야.");
        board.setUserNumber(10L);

        int i = mapper.insertBoard(board);

        assertTrue(i == 1);

    }

    @Test
    @DisplayName("boardsee를 위해 테스트")
    void seeBoard(){


        Board board = mapper.searchOneBoard(54L);

        log.info("board = {} ",board);

        User user = mapper.searchOneUser(10L);

        log.info("user = {} ",user);

        assertTrue(board.getBoardNumber() == 54);
        assertTrue(user.getNickName().equals("귀여운게죄라면난사형"));


    }

    @Test
    void UpdateBoard(){

        Board board = new Board();

        board.setBoardTitle("으ef하fwef하");
        board.setBoardContent("wfwefwe내용이야.");
        board.setBoardNumber(43L);

        mapper.updateBoard(board);

    }

}
