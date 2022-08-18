package com.ute.web.controllers;

import com.ute.web.beans.Category;
import com.ute.web.beans.GroupCategory;
import com.ute.web.beans.Product;
import com.ute.web.beans.User;
import com.ute.web.models.CategoryModel;
import com.ute.web.models.GroupCategoryModel;
import com.ute.web.models.ProductModel;
import com.ute.web.models.UserModel;
import com.ute.web.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AdminCategoryServlet", value = "/Admin/Category/*")
public class AdminCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }

        switch (path) {
            case "/Index":
                List<Category> list = CategoryModel.findAll();
                request.setAttribute("categories", list);
                List<GroupCategory> listGr = GroupCategoryModel.findAll();
                request.setAttribute("groupCategories", listGr);
                ServletUtils.forward("/views/vwCategory/Index.jsp", request, response);
                break;
            case "/Add":
                ServletUtils.forward("/views/vwCategory/Add.jsp", request, response);
                break;
            case "/Edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Category c = CategoryModel.findById(id);
                if (c != null) {
                    request.setAttribute("category", c);
                    ServletUtils.forward("/views/vwCategory/Edit.jsp", request, response);
                } else {
//                    ServletUtils.redirect("Admin/Category/" , request , response);//Đưa về lại trang mà mình muốn
                    ServletUtils.forward("/views/204.jsp", request, response);
                }
                break;
            case "/IsAvailable":
                int catID = Integer.parseInt(request.getParameter("catID"));
                List<Product> listProduct = ProductModel.findByCatID(catID);
                boolean isAvailable = (listProduct.size() == 0);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                out.print(isAvailable);
                out.flush();
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
                addCategory(request, response);
                break;
            case "/Delete":
                deleteCategory(request, response);
                break;
            case "/Update":
                updateCategory(request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupCategoryID = Integer.parseInt(request.getParameter("GroupCategoryID"));
        String name = request.getParameter("CatName");
        Category c = new Category( groupCategoryID , name );
        CategoryModel.add(c);
//        ServletUtils.forward("/views/vwCategory/Index.jsp", request, response);
        ServletUtils.redirect("/Admin/Category", request, response);
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("CatID"));
        int groupCategoryID = Integer.parseInt(request.getParameter("GroupCategoryID"));
        String name = request.getParameter("CatName");
        Category c = new Category(id, groupCategoryID , name);
        CategoryModel.update(c);
        ServletUtils.redirect("/Admin/Category", request, response);
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("CatID"));
        CategoryModel.delete(id);
        ServletUtils.redirect("/Admin/Category", request, response);
    }
}