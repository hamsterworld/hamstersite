package com.example.demo.mybatistest;


import com.example.demo.Mapper.DtoMapper;
import com.example.demo.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
@SpringBootTest
public class mybatistest {

    @Autowired
    DtoMapper mapper;


    @Test
    public void MapperTest(){

        List<User> members = mapper.findAll();

        log.info("members = {} ",members);

        assertThat(members).isNotNull();
    }

    @Test
    @DisplayName("갯수 세기")
    void BoardCount(){

        Map map = new HashMap<>();

        map.put("start",1);
        map.put("end",10);

        List<BoardUser> BoardUser = mapper.boardCount(map);

        log.info("게시물 = {} ",BoardUser);

        assertThat(BoardUser).isNotNull();

    }
    
    @Test
    @DisplayName("제대로 들어가는지 확인하기")
    void InsertBoard(){
        Board board = new Board();
        
        board.setBoardView(0L);
        board.setBoardTitle("으하하");
        board.setBoardContent("반갑다. 이건 글쓰기내용이야.");
        board.setUserNumber(10L);

        int i = mapper.insertBoard(board);

        assertTrue(i == 1);

    }

    @Test
    @DisplayName("boardsee를 위해 테스트")
    void seeBoard(){


        Board board = mapper.searchOneBoard(54L);

        log.info("board = {} ",board);

        User user = mapper.searchOneUser(10L);

        log.info("user = {} ",user);

        assertTrue(board.getBoardNumber() == 54);
        assertTrue(user.getNickName().equals("귀여운게죄라면난사형"));


    }

    @Test
    void UpdateBoard(){

        Board board = new Board();

        board.setBoardTitle("으ef하fwef하");
        board.setBoardContent("wfwefwe내용이야.");
        board.setBoardNumber(43L);

        mapper.updateBoard(board);

    }

    @Test
    @DisplayName("코맨트 입력 확인")
    void insertComment(){

        Comment comment = new Comment();

        comment.setBoardnumber(10);
        comment.setUsernumber(10);
        comment.setNickname("귀여운게죄라면난사형");
        comment.setCommentcontent("첫 댓글입니다.");

        mapper.insertComment(comment);
        Comment comment1 = mapper.selectComment(1);

        log.info("comment1 = {} ",comment1);

        assertTrue(comment.getNickname().equals(comment1.getNickname()));

    }

    @Test
    @DisplayName("코멘트 한개를 잘 받아올수있는지")
    void selectcomment(){

        Comment comment1 = mapper.selectComment(1);

        log.info("comment1 = {} ",comment1);

        assertTrue("귀여운게죄라면난사형".equals(comment1.getNickname()));

    }

    @Test
    @DisplayName("코멘트를 잘 삭제할수있는지")
    void deletecomment(){

        int i = mapper.deleteComment(2);

        assertTrue(i == 1);

    }

    @Test
    @DisplayName("각 게시판마다 코멘트개수를 잘 받아올수있는지")
    void countcomment(){

        int i = mapper.CountComment(10);

        assertTrue(i==1);

    }

    @Test
    @DisplayName("각 게시판마다 코멘트를 잘 받아올수있는지")
    void Listcountcomment(){

        List<Comment> comments = mapper.ListSelectComment(10);

        log.info("comments = {} ", comments);

    }

    @Test
    @DisplayName("코멘트를 잘 수정할수있는지")
    void updatecomment(){

        CommentUpdateForm commentUpdateForm = new CommentUpdateForm();

        commentUpdateForm.setCommentnumber(7);
        commentUpdateForm.setUsernumber(10);
        commentUpdateForm.setCommentcontent("변화란 좋은거야.");

        int i = mapper.updateComment(commentUpdateForm);

        Comment comment = mapper.selectComment(7);

        assertTrue(i == 1);

        assertTrue(comment.getCommentcontent().equals(commentUpdateForm.getCommentcontent()));

    }


}
