package com.works.utils;

import com.works.entities.User;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {


    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        System.out.println("Server Up");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");


        // add paramter
        boolean status = req.getSession().getAttribute("user") != null;
        if ( status ) {
            User us = (User) req.getSession().getAttribute("user");
            req.setAttribute("us", us);
        }


        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        System.out.println("Server Down");
    }
}
