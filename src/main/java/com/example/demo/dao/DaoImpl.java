package com.example.demo.dao;

import com.example.demo.dto.Board;
import com.example.demo.dto.BoardWriteForm;
import com.example.demo.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j
@Component
public class DaoImpl implements Dao{

    private final DataSource dataSource;

    @Override
    public User save(User user) {

        Connection con = null;
        PreparedStatement pstmt = null;

        String sql = "insert into member values(member_seq.nextval,?,?,?,?)";

        try {

            con = getConnection(dataSource);
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserPassWord());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getNickName());

            log.info("user= {} ",user);

            pstmt.executeUpdate();

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, null);
        }

        return null;
    }


    @Override
    public User search(String UserId) {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from member where UserId = ?";

        try {
            con = getConnection(dataSource);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, UserId);

            rs = pstmt.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setUserNumber(rs.getLong("UserNumber"));
                user.setUserId(rs.getString("UserId"));
                user.setUserPassWord(rs.getString("USERPassWord"));
                user.setUserName(rs.getString("UserName"));
                user.setNickName(rs.getString("NickName"));

                return user;

            } else {
                throw new NoSuchElementException("member not found userId=" + UserId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }

        return null;
    }

    @Override
    public ArrayList<Board> boardsearch() {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Board> Boards = new ArrayList<>();

        String sql = "select * from board";

        try{

            con = getConnection(dataSource);
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();


            while(rs.next()) {

                Board board = new Board();

                board.setUserNumber(rs.getLong("UserNumber"));
                board.setBoardContent(rs.getString("BoardContent"));
                board.setBoardNumber(rs.getLong("BoardNumber"));
                board.setBoardView(rs.getLong("BoardView"));
               // board.setBoardTitle(rs.getString("BoardTitle"));

                log.info("너 boardnumber = {} ",board.getBoardNumber());


                Boards.add(board);

            }

            return Boards;

        } catch (Exception e){

            e.printStackTrace();

        } finally {

            close(con, pstmt, rs);

        }

        return null;
    }



    public void boardwrite(BoardWriteForm boardWriteForm){

        Connection con = null;
        PreparedStatement pstmt = null;

        String sql = "insert into board values(member_seq.nextval,?,?,0,?)";


        try {

            con = getConnection(dataSource);
            pstmt = con.prepareStatement(sql);

            pstmt.setLong(3, boardWriteForm.getUserNumber());
            pstmt.setString(1, boardWriteForm.getBoardtitle());
            pstmt.setString(2, boardWriteForm.getBoardcontent());

            pstmt.executeUpdate();


        } catch (SQLException e) {
             e.printStackTrace();
        } finally {
             close(con, pstmt, null);
        }


    }

    @Override
    public void boardupdate(Board board) {

        Connection con = null;
        PreparedStatement pstmt = null;

        String sql = "update board set BoardTitle = ? ,boardcontent = ? where BoardNumber= ?";



        try {

            con = getConnection(dataSource);
            pstmt = con.prepareStatement(sql);

            pstmt.setLong(3, board.getBoardNumber());
            pstmt.setString(1, board.getBoardTitle());
            pstmt.setString(2, board.getBoardContent());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con, pstmt, null);
        }


    }


    private void close(Connection con,PreparedStatement stmt,ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        DataSourceUtils.releaseConnection(con, dataSource);

    }

    public Connection getConnection(DataSource dataSource) throws SQLException {

        Connection con = DataSourceUtils.getConnection(dataSource);

        return con;
    }



}
