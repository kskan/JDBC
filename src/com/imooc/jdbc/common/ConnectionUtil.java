package com.imooc.jdbc.common;

import java.sql.*;

public final class ConnectionUtil {

    private static String url="jdbc:mysql://localhost:3306/message_board?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";

    private static  String user="root";

    private static  String password="a13316153401";

    private ConnectionUtil(){}
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到驱动类");
            e.printStackTrace();
        }

    }
//    创建数据库
    public  static Connection getConnection(){
        try {
          return DriverManager.getConnection(url, user, password);
        }catch ( SQLException e){
            System.out.println("创建数据库链接失败");
             e.printStackTrace();
            return null;

        }

    }
//    释放数据库资源
    public static  void release(ResultSet rs, Statement stmt,Connection conn){
        try{
            if(rs!=null){
                rs.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (stmt != null) {

                    stmt.close();
                }
            }catch (SQLException e) {
                    e.printStackTrace();
            }finally {
                try{
                    if(conn!=null){
                        conn.close();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }


        }
    }
}
