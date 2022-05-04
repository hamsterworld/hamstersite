package com.example.demo.board.controller;


import com.example.demo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.net.Inet4Address;

@RequiredArgsConstructor
@Controller
@Slf4j
public class BoardDeleteController {

    private final BoardService boardService;

    @PostMapping("/board/delete")
    public String deleteBoard(@RequestParam("BoardNumber") Long BoardNumber,
                              @RequestParam("page") Integer page,
                              @RequestParam("pageSize") Integer pageSize,
                              HttpSession session,
                              Model model,
                              RedirectAttributes redirectAttributes){

        String s = boardService.boardDelete(BoardNumber, session);

        model.addAttribute("page",page);
        model.addAttribute("pagesize",pageSize);
        redirectAttributes.addAttribute("msg","실패했습니다.");

        return "redirect:/board";

    }


}
