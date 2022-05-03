package com.example.demo.Mapper;


import com.example.demo.dto.Board;
import com.example.demo.dto.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
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







}
