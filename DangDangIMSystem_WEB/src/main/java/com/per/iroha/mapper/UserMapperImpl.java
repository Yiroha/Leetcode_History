package com.per.iroha.mapper;

import com.per.iroha.model.Advice;
import com.per.iroha.model.Group;

import java.sql.*;

public class UserMapperImpl {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/dangdangim?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "308512231";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private Connection conn = null;

    public int getUserId(String name) throws SQLException {

        PreparedStatement pst;
        ResultSet rs;

        conn = conn();

        String sql1 = "select userId from `user` where realName=?";

        String sql2 = "select userId from `user` where username=?";

        pst = conn.prepareStatement(sql1);
        pst.setString(1, name);

        rs = pst.executeQuery();
        if(rs.next()){
            return rs.getInt("userId");
        }else{
            pst = conn.prepareStatement(sql2);
            pst.setString(1, name);
            rs = pst.executeQuery();

            if(rs.next()){
                return rs.getInt("userId");
            }
        }
        close();
        return 0;
    }

    public void saveAdvice(Advice advice) throws SQLException {

        PreparedStatement pst;

        conn = conn();

        String sql = "insert into advice (`date`,fromUsername,advice) values(?,?,?)";

        pst = conn.prepareStatement(sql);

        pst.setString(1,advice.getDate());
        pst.setString(2,advice.getFromUsername());
        pst.setString(3,advice.getAdvice());

        pst.executeUpdate();

        close();
    }

    /**
     * 连接数据库
     * @return
     */
    private Connection conn() {
        Connection conn = null;
        try {
            Class.forName(driver);  //加载数据库驱动
            try {
                conn = DriverManager.getConnection(url, username, password);  //连接数据库
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库链接
     * @return
     */
    private void close() {
        if(conn != null) {
            try {
                conn.close();  //关闭数据库链接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
