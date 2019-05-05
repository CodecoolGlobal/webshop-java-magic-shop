package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.jdbc.ProductDaoDB;
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
        ProductDao productDataStore = ProductDaoDB.getInstance();

        int id = Integer.parseInt(req.getParameter("cartButton"));
        Product chosenProduct = productDataStore.find(id);

        Cart cart = (Cart) httpSession.getAttribute("cart");
        if (cart != null) {
            boolean contains = false;

            for (LineItem lineItem : cart.productsInCart) {
                if (lineItem.getProduct().getId() == chosenProduct.getId()) {
                    contains = true;
                    lineItem.setQuantity(lineItem.getQuantity() + 1);
                    lineItem.setAddUpPrice(lineItem.getQuantity());
                    cart.setItemsTotal(1);
                }
            }
            if (!contains) {
                LineItem chosen = new LineItem(chosenProduct, 1);
                cart.addToCart(chosen);
            }
        }
    if (httpSession.getAttribute("lasturl") != null) {
        resp.sendRedirect(httpSession.getAttribute("lasturl").toString());
    } else {
        resp.sendRedirect("/");
    }
    }
}
