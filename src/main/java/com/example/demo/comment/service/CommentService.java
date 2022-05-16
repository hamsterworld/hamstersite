package com.example.demo.comment.service;

import com.example.demo.Mapper.DtoMapper;
import com.example.demo.dto.Comment;
import com.example.demo.dto.CommentDeleteForm;
import com.example.demo.dto.CommentWriteForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {


    private final DtoMapper mapper;

    public List<Comment> selectListComment(Integer boardnumber){

       return mapper.ListSelectComment(boardnumber);

    }


    public void WriteComment(CommentWriteForm commentWriteForm){

        Comment comment = new Comment();

        comment.setNickname(commentWriteForm.getNickname());
        comment.setBoardnumber(commentWriteForm.getBoardnumber());
        comment.setCommentcontent(commentWriteForm.getCommentcontent());
        comment.setUsernumber(commentWriteForm.getUsernumber());

        log.info("comment = {} ",comment);

        mapper.insertComment(comment);

    }

    public void DeleteComment(CommentDeleteForm commentDeleteForm){

        mapper.deleteComment(commentDeleteForm.getCommentnumber());

    }


}
