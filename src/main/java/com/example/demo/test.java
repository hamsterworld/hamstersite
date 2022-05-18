package com.example.demo;

import com.example.demo.Mapper.DtoMapper;
import com.example.demo.comment.service.CommentService;
import com.example.demo.dto.Comment;
import com.example.demo.dto.testdto;
import com.example.demo.paging.paging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class test {

    private final CommentService commentService;
    private final DtoMapper mapper;

    @GetMapping("/test")
    public String test(@RequestParam Integer cpage,
                       @RequestParam Integer cpagesize,
                       @RequestParam Integer boardnumber,
                       Model model){

        if(cpage==null) cpage = 1;
        if(cpagesize==null) cpagesize = 10;

        boardnumber = 10;

        int countComment = mapper.CountComment(boardnumber);

        paging paging = new paging(countComment, cpage, cpagesize);

        model.addAttribute("paging",paging);

        return "test";

    }

    @GetMapping("/test2")
    public String test2(HttpServletRequest request){

        return "test2";

    }

    @GetMapping("/test3")
    @ResponseBody
    public String test3(){

        return "json이 잘되는겁니다.";

    }



    @PostMapping("/test2")
    @ResponseBody
    public Map<String,Object> test3(@RequestBody testdto testdto){

        log.info("testdto = {} ", testdto);

        Map map = new HashMap<>();

        int totalcommentcount = mapper.CountComment(testdto.getBoardnumber());

        log.info("댓글이 이렇게 많이 썻어? ={}",totalcommentcount);

        if(testdto.getPage() == null) testdto.setPage(1);
        if(testdto.getPagesize() == null) testdto.setPagesize(10);

        paging paging = new paging(totalcommentcount,testdto.getPage(),testdto.getPagesize());

        map.put("start",(testdto.getPage()-1)*testdto.getPagesize()+1);
        map.put("end",((testdto.getPage()-1)*testdto.getPagesize()+1)+9);
        map.put("paging",paging);

        return map;

    }


}
