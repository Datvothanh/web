package com.ute.web.controllers;




import com.ute.web.beans.Auction;
import com.ute.web.beans.Favourite;
import com.ute.web.beans.Product;
import com.ute.web.beans.User;
import com.ute.web.models.AuctionModel;
import com.ute.web.models.FavouriteModel;
import com.ute.web.models.ProductModel;
import com.ute.web.models.UserModel;
import com.ute.web.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import static com.ute.web.tools.mask.maskString;

@WebServlet(name = "HomeServlet", value = "/Home/*")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }

        switch (path) {
            case "/Index":
                List<Product> listTop5HighestPrice = ProductModel.findTop5HighestPrice();
                List<Product> listTop5HighestCountAuction = ProductModel.findTop5HighestCountAuction();
                List<Favourite> listFavourite = FavouriteModel.findAll();
                List<User> User = UserModel.findAll();
                List<Auction> Auction = AuctionModel.findAll();
                for (com.ute.web.beans.User value : User) {
                    try {
                        value.setName(maskString(value.getName(), 0, 4, '*'));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                request.setAttribute("user", User);
                request.setAttribute("favourite", listFavourite);
                request.setAttribute("auction", Auction);
                request.setAttribute("listTop5HighestCountAuction", listTop5HighestCountAuction);
                request.setAttribute("listTop5HighestPrice", listTop5HighestPrice);
                ServletUtils.forward("/views/vwHome/Index.jsp", request, response);
                break;
            case "/About":
                ServletUtils.forward("/views/vwHome/About.jsp", request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
