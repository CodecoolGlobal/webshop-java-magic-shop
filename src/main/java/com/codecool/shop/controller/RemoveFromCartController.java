package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.jdbc.ProductDaoDB;
import com.codecool.shop.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart/remove"})
public class RemoveFromCartController extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(RemoveFromCartController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        ProductDao productDataStore = ProductDaoDB.getInstance();

        int id = Integer.parseInt(req.getParameter("cartButton"));
        Product chosenProduct = productDataStore.find(id);

        Cart cart = (Cart) httpSession.getAttribute("cart");

        for (int i=0; i< cart.productsInCart.size(); i++) {
            if (cart.productsInCart.get(i).getProduct().getId() == chosenProduct.getId()) {
                cart.setItemsTotal(-cart.productsInCart.get(i).getQuantity());
                cart.sumOfCart -= chosenProduct.getDefaultPrice();
                cart.removeFromCart(cart.productsInCart.get(i));
                logger.info(cart.productsInCart.get(i) + " was removed from cart.");

            }
        }


        resp.sendRedirect("/shopping-cart");

    }
}
