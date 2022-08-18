package com.ute.web.controllers;
import com.ute.web.beans.GroupCategory;
import com.ute.web.beans.Product;
import com.ute.web.beans.User;
import com.ute.web.models.GroupCategoryModel;
import com.ute.web.models.ProductModel;
import com.ute.web.models.UserModel;
import com.ute.web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "AdminPermissionServlet", value = "/Admin/Permission/*")
public class AdminPermissionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }
        switch (path) {
            case "/Index":
                List<User> userList = UserModel.findAll();
                request.setAttribute("userList" , userList);
                ServletUtils.forward("/views/vwAccount/Permission.jsp", request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");

    }


}