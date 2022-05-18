package com.example.demo.reply.replycontroller;


import com.example.demo.dto.ReplyComment;
import com.example.demo.dto.ReplyCommentWrite;
import com.example.demo.reply.replyservice.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
public class ReplyController {

    private final ReplyService replyService;


    @PostMapping("/replyinsert")
    public String replyInsert(@RequestBody ReplyCommentWrite replyCommentWrite){

        log.info("reply = {} ",replyCommentWrite);

        replyService.replyInsert(replyCommentWrite);

        return "ok";

    }


}
