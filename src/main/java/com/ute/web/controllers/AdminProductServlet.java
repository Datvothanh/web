package com.ute.web.controllers;
import com.ute.web.beans.Product;
import com.ute.web.models.ProductModel;
import com.ute.web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminProductServlet", value = "/Admin/Product/*")
public class AdminProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }

        switch (path) {
            case "/Index":
                List<Product> list = ProductModel.findAll();
                request.setAttribute("products", list);
                ServletUtils.forward("/views/vwProduct/Index.jsp", request, response);
                break;
            case "/Add":
                ServletUtils.forward("/views/vwProduct/Add.jsp", request, response);
                break;
            case "/Edit":
                int id = Integer.parseInt(request.getParameter("id"));
                Product p = ProductModel.findById(id);
                if (p != null) {
                    request.setAttribute("product", p);
                    ServletUtils.forward("/views/vwProduct/Edit.jsp", request, response);
                } else {
//                    ServletUtils.redirect("Admin/Product/" , request , response);//Đưa về lại trang mà mình muốn
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
        response.setCharacterEncoding("UTF-8");
        String path = request.getPathInfo();
        switch (path) {
            case "/Add":
                addProduct(request, response);
                break;
            case "/Delete":
                deleteProduct(request, response);
                break;
            case "/Update":
                updateProduct(request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("ProName");
        int price = Integer.parseInt(request.getParameter("Price"));
        int quantity = Integer.parseInt(request.getParameter("Quantity"));
        int type = Integer.parseInt(request.getParameter("CatID"));
        String tinyDes = request.getParameter("TinyDes");
        String fullDes = request.getParameter("FullDes");
        Product p = new Product(price , type , quantity , name , tinyDes, fullDes );
        ProductModel.add(p);
//        ServletUtils.forward("/views/vwProduct/Index.jsp", request, response);
        ServletUtils.redirect("/Admin/Product", request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int type = Integer.parseInt(request.getParameter("CatID"));
        int price = Integer.parseInt(request.getParameter("Price"));
        int quantity = Integer.parseInt(request.getParameter("Quantity"));
        int id = Integer.parseInt(request.getParameter("ProID"));
        String name = request.getParameter("ProName");
        String tinyDes = request.getParameter("TinyDes");
        String fullDes = request.getParameter("FullDes");
        Product p = new Product(id , price , type, quantity , name , tinyDes , fullDes);
        ProductModel.update(p);
        ServletUtils.redirect("/Admin/Product", request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ProID"));
        ProductModel.delete(id);
        ServletUtils.redirect("/Admin/Product", request, response);
    }
}