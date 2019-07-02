package com.imooc.jdbc.servlet;

import com.imooc.jdbc.bean.User;
import com.imooc.jdbc.serivce.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register.do")
public class RegisterDoServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        userService=new UserService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.service(request, response);
      String username =  request.getParameter("username");
      String password =  request.getParameter("password");
      String repassword = request.getParameter("repassword");
      String message="";
        if(!password.equals(repassword))message="前后密码输入不正确";
        if(!password.matches("[A-Za-z_0-9]{6,}"))message="密码输入不正确";
        if (!username.matches("[A-Za-z]{4,}")) message="用户名输入不正确";
      if(message==null||message.equals("")) {
          boolean result = userService.register(username, password);
          if (result) {
              request.getRequestDispatcher("/login.do").forward(request, response);
          } else {
              request.getRequestDispatcher("/regPrompt.do").forward(request, response);
          }
      }
      else{
          request.getSession().setAttribute("message",message);
          request.getRequestDispatcher("/regPrompt.do").forward(request, response);

      }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
