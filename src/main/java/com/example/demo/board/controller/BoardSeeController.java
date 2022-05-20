package com.example.demo.board.controller;


import com.example.demo.board.service.BoardService;
import com.example.demo.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Slf4j
@Controller
public class BoardSeeController {

    private final BoardService boardService;

    @GetMapping("/board/{BoardNumber}")
    public String SeeBoard(@PathVariable Integer BoardNumber,
                           Model model,
                           @RequestParam Integer page,
                           @RequestParam Integer pagesize,
                           @RequestParam Integer cpage,
                           @RequestParam Integer cpagesize){

        Long BoradNumberL = new Long(BoardNumber);

        log.info("숫자가 정상적인가요?= {} ", BoradNumberL);

        model.addAttribute("page",page);
        model.addAttribute("pagesize",pagesize);
        model.addAttribute("boardnumber",BoardNumber);

        boardService.BoardSee(BoradNumberL,BoardNumber,model,cpage,cpagesize);

        return "boardSee";
    }



}
