package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
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

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order currentOrder = getCurrentOrder(req);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("order", currentOrder);
        engine.process("product/payment.html", context, resp.getWriter());
        System.out.println(currentOrder.toString());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order currentOrder = getCurrentOrder(req);
        currentOrder.setMethod(req.getParameter("method"));
        if (req.getParameter("method") == "card") {
            currentOrder.paymentMethod(req.getParameter("payment"));
        } else if (req.getParameter("method") == "coin") {
            currentOrder.paymentMethod(req.getParameter("payment"));
        } else if (req.getParameter("method") == "craft") {
            currentOrder.paymentMethod(req.getParameter("payment"));
        }
        resp.sendRedirect("/confirmation");
    }

    private Order getCurrentOrder(HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        return (Order) httpSession.getAttribute("order");
    }
}

