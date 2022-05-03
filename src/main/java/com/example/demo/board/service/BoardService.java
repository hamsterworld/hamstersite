package com.example.demo.board.service;


import com.example.demo.Mapper.DtoMapper;
import com.example.demo.dao.Dao;
import com.example.demo.dto.Board;
import com.example.demo.dto.BoardWriteForm;
import com.example.demo.paging.paging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

        List<Board> boards = mapper.boardCount(map);

        log.info("boardsearch = {} ",boards);

        model.addAttribute("Board",boards);
        model.addAttribute("paging",paging);

    }


    public void BoardWrite(BoardWriteForm boardWriteForm){

        dao.boardwrite(boardWriteForm);

    }


}
