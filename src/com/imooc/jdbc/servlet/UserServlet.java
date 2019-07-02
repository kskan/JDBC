package com.imooc.jdbc.servlet;

import com.imooc.jdbc.bean.User;
import com.imooc.jdbc.serivce.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

@WebServlet(urlPatterns = {"/userInfo.do","/editUserPrompt.do","/editUser.do"})
public class UserServlet  extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService=new UserService();
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String pathname=request.getServletPath();
         if(Objects.equals("/userInfo.do",pathname)){
             request.getRequestDispatcher("/WEB-INF/views/biz/user.jsp").forward(request,response);
         }else if(Objects.equals("/editUserPrompt.do",pathname)){
             Long id=Long.valueOf(request.getParameter("id"));
             User user=userService.getUserById(id);
             if(user!=null) {
                 request.setAttribute("user",user);
                 request.getRequestDispatcher("/WEB-INF/views/biz/edit_user.jsp").forward(request, response);
             }else {
                 request.getRequestDispatcher("/WEB-INF/views/biz/user.jsp").forward(request,response);
             }

         }else if(Objects.equals("/editUser.do",pathname)){
             Long id=Long.valueOf(request.getParameter("id"));
             String name=request.getParameter("name");
             String password=request.getParameter("password");
             String realName=request.getParameter("realName");
             String birthday=request.getParameter("birthday");
             String phone=request.getParameter("phone");
             String address=request.getParameter("address");
             User user=new User();
             user.setId(id);
             user.setName(name);
             user.setPassword(password);
             user.setRealname(realName);
             try {
                 user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
             } catch (ParseException e) {
                 System.out.println("格式化birthday失败");
                 e.printStackTrace();
             }
             user.setPhone(phone);
             user.setAddress(address);
             boolean result=userService.updateUser(user);
             if(result){
                 user = userService.getUserById(user.getId());
                 request.getSession().setAttribute("user",user);
                 request.setAttribute("user",user);
                 request.getRequestDispatcher("/WEB-INF/views/biz/user.jsp").forward(request, response);
             }else {
                 request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
             }

         }else {
             request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
         }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
