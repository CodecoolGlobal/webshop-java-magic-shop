package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        //setting up a new product category
        ProductCategory spell = new ProductCategory("Spell", "Sorcery", "An invocation of magic, or a curse, that may help the caster in various ways. ");
        ProductCategory potion = new ProductCategory("Potions", "Alchemy", "A finely brewed liquid that may be a remedy, or provide some form of enhancement of the mind or body.");
        ProductCategory poison = new ProductCategory("Poisons", "Alchemy", "A vile liquid that affects its victim's health, it may be deadly, or paralyzing, or any other form of malice.");
        ProductCategory scroll = new ProductCategory("Books", "Sorcery", "A document of wizardry, describing either a spell, curse, recipe, or anything related to magic.");
        ProductCategory weapon = new ProductCategory("Weapons", "Armament", "An enchanted or magical tool, mostly used by people for the intent of murder, dismemberment, crippling, or other form of vileness.");
        ProductCategory armor = new ProductCategory("Armors", "Armament", "A piece or set of gear, used for protection against one's acquaintance with intent not so pure..");



        productCategoryDataStore.add(spell);
        productCategoryDataStore.add(poison);
        productCategoryDataStore.add(potion);
        productCategoryDataStore.add(scroll);
        productCategoryDataStore.add(weapon);
        productCategoryDataStore.add(armor);


        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", potion, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", spell, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", spell, amazon));
    }
}
