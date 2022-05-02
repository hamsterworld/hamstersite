package com.example.demo.board.service;

import com.example.demo.dao.Dao;
import com.example.demo.dto.Board;
import com.example.demo.dto.BoardWriteForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class BoardServiceTest {

    @Autowired
    Dao dao;

    @Test
    void boardSee() {

        log.info("Dao = {} ", dao);

        ArrayList<Board> BoardSearch = dao.boardsearch();

        log.info("BoardSearch = {} ",BoardSearch);

        assertThat(BoardSearch).isNotNull();

    }


    @Test
    public void BoardWrite(){

        BoardWriteForm boardWriteForm = new BoardWriteForm();
        boardWriteForm.setUserNumber(10L);
        boardWriteForm.setBoardTitle("이것도 테스트 데이터 입니다.");
        boardWriteForm.setBoardContent("이건 테스트데이터의 내용입니다.");

        dao.boardwrite(boardWriteForm);


    }


}