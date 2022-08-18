package com.ute.web.controllers;

import com.ute.web.beans.*;
import com.ute.web.models.*;
import com.ute.web.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.ute.web.tools.mask.maskString;

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
                List<User> User = UserModel.findAll();
                List<Auction> Auction = AuctionModel.findAll();
                Category category = CategoryModel.findById(catId);
                for (com.ute.web.beans.User value : User) {
                    try {
                        value.setName(maskString(value.getName(), 0, 4, '*'));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                request.setAttribute("user", User);
                request.setAttribute("category", category);
                request.setAttribute("products", list);
                request.setAttribute("favourite", listFavourite);
                request.setAttribute("auction", Auction);
                ServletUtils.forward("/views/vwProduct/ByCat.jsp", request, response);
                break;
            case "/Detail":
                int proId = Integer.parseInt(request.getParameter("id"));
                Product product = ProductModel.findById(proId);
                if (product == null) {
                    ServletUtils.redirect("/Home", request, response);
                } else {
                    request.setAttribute("product", product);
                    List<Auction> TopAuctionHighestPrice = AuctionModel.findTopHighestPrice();
                    request.setAttribute("TopAuctionHighestPrice", TopAuctionHighestPrice);
                    List<Favourite> ListFavourite = FavouriteModel.findAll();
                    request.setAttribute("favourite", ListFavourite);
                    List<Product> proCat5 = ProductModel.findByCatID5(product.getCatID(), proId);
                    request.setAttribute("proCat5", proCat5);
                    List<BanBidder> BanBidder = BanBidderModel.findAll();
                    request.setAttribute("BanBidder", BanBidder);
                    List<User> user = UserModel.findAll();
                    for (com.ute.web.beans.User value : user) {
                        try {
                            value.setName(maskString(value.getName(), 0, 4, '*'));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    request.setAttribute("user", user);
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
            case "/BanBidder":
                banBidder(request, response);
                break;
            case "/Favourite":
                favourite(request, response);
                break;
            case "/FavouriteEdit":
                favouriteEdit(request, response);
                break;
            case "/Auction":
                Auction(request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    private void banBidder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        int idPro = Integer.parseInt(request.getParameter("idPro"));
        int idAu = Integer.parseInt(request.getParameter("idAu"));
        int countAuction = Integer.parseInt(request.getParameter("countAuction"));
        int idUserHold = Integer.parseInt(request.getParameter("idUserHold"));

        if (idUserHold == idUser) {
            if (AuctionModel.findSizeProID(idPro) == 1) {
                Product p = new Product(idPro, 0, -1, 0);
                ProductModel.updateHighestPaidPrice(p);
            } else {
                Auction auctionSecond = AuctionModel.findSecondHighestPrice(idPro);
                Product p = new Product(idPro, auctionSecond.getPrice() , auctionSecond.getUserID(), countAuction-1);
                ProductModel.updateHighestPaidPrice(p);
            }
        } else {
            Product p = new Product(idPro, countAuction-1);
            ProductModel.updateCountAuction(p);
        }
        AuctionModel.delete(idAu);
        BanBidder banBidder = new BanBidder(idUser, idPro);
        BanBidderModel.add(banBidder);
        String url = request.getHeader("referer");
        ServletUtils.redirect(url, request, response);
    }

    private void Auction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        int idPro = Integer.parseInt(request.getParameter("idPro"));
        int price = Integer.parseInt(request.getParameter("price"));
        int countAuction = Integer.parseInt(request.getParameter("countAuction"));
        List<Auction> Auction = AuctionModel.findAll();
        request.setAttribute("auction", Auction);
        if (AuctionModel.findUserIDProID(idUser, idPro) == -1) {
            Auction auction = new Auction(idUser, idPro, price);
            AuctionModel.add(auction);
            int CountAuction = countAuction + 1;
            Product p = new Product(idPro, price, idUser, CountAuction);
            ProductModel.updateHighestPaidPrice(p);
        } else {
            int idAu = AuctionModel.findUserIDProID(idUser, idPro);
            Auction auction = new Auction(idAu, idUser, idPro, price);
            AuctionModel.update(auction);
            Product p = new Product(idPro, price, idUser, countAuction);
            ProductModel.updateHighestPaidPrice(p);
        }

        String url = request.getHeader("referer");
        ServletUtils.redirect(url, request, response);
    }

    private void favouriteEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        int idPro = Integer.parseInt(request.getParameter("idPro"));
        int favourite = Integer.parseInt(request.getParameter("favourite"));
        int idFa = Integer.parseInt(request.getParameter("idFa"));
        Favourite Favourite = new Favourite(idFa, idUser, idPro, favourite);
        FavouriteModel.update(Favourite);
        String url = request.getHeader("referer");
        ServletUtils.redirect(url, request, response);
    }

    private void favourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        int idPro = Integer.parseInt(request.getParameter("idPro"));
        int favourite = Integer.parseInt(request.getParameter("favourite"));
        Favourite Favourite = new Favourite(idUser, idPro, favourite);
        FavouriteModel.add(Favourite);
        String url = request.getHeader("referer");
        ServletUtils.redirect(url, request, response);
    }
}
