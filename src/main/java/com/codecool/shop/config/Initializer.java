package com.codecool.shop.config;

import com.codecool.shop.controller.Cart;
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
import javax.servlet.http.HttpSession;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();


        //setting up a new supplier
        Supplier khajiit = new Supplier("Khajiit Caravans", "Various");
        supplierDataStore.add(khajiit);
        Supplier Thaler = new Supplier("Thaler", "Various");
        supplierDataStore.add(Thaler);
        Supplier consortium = new Supplier("Consortium", "Various");
        supplierDataStore.add(consortium);

        //setting up a new product category
        ProductCategory spell = new ProductCategory("Spell", "Sorcery", "An invocation of magic, or a curse, that may help the caster in various ways. ");
        ProductCategory potion = new ProductCategory("Potions", "Alchemy", "A finely brewed liquid that may be a remedy, or provide some form of enhancement of the mind or body.");
        ProductCategory poison = new ProductCategory("Poisons", "Alchemy", "A vile liquid that affects its victim's health, it may be deadly, or paralyzing, or any other form of malice.");
        ProductCategory book = new ProductCategory("Books", "Sorcery", "A document of wizardry, describing either a spell, curse, recipe, or anything related to magic.");
        ProductCategory weapon = new ProductCategory("Weapons", "Armament", "An enchanted or magical tool, mostly used by people for the intent of murder, dismemberment, crippling, or other form of vileness.");
        ProductCategory armor = new ProductCategory("Armors", "Armament", "A piece or set of gear, used for protection against one's acquaintance with intent not so pure..");


        productCategoryDataStore.add(spell);
        productCategoryDataStore.add(poison);
        productCategoryDataStore.add(potion);
        productCategoryDataStore.add(book);
        productCategoryDataStore.add(weapon);
        productCategoryDataStore.add(armor);


        //setting up products and printing it
        productDataStore.add(new Product("Elixir of Life", 49.9f, "USD", "Awful smell, worse taste, but it can save a life.", potion, khajiit));
        productDataStore.add(new Product("Invoker's Grimoire", 8900, "USD", "Though ancient, it doesn't seems it has been used much.", book, consortium));
        productDataStore.add(new Product("Circle of Protection", 1229, "USD", "Handy for most wizards, crucial if a witchhunt is near.", spell, Thaler));
        productDataStore.add(new Product("Blue Sky", 319.99f, "USD", "Legends say this drink makes one invisible, but since nobody has reverted it before, it may be that it just removes you from the world.", potion, khajiit));
        productDataStore.add(new Product("Emerald Dream", 139.9f, "USD", "A draught of serenity, this concoction can calm, and in some cases bring sleep to one who's able to keep it down..", potion, khajiit));
        productDataStore.add(new Product("White Flame", 849.9f, "USD", "As the name suggests, this substance can burn or melt almost anything with spectacular white tongues of flame, as soon as in contact with something other than the enchanted bottle it's usually stored inside. .", poison, consortium));
        productDataStore.add(new Product("On house-elves Part I, and II by Albus Dumbledore", 349.9f, "USD", "The famous wizard's books on the subject of house-elves and their history.", book, khajiit));
        productDataStore.add(new Product("Decrypt", 1479, "USD", "A spell mostly used by witch hunters, or treasure seekers, it can reveal hidden meanings and messages lesser mages hide in their books, or other objects..", spell, Thaler));
        productDataStore.add(new Product("Wild Arcane", 379, "USD", "A lunatic you are, if you attempt this sorcery, it is said to grant immortality, but most attempts result with the caster melted into a puddle.", spell, consortium));
        productDataStore.add(new Product("Hailstorm", 2279, "USD", "Weather spells are quite rare, this one especially. The last time it was properly cast, it caused the Ice Age.", spell, consortium));
        productDataStore.add(new Product("Witch Hunter's shortsword", 1079, "USD", "One of those famous weapons the members of the Order of the Witch Hunters use, it nullifies magic it comes into contact with.", weapon, Thaler));
        productDataStore.add(new Product("Dwarven runeblade", 1779, "USD", "One would think the runes carved into the blade of this fine weapon is some kind of spell. It's probably just an insult.", weapon, Thaler));
        productDataStore.add(new Product("Bulwark of Reflection", 979, "USD", "This seemingly regular shield is said to reflect any projectiles it is struck with, be it of magical nature, or not.", armor, khajiit));
        productDataStore.add(new Product("Mysterious Hammer", 2679, "USD", "It may be the weapon of a god, or just a regular hammer made by the alley blacksmith..", weapon, Thaler));
        productDataStore.add(new Product("Swirling Death", 7989.9f, "USD", "The most lethal poison currently known to exist, it has no known cure, and a drop finishes off the victim in seconds. The recipe is lost, only a few bottles remain scattered around the world. .", poison, consortium));
        productDataStore.add(new Product("Pyre of Doom", 2290, "USD", "A devastating hex, invented by an insane warlock whose name is now long forgotten, in contradiction with his creation..", spell, khajiit));
        productDataStore.add(new Product("Rider of the Storm", 1549.9f, "USD", "A concoction that allows the user to travel through storm clouds in the form of ball lightning, it's consifered safe by most madmen..", potion, Thaler));
        productDataStore.add(new Product("Reptile's Saliva", 249.9f, "USD", "A destructive acid, hardly contained in anything for long.", poison, khajiit));

    }
}
