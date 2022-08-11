package com.ute.web.controllers;

import com.ute.web.beans.Favourite;
import com.ute.web.beans.Product;
import com.ute.web.models.FavouriteModel;
import com.ute.web.models.ProductModel;
import com.ute.web.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductFEServlet", value = "/Product/*")
public class ProductFEServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/404";
        }
        switch (path) {
            case "/ByCat":
                int catId = Integer.parseInt(request.getParameter("id"));
                List<Product> list = ProductModel.findByCatID(catId);
                List<Favourite> listFavourite = FavouriteModel.findAll();
                request.setAttribute("products", list);
                request.setAttribute("favourite", listFavourite);
                ServletUtils.forward("/views/vwProduct/ByCat.jsp", request, response);
                break;
            case "/Detail":
                int proId = Integer.parseInt(request.getParameter("id"));
                Product product = ProductModel.findById(proId);
                if (product == null) {
                    ServletUtils.redirect("/Home", request, response);
                } else {
                    request.setAttribute("product", product);
                    List<Favourite> ListFavourite = FavouriteModel.findAll();
                    request.setAttribute("favourite", ListFavourite);
                    ServletUtils.forward("/views/vwProduct/Detail.jsp", request, response);
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
            case "/Favourite":
                favourite(request, response);
                break;
            case "/FavouriteEdit":
                favouriteEdit(request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    private void favouriteEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        int idPro = Integer.parseInt(request.getParameter("idPro"));
        int favourite = Integer.parseInt(request.getParameter("favourite"));
        int idFa = Integer.parseInt(request.getParameter("idFa"));
        Favourite Favourite = new Favourite(idFa ,idUser , idPro , favourite);
        FavouriteModel.update(Favourite);
        String url = request.getHeader("referer");
        ServletUtils.redirect(url, request, response);
    }

    private void favourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        int idPro = Integer.parseInt(request.getParameter("idPro"));
        int favourite = Integer.parseInt(request.getParameter("favourite"));
        Favourite Favourite = new Favourite(idUser , idPro , favourite);
        FavouriteModel.add(Favourite);
        String url = request.getHeader("referer");
        ServletUtils.redirect(url, request, response);
    }

   

}
