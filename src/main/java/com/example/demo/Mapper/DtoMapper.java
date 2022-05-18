package com.example.demo.Mapper;


import com.example.demo.dto.*;
import org.apache.ibatis.annotations.*;


import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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

    public int insertComment(Comment comment);

    @Select("select * from commenttable where commentnumber = #{commentnumber}" )
    public Comment selectComment(Integer Commentnumber);

    @Select("select count(*) from commenttable where boardnumber = #{boardnumber}")
    public int CountComment(Integer boardnumber);

    //@Select("select commentnumber,boardnumber,usernumber,nickname,commentcontent,commentdate,nvl(parentcommentnumber,commentnumber) as parentcommentnumber,targetnickname " +
            //"from commenttable where boardnumber = #{boardnumber} order by parentcommentnumber asc,commentnumber asc")
    //public List<Comment> ListSelectComment(Integer boardnumber);

    public List<Comment> ListSelectComment(Map map);

    public int updateComment(CommentUpdateForm commentUpdateForm);

    @Delete("delete commenttable where commentnumber = #{commentnumber}")
    public int deleteComment(Integer commentnumber);

    public int insertReply(ReplyCommentWrite replyCommentwrite);

}
