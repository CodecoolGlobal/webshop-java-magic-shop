package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/shopping-cart"})
public class CartController extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        Cart currentCart = (Cart) httpSession.getAttribute("cart");

        if (currentCart != null) {
            logger.info("Content of cart: " + currentCart.toString());
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("products", currentCart.getProductsInCart());
            engine.process("product/shopping-cart.html", context, resp.getWriter());
        } else {
            resp.sendRedirect("/");
        }
    }
}


