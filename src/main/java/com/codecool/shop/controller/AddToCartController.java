package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart/add"})
public class AddToCartController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        ProductDao productDataStore = ProductDaoMem.getInstance();

        int id = Integer.parseInt(req.getParameter("cartButton"));
        Product chosenProduct = productDataStore.find(id);

        Cart cart = (Cart) httpSession.getAttribute("cart");
        boolean contains = false;

        for (LineItem lineItem : cart.productsInCart) {
            if (lineItem.product == chosenProduct) {
                contains = true;
                lineItem.quantity++;
            }
        }
        if (!contains) {
            LineItem chosen = new LineItem(chosenProduct, 1);
            cart.addToCart(chosen);
        }


        resp.sendRedirect((String) httpSession.getAttribute("lasturl").toString());

    }
}
