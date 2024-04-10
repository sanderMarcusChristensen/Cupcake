package app.controllers;

import app.entities.*;
import app.exceptions.DatabaseException;
import app.persistence.BottomsMapper;
import app.persistence.ConnectionPool;
import app.persistence.ToppingsMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class CartController {


    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("addtobasket", ctx -> addtobasket(ctx, connectionPool));
        app.get("viewbasket", ctx -> ctx.render("checkOutPage.html"));
    }


    private static void addtobasket(Context ctx, ConnectionPool connectionPool) {

        int bottomId = Integer.parseInt(ctx.formParam("bottom_id"));
        int toppingId = Integer.parseInt(ctx.formParam("topping_id"));
        int amount = Integer.parseInt(ctx.formParam("amount"));

        Cart cart = ctx.sessionAttribute("cart");

        List<Bottoms> bottomsList = ctx.sessionAttribute("bottomsList");
        List<Toppings> toppingsList = ctx.sessionAttribute("toppingsList");

        if (cart == null) {
            cart = new Cart();
        }

        try {
            if (bottomId != 0 && toppingId != 0 && amount > 0) {
                Bottoms bottoms = BottomsMapper.getBottomsById(bottomId, connectionPool);
                Toppings toppings = ToppingsMapper.getToppingsById(toppingId, connectionPool);

                cart.add(toppings, bottoms, amount);
                ctx.sessionAttribute("cart", cart);

            } else {
                ctx.attribute("message", "Please choose correct inputs");
            }
            ctx.attribute("bottomsList", bottomsList);
            ctx.attribute("toppingsList", toppingsList);
            ctx.render("orderpage.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", "Noget gik galt. Prøv evt. igen");
            ctx.attribute("bottomsList");
            ctx.attribute("toppingsList");
            ctx.render("orderpage.html");

        }
    }
}

