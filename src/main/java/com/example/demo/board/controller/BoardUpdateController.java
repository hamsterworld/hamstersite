package com.example.demo.board.controller;


import com.example.demo.board.service.BoardService;
import com.example.demo.dto.Board;
import com.example.demo.dto.BoardUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Slf4j
@Controller
public class BoardUpdateController {

    private final BoardService boardSErvice;

    @GetMapping("/update")
    public String updateBoard(@RequestParam("BoardNumber") Long boardNumber,
                              @RequestParam Integer page,
                              @RequestParam Integer pagesize,
                              HttpSession session,
                              RedirectAttributes redirectAttributes,
                              Model model){

        Board board = boardSErvice.NotUpViewBoardSee(boardNumber, model);

        if(!boardSErvice.boardconfirm(session, board)){

            redirectAttributes.addAttribute("page",page);
            redirectAttributes.addAttribute("pagesize",pagesize);
            redirectAttributes.addFlashAttribute("msg","UPD_NO");

            return "redirect:/board/"+boardNumber;

        }

        model.addAttribute("page",page);
        model.addAttribute("pagesize",pagesize);

        return "boardupdate";
    }

    @PostMapping("/update")
    @ResponseBody
    public String PostupdateBoard(@RequestBody BoardUpdateForm boardUpdateForm,
                                  Model model){

        log.info("boardupdatefrom = {} ",boardUpdateForm);


        boardSErvice.boardUpdate(boardUpdateForm);

        return "success";

    }


}
