package com.ute.web.controllers;
import com.ute.web.beans.Product;
import com.ute.web.models.ProductModel;
import com.ute.web.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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

@MultipartConfig(
        fileSizeThreshold = 2 * 1024 * 1024,
        maxFileSize = 50 * 1024 * 1024,
        maxRequestSize = 50 * 1024 * 1024
)
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
                Product ProEnd = ProductModel.ProEnd();
                request.setAttribute("proEnd", ProEnd);
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
        String proID = request.getParameter("ProID");
        int userSellID = Integer.parseInt(request.getParameter("UserSellID"));
        int startingPrice = Integer.parseInt(request.getParameter("StartingPrice"));
        int stepPrice = Integer.parseInt(request.getParameter("StepPrice"));
        int nowPrice = Integer.parseInt(request.getParameter("NowPrice"));
        int type = Integer.parseInt(request.getParameter("CatID"));
        int autoExtend = Integer.parseInt(request.getParameter("AutoExtend"));
        int highestPaidPrice = 0;
        int sell = 0;
        int userID = -1;
        int countAuction = 0;
        String tinyDes = request.getParameter("TinyDes");
        String fullDes = request.getParameter("FullDes");
        String strSD = request.getParameter("StartDay");
        String strED = request.getParameter("EndDay");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDay = LocalDate.parse(strSD, df);
        LocalDate endDay = LocalDate.parse(strED, df);
        Product p = new Product(startingPrice , type , stepPrice, highestPaidPrice ,nowPrice, autoExtend ,userID , sell ,countAuction,userSellID,name , tinyDes, fullDes, startDay, endDay );
        ProductModel.add(p);
        Part partMain = request.getPart("ImageMain");
        Part partSub1 = request.getPart("ImageSub1");
        Part partSub2 = request.getPart("ImageSub2");
        Part partSub3 = request.getPart("ImageSub3");
        String realpath = "/public/imgs/sp/";
        String realPathAll = realpath.concat(proID);
        String realPath = this.getServletContext().getRealPath(realPathAll);
        if (!Files.exists(Path.of(realPath))) {
            Files.createDirectory(Path.of(realPath));
            partMain.write(realPath + "/" + "main.jpg");
            partSub1.write(realPath + "/" + "sub1.jpg");
            partSub2.write(realPath + "/" + "sub2.jpg");
            partSub3.write(realPath + "/" + "sub3.jpg");
        } else {
            partMain.write(realPath + "/" + "main.jpg");
            partSub1.write(realPath + "/" + "sub1.jpg");
            partSub2.write(realPath + "/" + "sub2.jpg");
            partSub3.write(realPath + "/" + "sub3.jpg");
        }
        ServletUtils.redirect("/Admin/Product", request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int type = Integer.parseInt(request.getParameter("CatID"));
        int startingPrice = Integer.parseInt(request.getParameter("StartingPrice"));
        int stepPrice = Integer.parseInt(request.getParameter("StepPrice"));
        int highestPaidPrice = Integer.parseInt(request.getParameter("HighestPaidPrice"));
        int nowPrice = Integer.parseInt(request.getParameter("NowPrice"));
        int id = Integer.parseInt(request.getParameter("ProID"));
        int autoExtend = Integer.parseInt(request.getParameter("AutoExtend"));
        String name = request.getParameter("ProName");
        String tinyDes = request.getParameter("TinyDes");
        String fullDes = request.getParameter("FullDes");
        String strSD = request.getParameter("StartDay");
        String strED = request.getParameter("EndDay");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDate startDay = LocalDate.parse(strSD, df);
        LocalDate endDay = LocalDate.parse(strED, df);
        Product p = new Product(id,startingPrice , type , stepPrice, highestPaidPrice ,nowPrice, autoExtend , name , tinyDes, fullDes, startDay, endDay);
        ProductModel.update(p);
        ServletUtils.redirect("/Admin/Product", request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ProID"));
        ProductModel.delete(id);
        ServletUtils.redirect("/Admin/Product", request, response);
    }
}