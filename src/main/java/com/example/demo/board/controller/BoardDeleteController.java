package com.example.demo.board.controller;


import com.example.demo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.net.Inet4Address;

@RequiredArgsConstructor
@Controller
@Slf4j
public class BoardDeleteController {

    private final BoardService boardService;

    @PostMapping("/delete")
    public RedirectView deleteBoard(@RequestParam("BoardNumber") Long BoardNumber,
                                    @RequestParam("page") Integer page,
                                    @RequestParam("pageSize") Integer pageSize,
                                    HttpSession session,
                                    Model model,
                                    RedirectAttributes redirectAttributes){



        String s = boardService.boardDelete(BoardNumber, session);

        if(s.equals("false")){

            redirectAttributes.addFlashAttribute("msg","DEL_NO");
            redirectAttributes.addAttribute("page",page);
            redirectAttributes.addAttribute("pagesize",pageSize);
            redirectAttributes.addAttribute("BoardNumber",BoardNumber);

            return new RedirectView("/board/{BoardNumber}");

        }

        redirectAttributes.addAttribute("page",page);
        redirectAttributes.addAttribute("pagesize",pageSize);
        redirectAttributes.addFlashAttribute("msg","DEL_OK");

        return new RedirectView("/board");

    }


}
