package com.example.demo.dao;

import com.example.demo.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
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
