package com.example.demo.reply.replyservice;


import com.example.demo.Mapper.DtoMapper;
import com.example.demo.dto.ReplyComment;
import com.example.demo.dto.ReplyCommentWrite;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ReplyService {

    private final DtoMapper mapper;

    public void replyInsert(ReplyCommentWrite replyCommentWrite) {

        int i = mapper.insertReply(replyCommentWrite);

    }
}
