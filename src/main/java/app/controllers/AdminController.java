package app.controllers;

import app.entities.Bottoms;
import app.entities.Order;
import app.entities.Toppings;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.*;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class AdminController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("viewOrders", ctx -> viewOrders(ctx, connectionPool));
        app.get("viewUsers", ctx -> ctx.render("viewUsers.html"));
        app.get("backToHomepage", ctx -> backToHomepage(ctx, connectionPool));
    }

    private static void viewOrders(Context ctx, ConnectionPool connectionPool) {
        try {
            List<Order> orderList = OrderMapper.getAllOrder(connectionPool);

            ctx.attribute("orderList", orderList);
            ctx.render("viewOrders.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("viewOrders.html");
        }

    }

    private static void backToHomepage(Context ctx, ConnectionPool connectionPool) {
        List<Bottoms> bottomsList = ctx.sessionAttribute("bottomsList");
        List<Toppings> toppingsList = ctx.sessionAttribute("toppingsList");
        ctx.attribute("bottomsList", bottomsList);
        ctx.attribute("toppingsList", toppingsList);
        ctx.render("orderpage.html");

    }
}