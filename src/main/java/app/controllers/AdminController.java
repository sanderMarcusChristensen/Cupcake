package app.controllers;

import app.entities.Bottoms;
import app.entities.Toppings;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.BottomsMapper;
import app.persistence.ConnectionPool;
import app.persistence.ToppingsMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class AdminController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("viewOrder", ctx -> ctx.render("viewOrder.html"));
        app.get("userViewPage", ctx -> ctx.render("userViewPage.html"));
        app.get("backToHomepage", ctx -> backToHomepage(ctx, connectionPool));
    }

    private static void backToHomepage(Context ctx, ConnectionPool connectionPool) {
        List<Bottoms> bottomsList = ctx.sessionAttribute("bottomsList");
        List<Toppings> toppingsList = ctx.sessionAttribute("toppingsList");
        ctx.attribute("bottomsList", bottomsList);
        ctx.attribute("toppingsList", toppingsList);
        ctx.render("orderpage.html");

    }
}



