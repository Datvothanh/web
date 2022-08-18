package com.ute.web.filters;


import com.ute.web.beans.User;
import com.ute.web.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "AuthAddProductFilter")
public class AuthAddProductFilter implements Filter {
    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        boolean auth = (boolean) session.getAttribute("auth");
        User user = (User) session.getAttribute("authUser");
        int permission = user.getPermission();
        if(permission != 0 && permission != 1 || !auth){
            session.setAttribute("retUrl" , request.getRequestURI());
            ServletUtils.redirect("/Account/Login" , request , (HttpServletResponse) res);
            return;
        }
        chain.doFilter(req, res);
    }
}
