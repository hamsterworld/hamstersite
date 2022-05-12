package com.example.demo.Mapper;


import com.example.demo.dto.Board;
import com.example.demo.dto.BoardUpdateForm;
import com.example.demo.dto.BoardUser;
import com.example.demo.dto.User;
import org.apache.ibatis.annotations.*;


import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
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

    public List<BoardUser> boardCount(Map map);

    public Integer insertBoard(Board board);

    @Select("select * from board where BoardNumber = #{BoardNumber}")
    public Board searchOneBoard(@Param("BoardNumber") Long BoardNumber);

    @Select("select * from member where UserNumber = #{UserNumber}")
    public User searchOneUser(@Param("UserNumber") Long UserNumber);

    @Delete("delete from board where BoardNumber = #{BoardNumber}")
    public int deleteBoard(@Param("BoardNumber") Long boardNumber);

    @Update("update board set boardview = boardview + 1 where BoardNumber = #{BoardNumber}")
    public int UpdateViewBoard(@Param("BoardNumber") Long BoardNumber);

    public int updateBoard(Board Board);

    @Select("select sysdate from dual")
    public Timestamp date();

}
