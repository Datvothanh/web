package com.ute.web.controllers;

import com.ute.web.beans.Category;
import com.ute.web.beans.GroupCategory;
import com.ute.web.models.CategoryModel;
import com.ute.web.models.GroupCategoryModel;
import com.ute.web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminGroupCategoryServlet", value = "/Admin/GroupCategory/*")
public class AdminGroupCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }

        switch (path) {
            case "/Index":
                List<GroupCategory> list = GroupCategoryModel.findAll();
                request.setAttribute("groupCategories", list);
                ServletUtils.forward("/views/vwGroupCategory/Index.jsp", request, response);
                break;
            case "/Add":
                ServletUtils.forward("/views/vwGroupCategory/Add.jsp", request, response);
                break;
            case "/Edit":
                int id = Integer.parseInt(request.getParameter("id"));
                GroupCategory g = GroupCategoryModel.findById(id);
                if (g != null) {
                    request.setAttribute("groupCategory", g);
                    ServletUtils.forward("/views/vwGroupCategory/Edit.jsp", request, response);
                } else {
//                    ServletUtils.redirect("Admin/GroupCategory/" , request , response);//Đưa về lại trang mà mình muốn
                    ServletUtils.forward("/views/204.jsp", request, response);
                }
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String path = request.getPathInfo();
        switch (path) {
            case "/Add":
                addGroupCategory(request, response);
                break;
            case "/Delete":
                deleteGroupCategory(request, response);
                break;
            case "/Update":
                updateGroupCategory(request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    private void addGroupCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("GroupCatName");
        GroupCategory g = new GroupCategory( name );
        GroupCategoryModel.add(g);
//        ServletUtils.forward("/views/vwGroupCategory/Index.jsp", request, response);
        ServletUtils.redirect("/Admin/GroupCategory", request, response);
    }

    private void updateGroupCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("GroupCatID"));
        String name = request.getParameter("GroupCatName");
        GroupCategory c = new GroupCategory(id , name);
        GroupCategoryModel.update(c);
        ServletUtils.redirect("/Admin/GroupCategory", request, response);
    }

    private void deleteGroupCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("GroupCatID"));
        GroupCategoryModel.delete(id);
        ServletUtils.redirect("/Admin/GroupCategory", request, response);
    }
}