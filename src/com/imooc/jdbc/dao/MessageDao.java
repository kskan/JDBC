package com.imooc.jdbc.dao;

import com.imooc.jdbc.bean.Message;
import com.imooc.jdbc.common.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDao {

    public boolean save(Message message){
        Connection conn=ConnectionUtil.getConnection();
        String sql="insert into message(user_id, username, title, content, create_time) values (?, ?, ?, ?, ?)";
        PreparedStatement stmt=null;
        try{
            stmt=conn.prepareStatement(sql);
            stmt.setLong(1,message.getUserid());
            stmt.setString(2,message.getUsername());
            stmt.setString(3,message.getTitle());
            stmt.setString(4,message.getContent());
            stmt.setTimestamp(5,new Timestamp(message.getCreateTime().getTime()));
            stmt.execute();

        }catch (Exception e){
            System.out.println("保存留言信息失败");
            e.printStackTrace();
            return false;
        }finally {
            ConnectionUtil.release(null,stmt,conn);
        }
        return true;
    }

    public List<Message> getMessages(int page,int pageSize){

        Connection conn= ConnectionUtil.getConnection();
        String sql="select * from message order by create_time desc limit ?,?;";
        PreparedStatement stmt=null;
        ResultSet rs=null;
        List<Message> messages=new ArrayList<>();
        try {
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,(page-1)*pageSize);
            stmt.setInt(2,pageSize);
            rs=stmt.executeQuery();
            while (rs.next()){
                Long id=rs.getLong("id");
                Long user_id=rs.getLong("user_id");
                String username=rs.getString("username");
                String title=rs.getString("title");
                String content=rs.getString("content");
                Date create=rs.getTimestamp("create_time");
                messages.add(new Message( id,  user_id, username, title , content,create));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.release(rs,stmt,conn);
        }

        return messages;
    }
    public  boolean ReWhiteMessage(String title,String content,Long messageid){
        Connection conn=ConnectionUtil.getConnection();
        String sql ="update message set title= ? ,content=? where id=?";
        PreparedStatement stmt=null;
        try{
            stmt= conn.prepareStatement(sql);
            stmt.setString(1,title);
            stmt.setString(2,content);
            stmt.setLong(3,messageid);
            stmt.execute();
        }catch (Exception e){
            System.out.println("修改信息失败");
            e.printStackTrace();
            return false;

        }finally {
            ConnectionUtil.release(null,stmt,conn);
        }
        return true;
    }
    public List<Message> getMyMessages(Long userid,int page,int pageSize){
        Connection conn= ConnectionUtil.getConnection();
        String sql="select * from message where user_id = ? order by create_time desc limit ?,?;";
        PreparedStatement stmt=null;
        ResultSet rs=null;
        List<Message> messages=new ArrayList<>();
        try {
            stmt=conn.prepareStatement(sql);
            stmt.setLong(1,userid);
            stmt.setInt(2,(page-1)*pageSize);
            stmt.setInt(3,pageSize);
            rs=stmt.executeQuery();
            while (rs.next()){
                Long id=rs.getLong("id");
                Long user_id=rs.getLong("user_id");
                String username=rs.getString("username");
                String title=rs.getString("title");
                String content=rs.getString("content");
                Date create=rs.getTimestamp("create_time");
                messages.add(new Message( id,  user_id, username, title , content,create));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.release(rs,stmt,conn);
        }

        return messages;

    }
    public Message getMyOneMessages(Long messageid){
        Connection conn= ConnectionUtil.getConnection();
        String sql="select * from message where id = ? ;";
        PreparedStatement stmt=null;
        ResultSet rs=null;
        Message messages=null;
        try {
            stmt=conn.prepareStatement(sql);
            stmt.setLong(1,messageid);
            rs=stmt.executeQuery();
            while (rs.next()){
                Long id=rs.getLong("id");
                Long user_id=rs.getLong("user_id");
                String username=rs.getString("username");
                String title=rs.getString("title");
                String content=rs.getString("content");
                Date create=rs.getTimestamp("create_time");
                messages = new Message( id,  user_id, username, title , content,create);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.release(rs,stmt,conn);
        }

        return messages;

    }
    //计算所有留言
    public int countMessages(){
        Connection conn=ConnectionUtil.getConnection();
        String sql="select count(*) total from message";
        PreparedStatement stmt=null;
        ResultSet rs =null;
        try {
            stmt=conn.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                return  rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionUtil.release(rs,stmt,conn);
        }

        return 0;

    }
    public  boolean DelMessage(Long messageid){
        Connection conn=ConnectionUtil.getConnection();
        String sql="delete from message where id= ? ";
        PreparedStatement stmt=null;
        try{
            stmt=conn.prepareStatement(sql);
            stmt.setLong(1,messageid);
            stmt.execute();
        }catch(SQLException e){
            System.out.println("数据删除失败");
            e.printStackTrace();
            return false;

        }finally{
            ConnectionUtil.release(null,stmt,conn);
        }
        return true;



    }
}
