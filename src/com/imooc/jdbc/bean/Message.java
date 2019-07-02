package com.imooc.jdbc.bean;

import java.sql.Timestamp;
import java.util.Date;

public class Message {
    private long id ;
    private long userid;
    private  String username;
    private  String title;
    private  String content;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Message(long id, long userid, String username, String title, String content, Date createTime) {
         this.id = id;
         this.userid = userid;
         this.username = username;
         this.title = title;
         this.content = content;
         this.createTime = createTime;
         }
    public Message( long userid, String username, String title, String content) {
        this.userid = userid;
        this.username = username;
        this.title = title;
        this.content = content;

    }

public Message(long id, long user_id, String username, String content, Timestamp create_time) {
        }

@Override
public String toString() {
        return "Message{" +
        "id=" + id +
        ", userid=" + userid +
        ", username='" + username + '\'' +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", createTime='" + createTime + '\'' +
        '}';
        }
        }

