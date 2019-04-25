package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession httpSession = req.getSession();
        Cart currentCart = (Cart) httpSession.getAttribute("cart");
        Order currentOrder;
        if (currentCart.productsInCart.isEmpty()) {
            resp.sendRedirect("shopping-cart");
        } else {
            if (httpSession.getAttribute("order") == null) {
                currentOrder = new Order(currentCart.productsInCart);
                httpSession.setAttribute("order", currentOrder);
            } else {
                currentOrder = (Order) httpSession.getAttribute("order");
            }

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("cartSum", currentCart.sumOfCart);
            context.setVariable("cartTotal", currentCart.getItemsTotal());
            context.setVariable("order", currentOrder.getItemList());
            context.setVariable("currentOrder", currentOrder);
            setEncoding(resp);
            engine.process("product/checkout.html", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDao orderDataStore = OrderDaoMem.getInstance();
        HttpSession httpSession = req.getSession();
        Order currentOrder = (Order) httpSession.getAttribute("order");
        currentOrder.saveData(req.getParameter("billing"), req.getParameter("shipping"), req.getParameter("phone"), req.getParameter("name"), req.getParameter("email"));
        httpSession.setAttribute("order", currentOrder);
        orderDataStore.add(currentOrder);
        setEncoding(resp);
        resp.sendRedirect("/checkout");

    }

    private void setEncoding(HttpServletResponse resp) {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
    }
}

