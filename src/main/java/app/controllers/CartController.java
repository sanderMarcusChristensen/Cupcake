package app.controllers;

import app.entities.*;
import app.exceptions.DatabaseException;
import app.persistence.BottomsMapper;
import app.persistence.ConnectionPool;
import app.persistence.ToppingsMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class CartController {

    Cart cart;

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("addtobasket", ctx -> addtobasket(ctx, connectionPool));
        app.get("viewbasket", ctx -> ctx.render("checkOutPage.html"));
        //app.get("orderpage", ctx -> ctx.render("orderpage.html"));
        app.get("orderpage", ctx -> loadOrderPage(ctx, connectionPool));
    }

    private static void loadOrderPage(Context ctx, ConnectionPool connectionPool) {
        User user = ctx.sessionAttribute("currentUser");

        List<Bottoms> bottomsList = null;
        List<Toppings> toppingsList = null;
        try {
            bottomsList = BottomsMapper.getAllBottoms(connectionPool);
            toppingsList = ToppingsMapper.getAllToppings(connectionPool);
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
        }
        ctx.attribute("currentUserName", user.getUser_name());
        ctx.attribute("bottomsList", bottomsList);
        ctx.attribute("toppingsList", toppingsList);
        ctx.render("orderpage.html");
    }

    private static void addtobasket(Context ctx, ConnectionPool connectionPool) {

        List<Bottoms> bottomsList = null;
        List<Toppings> toppingsList = null;

        int bottomId = Integer.parseInt(ctx.formParam("bottom_id"));
        int toppingId = Integer.parseInt(ctx.formParam("topping_id"));
        int amount = Integer.parseInt(ctx.formParam("amount"));
        User user = ctx.sessionAttribute("currentUser");
        Cart cart = ctx.sessionAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        if (bottomsList == null || toppingsList == null) {
            try {
                bottomsList = BottomsMapper.getAllBottoms(connectionPool);
                toppingsList = ToppingsMapper.getAllToppings(connectionPool);
            } catch (DatabaseException e) {
                ctx.attribute("message", e.getMessage());
            }
            ctx.attribute("bottomsList", bottomsList);
            ctx.attribute("toppingsList", toppingsList);
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

            ctx.render("orderpage.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", "Noget gik galt. Prøv evt. igen");
            ctx.render("orderpage.html");

        }
    }
}

