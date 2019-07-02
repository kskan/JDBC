package com.imooc.jdbc.serivce;

import com.imooc.jdbc.bean.User;
import com.imooc.jdbc.dao.UserDao;

public class UserService {
    private UserDao userDao;
    public UserService(){
        userDao=new UserDao();

    }
    public User login(String username, String password){
        return  userDao.login(username,password);
    }
    public User getUserById(Long id){return userDao.getUserById(id);}
    public boolean updateUser(User user){
        return userDao.updateUser(user);
    }
    public boolean register(String username,String password){
        return userDao.register(username, password);

    }

}
