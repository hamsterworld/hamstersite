package com.example.demo.board.controller;

import com.example.demo.board.service.BoardService;
import com.example.demo.dto.BoardWriteForm;
import com.example.demo.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Slf4j
@Controller
public class BoardWriteController {

    private final BoardService boardService;


    @GetMapping("/write")
    public String Write(HttpSession session,Model model){

        User loginUser = (User) session.getAttribute("LoginUser");
        model.addAttribute("User",loginUser);

        return "BoardWrite";
    }

    @PostMapping("/write")
    @ResponseBody
    public String PostWrite(@RequestBody BoardWriteForm boardWriteForm,
                            HttpSession session,
                            Model model){

        boardService.BoardWrite(boardWriteForm,session);
        boardService.BoardSee(1,10,model);

     return "ok";

    }

}
