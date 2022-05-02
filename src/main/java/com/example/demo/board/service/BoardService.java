package com.example.demo.board.service;


import com.example.demo.dao.Dao;
import com.example.demo.dto.Board;
import com.example.demo.dto.BoardWriteForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final Dao dao;

    public void BoardSee(Model model){

        ArrayList<Board> boardsearch = dao.boardsearch();

        log.info("boardsearch = {} ",boardsearch);

        model.addAttribute("Board",boardsearch);

    }


    public void BoardWrite(BoardWriteForm boardWriteForm){

        dao.boardwrite(boardWriteForm);

    }


}
