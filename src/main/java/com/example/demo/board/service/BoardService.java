package com.example.demo.board.service;


import com.example.demo.Mapper.DtoMapper;
import com.example.demo.dao.Dao;
import com.example.demo.dto.Board;
import com.example.demo.dto.BoardUser;
import com.example.demo.dto.BoardWriteForm;
import com.example.demo.dto.User;
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


    public void BoardWrite(BoardWriteForm boardWriteForm, HttpSession session){

        User loginUser = (User) session.getAttribute("LoginUser");

        Board board = new Board();

        board.setUserNumber(loginUser.getUserNumber());
        board.setBoardTitle(boardWriteForm.getBoardtitle());
        board.setBoardContent(boardWriteForm.getBoardcontent());
        board.setBoardView(0L);

        log.info("board = {} ", board);

        mapper.insertBoard(board);

    }

    public void BoardSee(Long BoardNumber,Model model){

        Board board = mapper.searchOneBoard(BoardNumber);

        User user = mapper.searchOneUser(board.getUserNumber());

        int i = mapper.UpdateViewBoard(BoardNumber);

        model.addAttribute("Board",board);
        model.addAttribute("User",user);

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

    public void boardUpdate(Board board){

        mapper.updateBoard(board);

    }

}
