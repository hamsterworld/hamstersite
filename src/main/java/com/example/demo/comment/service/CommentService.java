package com.example.demo.comment.service;

import com.example.demo.Mapper.DtoMapper;
import com.example.demo.dto.Comment;
import com.example.demo.dto.CommentDeleteForm;
import com.example.demo.dto.CommentUpdateForm;
import com.example.demo.dto.CommentWriteForm;
import com.example.demo.paging.paging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {


    private final DtoMapper mapper;

    public List<Comment> selectListComment(Integer boardnumber, Integer page, Integer pagesize){

        Map map = new HashMap<>();

        int totalcommentcount = mapper.CountComment(boardnumber);

        if(page == null) page = 1;
        if(pagesize == null) pagesize = 5;

        paging paging = new paging(totalcommentcount,page,pagesize);

        map.put("start",(page-1)*pagesize+1);
        map.put("end",((page-1)*pagesize+1)+9);

        //model.addAttribute("paging",paging);

        return mapper.ListSelectComment(map);

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

    public Comment SelectOneComment(Integer commentnumber){

       return mapper.selectComment(commentnumber);

    }


    public void UpdateComment(CommentUpdateForm commentUpdateForm) {

        log.info("commentUpdateform = {} ", commentUpdateForm);

        mapper.updateComment(commentUpdateForm);

    }
}
