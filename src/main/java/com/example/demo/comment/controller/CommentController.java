package com.example.demo.comment.controller;


import com.example.demo.comment.service.CommentService;
import com.example.demo.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public HashMap<String, Object> CommentSee(@RequestBody testdto testdto){

        log.info("testdto = {} ", testdto);

        List<Comment> comments = commentService.selectListComment(testdto.getBoardnumber(),testdto.getCpage(),testdto.getCpagesize());

        log.info("targetcomment = {} ",  comments);

        HashMap<String, Object> Map = new HashMap<>();

        Map.put("comments",comments);

        return Map;
    }

    @PostMapping("/commentdelete")
    public String CommentDelete(@RequestBody CommentDeleteForm commentDeleteForm){

        log.info("잘왓니? = {} ",commentDeleteForm);

        commentService.DeleteComment(commentDeleteForm);

        return "ok";

    }

    @PostMapping("/commentwrite")
    public String CommentWrite(@RequestBody CommentWriteForm commentWriteForm){

        log.info("잘왓니? = {} ",commentWriteForm);

        commentService.WriteComment(commentWriteForm);

        return "ok";
    }

    @PostMapping("/commentoneselect")
    public Comment commentoneselect(@RequestBody CommentUpdateoneForm commentUpdateoneForm){

        return commentService.SelectOneComment(commentUpdateoneForm.getCommentnumber());

    }

    @PostMapping("/commentupdate")
    public void CommentUpdate(@RequestBody CommentUpdateForm commentUpdateForm){

        commentService.UpdateComment(commentUpdateForm);

    }


}
