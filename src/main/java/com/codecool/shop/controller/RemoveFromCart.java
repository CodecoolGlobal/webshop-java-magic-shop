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

@WebServlet(urlPatterns = {"/cart/remove"})
public class RemoveFromCart extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        ProductDao productDataStore = ProductDaoMem.getInstance();

        int id = Integer.parseInt(req.getParameter("cartButton"));
        Product chosenProduct = productDataStore.find(id);

        Cart cart = (Cart) httpSession.getAttribute("cart");
        boolean contains = true;

        for (int i=0; i< cart.productsInCart.size(); i++) {
            if (cart.productsInCart.get(i).getProduct() == chosenProduct) {
                contains = false;
                cart.removeFromCart(cart.productsInCart.get(i));
                cart.sumOfCart -= chosenProduct.getDefaultPrice();
            }
        }


        resp.sendRedirect("/shopping-cart");

    }
}
