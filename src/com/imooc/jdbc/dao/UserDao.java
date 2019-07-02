package com.imooc.jdbc.dao;

import com.imooc.jdbc.bean.User;
import com.imooc.jdbc.common.ConnectionUtil;
//import java.util.Date ;

import java.sql.*;


public class UserDao {
    public boolean register(String username,String password){
        Connection conn=ConnectionUtil.getConnection();
        String sql="insert into user( username, password) values (?, ?)";
        PreparedStatement stmt=null;
        try{
           stmt=conn.prepareStatement(sql);
           stmt.setString(1,username);
           stmt.setString(2,password);
           stmt.execute();

        }catch (Exception e){
            System.out.println("注册信息失败");
            e.printStackTrace();
            return false;
        }finally {
            ConnectionUtil.release(null,stmt,conn);
        }

        return true;

    }
    public User login(String username,String password){
        Connection conn = ConnectionUtil.getConnection();
        String sql="select * from user where username = ? and password = ?";
        PreparedStatement stmt=null;
        ResultSet rs=null;
        User user=null;
        try {
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,username);
            stmt.setString(2,password);
            rs=stmt.executeQuery();
            while (rs.next()){
                user=new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("username"));
                user.setRealname(rs.getString("real_name"));
                user.setPassword(rs.getString("password"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                System.out.println(user.toString());
            }

        } catch (SQLException e) {
            System.out.println("登陆失败");
            e.printStackTrace();
        }
        finally {
            ConnectionUtil.release(rs,stmt,conn);
        }
        return user;
    }
    public User getUserById(Long id){
        Connection conn = ConnectionUtil.getConnection();
        String sql="select * from user where id = ? ";
        PreparedStatement stmt=null;
        ResultSet rs=null;
        User user=null;
        try {
            stmt=conn.prepareStatement(sql);
            stmt.setLong(1,id);
//            stmt.setString(2,password);
            rs=stmt.executeQuery();
            while (rs.next()){
                user=new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("username"));
                user.setRealname(rs.getString("real_name"));
                user.setPassword(rs.getString("password"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                System.out.println(user.toString());
            }

        } catch (SQLException e) {
            System.out.println("查询用户信息失败");
            e.printStackTrace();
        }
        finally {
            ConnectionUtil.release(rs,stmt,conn);
        }
        return user;

    }
    public boolean updateUser(User user){
        Connection conn = ConnectionUtil.getConnection();
        String sql="update user set username= ? ,password= ?,real_name= ?,birthday= ?,phone= ? ,address= ? where id = ? ";
        PreparedStatement stmt=null;
//        ResultSet rs=null;
        try {
            stmt=conn.prepareStatement(sql);
            stmt.setString(1,user.getName());
            stmt.setString(2,user.getPassword());
            stmt.setString(3,user.getRealname());
            stmt.setDate(4,new Date(user.getBirthday().getTime()+86400000));
            stmt.setString(5,user.getPhone());
            stmt.setString(6,user.getAddress());
            stmt.setLong(7,user.getId());

            stmt.execute();



        } catch (SQLException e) {
            System.out.println("修改用户信息失败");


            e.printStackTrace();
            return false;
        }
        finally {
            ConnectionUtil.release(null,stmt,conn);
        }
        return true;

    }

}
