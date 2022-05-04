package com.example.demo.board.controller;


import com.example.demo.board.service.BoardService;
import com.example.demo.dto.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.util.DbDriverActivator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Slf4j
@Controller
public class BoardUpdateController {

    private final BoardService boardSErvice;

    @GetMapping("/board/update")
    public String updateBoard(@RequestParam("BoardNumber") Long boardNumber,
                              Model model){

        boardSErvice.BoardSee(boardNumber,model);

        return "updateboard";
    }

    @PostMapping("/board/update")
    public String PostupdateBoard(@ModelAttribute("Board") Board board,
                                  Model model){

        //애초에 자기글이 아니면 수정삭제버튼이 안뜨기때문에 따로 검증할 이유가없다.

        boardSErvice.boardUpdate(board);

        return "redirect:/board";

    }


}
