package com.example.demo.board.controller;


import com.example.demo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.DocFlavor;

@RequiredArgsConstructor
@Slf4j
@Controller
public class BoardSeeController {

    private final BoardService boardService;

    @GetMapping("/board/{BoardNumber}")
    public String SeeBoard(@PathVariable Long BoardNumber,
                           Model model,
                           @RequestParam Integer page,
                           @RequestParam Integer pagesize){

        model.addAttribute("page",page);
        model.addAttribute("pagesize",pagesize);

        boardService.BoardSee(BoardNumber,model);

        return "boardSee";
    }



}
