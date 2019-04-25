package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.implementation.jdbc.ProductCategoryDaoDB;
import com.codecool.shop.dao.implementation.jdbc.ProductDaoDB;
import com.codecool.shop.dao.implementation.jdbc.SupplierDaoDB;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoDB.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoDB.getInstance();
        SupplierDao supplierDao = SupplierDaoDB.getInstance();

        HttpSession session = req.getSession();
        Cart sessionCart = Cart.getInstance();
        session.setAttribute("cart", sessionCart);


//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        int categoryId = 1;
        int supplierId = 0;
        if (req.getParameter("category") != null) {
            categoryId = Integer.parseInt(req.getParameter("category"));
            session.setAttribute("lasturl","/?category="+req.getParameter("category"));
        }
//        context.setVariables(params);
        context.setVariable("cartTotal", sessionCart.getItemsTotal());
        if (req.getParameter("supplier") != null) {
            supplierId = Integer.parseInt(req.getParameter("supplier"));
            session.setAttribute("lasturl","/?supplier="+req.getParameter("supplier"));

            categoryId = 0;
        }
        if (categoryId != 0) {
            context.setVariable("category", productCategoryDataStore.find(categoryId));
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(categoryId)));
        } else {
            context.setVariable("category", supplierDao.find(supplierId));
            context.setVariable("products", productDataStore.getBy(supplierDao.find(supplierId)));
        }

        engine.process("product/index.html", context, resp.getWriter());
    }

}
