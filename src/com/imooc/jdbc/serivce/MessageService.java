package com.imooc.jdbc.serivce;

import com.imooc.jdbc.bean.Message;
import com.imooc.jdbc.dao.MessageDao;

import java.util.Date;
import java.util.List;

public class MessageService {
    private MessageDao messageDao;
    public MessageService(){
        messageDao=new MessageDao();
    }

    public  boolean addMessage(Message message){
        message.setCreateTime(new Date());
        return messageDao.save(message);

    }
    public boolean  DelMessage(Long messageid){
        return messageDao.DelMessage(messageid);
    }

    public  boolean ReWhiteMessage(String title,String content,Long messageid){
        return  messageDao.ReWhiteMessage(title,content,messageid);
    }
    public List<Message> getMessages(int page,int pageSize) {
        return messageDao.getMessages (page,pageSize);
    }
    public List<Message> getMyMessages(Long userid,int page,int pageSize) {
        return messageDao.getMyMessages (userid, page,pageSize);
    }
    public Message getMyOneMessages(Long messageid){
        return messageDao.getMyOneMessages(messageid);
    }
    public int countMessages(){
        return messageDao.countMessages();
    }
}
