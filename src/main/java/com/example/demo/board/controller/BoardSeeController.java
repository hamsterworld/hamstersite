package com.example.demo.board.controller;


import com.example.demo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Slf4j
@Controller
public class BoardSeeController {

    private final BoardService boardService;

    @GetMapping("/board/{BoardNumber}")
    public String SeeBoard(@PathVariable Integer BoardNumber, Model model){

        boardService.BoardSee(BoardNumber,model);

        return "boardSee";
    }



}
