package app.controllers;

import app.entities.Bottoms;
import app.entities.Orderline;
import app.entities.Toppings;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.*;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class OrderController {
    protected static List<Orderline> basket = new ArrayList<>();
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("addtobasket", ctx -> addtobasket(ctx, connectionPool));
        app.get("viewOrder", ctx -> ctx.render("viewOrder.html"));
        app.get("userViewPage", ctx -> ctx.render("userViewPage.html"));
        app.get("homepage", ctx -> ctx.render("orderpage.html"));
    }

    private static void addtobasket(Context ctx, ConnectionPool connectionPool) {

        int bottomId = Integer.parseInt(ctx.formParam("bottom_id"));
        int toppingId = Integer.parseInt(ctx.formParam("topping_id"));
        int amount = Integer.parseInt(ctx.formParam("amount"));
        User user = ctx.sessionAttribute("currentUser");

        try {
            if (bottomId != 0 && toppingId != 0 && amount > 0) {
                for (int i = 0; i < amount; i++){
                    OrderLineMapper.addOrderLine(toppingId, bottomId, connectionPool);
                }
            } else {
                ctx.attribute("message", "Please choose correct inputs");
            }
            List<Bottoms> bottomsList = BottomsMapper.getAllBottoms(connectionPool);
            List<Toppings> toppingsList = ToppingsMapper.getAllToppings(connectionPool);
            ctx.attribute("bottomsList", bottomsList);
            ctx.attribute("toppingsList", toppingsList);
            ctx.render("orderpage.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", "Noget gik galt. Prøv evt. igen");
            ctx.render("orderpage.html");
        }
    }
}

