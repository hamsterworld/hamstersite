package com.example.demo.board.service;


import com.example.demo.Mapper.DtoMapper;
import com.example.demo.dao.Dao;
import com.example.demo.dto.*;
import com.example.demo.paging.paging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final Dao dao;
    private final DtoMapper mapper;


    public void BoardSee(Integer page,Integer pagesize,Model model){

        Map map = new HashMap<>();

        if(page == null) page = 1;
        if(pagesize == null) pagesize = 10;

        int totalCount = mapper.findTotalCount();

        paging paging = new paging(totalCount,page,pagesize);

        map.put("start",(page-1)*pagesize+1);
        map.put("end",((page-1)*pagesize+1)+9);

        List<BoardUser> BoardUser = mapper.boardCount(map);

        log.info("boardsearch = {} ",BoardUser);

        model.addAttribute("page",page);
        model.addAttribute("Board",BoardUser);
        model.addAttribute("paging",paging);

    }


    public void BoardWrite(BoardWriteForm boardWriteForm,
                           HttpSession session
                           ){

        User loginUser = (User) session.getAttribute("LoginUser");

        Board board = new Board();

        board.setUserNumber(loginUser.getUserNumber());
        board.setBoardTitle(boardWriteForm.getBoardtitle());
        board.setBoardContent(boardWriteForm.getBoardcontent());
        board.setBoardView(0L);

        mapper.insertBoard(board);

    }

    public Board BoardSee(Long BoardNumber,Integer BoardNumberint,Model model,Integer cpage,Integer cpagesize){



        Board board = mapper.searchOneBoard(BoardNumber);

        User user = mapper.searchOneUser(board.getUserNumber());

        int i = mapper.UpdateViewBoard(BoardNumber);


        //comment paging처리

        if(cpage==null) cpage = 1;
        if(cpagesize==null) cpagesize = 10;

        int countComment = mapper.CountComment(BoardNumberint);

        paging paging = new paging(countComment, cpage, cpagesize);

        log.info("paging = {} ",paging);

        model.addAttribute("paging",paging);
        model.addAttribute("boardnumber",BoardNumber);

        model.addAttribute("Board",board);
        model.addAttribute("User",user);

        return board;

    }

    public Board BoardSee(Long BoardNumber,Model model){

        Board board = mapper.searchOneBoard(BoardNumber);

        User user = mapper.searchOneUser(board.getUserNumber());

        int i = mapper.UpdateViewBoard(BoardNumber);

        model.addAttribute("Board",board);
        model.addAttribute("User",user);

        return board;

    }

    public Board NotUpViewBoardSee(Long BoardNumber,Model model){

        Board board = mapper.searchOneBoard(BoardNumber);

        User user = mapper.searchOneUser(board.getUserNumber());

        model.addAttribute("Board",board);
        model.addAttribute("User",user);

        return board;

    }

    public String boardDelete(Long BoardNumber,HttpSession session){

        User loginUser = (User)session.getAttribute("LoginUser");
        Board board = mapper.searchOneBoard(BoardNumber);
        User user = mapper.searchOneUser(board.getUserNumber());

        if(!loginUser.getUserId().equals(user.getUserId())){
            return "false";
        }

        int i = mapper.deleteBoard(BoardNumber);

        return "i";

    }

    public void boardUpdate(BoardUpdateForm boardUpdateForm){

        log.info("boardupdateform = {} ",boardUpdateForm);

        Board board = new Board();

        board.setBoardTitle(boardUpdateForm.getBoardtitle());
        board.setBoardContent(boardUpdateForm.getBoardcontent());
        board.setBoardNumber(boardUpdateForm.getBoardnumber());

        mapper.updateBoard(board);

    }

    public boolean boardconfirm(HttpSession session,Board board){

        User user = (User)session.getAttribute("LoginUser");

        if(board.getUserNumber() == user.getUserNumber()){

            return true;

        }

        return false;


    }
}
