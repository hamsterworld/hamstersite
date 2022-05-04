package com.example.demo.Mapper;


import com.example.demo.dto.Board;
import com.example.demo.dto.User;
import org.apache.ibatis.annotations.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DtoMapper {


    public List<User> findAll();

    @Select("select * from board")
    public List<Board> findBoardAll();

    @Select("select count(*) from board")
    public int findTotalCount();

    public List<Board> boardCount(Map map);

    public Integer insertBoard(Board board);

    @Select("select * from board where BoardNumber = #{BoardNumber}")
    public Board searchOneBoard(@Param("BoardNumber") int BoardNumber);

    @Select("select * from member where UserNumber = #{UserNumber}")
    public User searchOneUser(@Param("UserNumber") Long UserNumber);
}
