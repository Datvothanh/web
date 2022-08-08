package com.ute.web.controllers;


import com.ute.web.utils.ServletUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MiscServlet", value = "/Misc/*")
public class MiscServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/404";
        }
        switch (path) {
            case "/Editor":
                ServletUtils.forward("/views/vwMisc/Editor.jsp", request, response);
                break;
            case "/Upload":
                ServletUtils.forward("/views/vwMisc/Upload.jsp", request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String path = request.getPathInfo();
        switch (path) {
            case "/Editor":
                postEditor(request, response);
                break;
            case "/Upload":
                postUpload(request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    private void postUpload(HttpServletRequest request, HttpServletResponse response) {
    }

    private void postEditor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tinyDes = request.getParameter("TinyDes");
        System.out.println(tinyDes);
        String desc = request.getParameter("FullDes");
        System.out.println(desc);
        ServletUtils.forward("/views/vwMisc/Editor.jsp", request, response);
    }


}