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
import java.util.List;
/*
 * 消息列表Servlet
 *
 */
@WebServlet("/my/message/list.do")

public class MyMessageListServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }
    MessageService messageService=null;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageStr = request.getParameter("page");//当前页码
        messageService = new MessageService();
        int page = 1;//页码默认值为1
        if (null != pageStr && (!"".equals(pageStr))) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        User user=(User)request.getSession().getAttribute("user");
        List<Message> messages = messageService.getMyMessages(user.getId(),page, 5);
        int count = messageService.countMessages();
        int last = count % 5 == 0 ? (count / 5) : ((count / 5) + 1);
        request.setAttribute("last", last);
        request.setAttribute("messages",messages);
        request.setAttribute("page",page);
        request.getRequestDispatcher("/WEB-INF/views/biz/mymessage_list.jsp").forward(request,response);

    }

    @Override
    public void destroy() {
        super.destroy();
        messageService=null;
    }
}
