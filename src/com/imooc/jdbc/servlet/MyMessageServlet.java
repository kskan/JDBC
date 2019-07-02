package com.imooc.jdbc.servlet;


import com.imooc.jdbc.bean.Message;
import com.imooc.jdbc.bean.User;
import com.imooc.jdbc.serivce.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = {"/reMyMessage.do","/reMyMessagePrompt.do","/delMyMessagePrompt.do"})
public class MyMessageServlet extends HttpServlet {

    private MessageService messageService;

    @Override
    public void init() throws ServletException {

        super.init();
        messageService= new  MessageService();
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathname=request.getServletPath();
        if(Objects.equals("/reMyMessagePrompt.do",pathname)){
            Long messageid = Long.valueOf(request.getParameter("messageid"));
            Message message=messageService.getMyOneMessages(messageid);
            request.getSession().setAttribute("message",message);
            request.getRequestDispatcher("/WEB-INF/views/biz/rewhite_message.jsp").forward(request,response);
        }else if(Objects.equals("/reMyMessage.do",pathname)){
            User user=(User) request.getSession().getAttribute("user");
            if(null==user){
            request.getRequestDispatcher("/message/list.do").forward(request,response);
            }else {
                String title=request.getParameter("title");
                String content=request.getParameter("content");
                Long messageid=Long.valueOf( request.getParameter("messageid"));
                boolean result=messageService.ReWhiteMessage(title,content,messageid);
                if (result) {
                    request.getRequestDispatcher("/message/list.do").forward(request, response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/views/biz/rewhite_message.jsp").forward(request, response);
                }
            }
        }else if(Objects.equals("/delMyMessagePrompt.do",pathname)){
            Long messageid =Long.valueOf(request.getParameter("messageid"));
            boolean result=messageService.DelMessage(messageid);
            if(result){
                request.getRequestDispatcher("/my/message/list.do").forward(request,response);
            }else {
                request.getRequestDispatcher("/my/message/list.do").forward(request,response);
            }


        } else {
            request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request,response);
        }

    }
}
