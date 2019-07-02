package com.imooc.jdbc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPromptServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.service(req, resp);
        request.getRequestDispatcher("/WEB-INF/views/biz/login.jsp").forward(request,response);

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
