package com.example.demo.board.controller;


import com.example.demo.board.service.BoardService;
import com.example.demo.dto.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;


@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/board")
    public String Board(Integer page, Integer pagesize, Model model, HttpSession session,
                        RedirectAttributes redirectAttributes){

        boardService.BoardSee(page,pagesize,model);



        log.info("redirect = {} ", redirectAttributes.getFlashAttributes());

        log.info("model = {} ",model);
        return "board";

    }



}
