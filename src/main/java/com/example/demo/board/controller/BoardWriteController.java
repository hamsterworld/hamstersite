package com.example.demo.board.controller;

import com.example.demo.board.service.BoardService;
import com.example.demo.dto.BoardWriteForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Slf4j
@Controller
public class BoardWriteController {

    private final BoardService boardService;


    @GetMapping("/write")
    public String Write(){
        return "write";
    }

    @PostMapping("/write")
    public String PostWrite(BoardWriteForm boardWriteForm){

        boardService.BoardWrite(boardWriteForm);

        return "board";

    }

}
