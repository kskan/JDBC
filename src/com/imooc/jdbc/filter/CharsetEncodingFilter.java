package com.imooc.jdbc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CharsetEncodingFilter")
public class CharsetEncodingFilter implements Filter {

    private String encoding;

    public void destroy() {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
//        chain.doFilter(req, resp);
        servletRequest.setCharacterEncoding(encoding);
        chain.doFilter(servletRequest,servletResponse);
    }

    public void init(FilterConfig config) throws ServletException {
        this.encoding =config.getInitParameter("encoding");

    }

}
