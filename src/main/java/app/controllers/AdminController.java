package app.controllers;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;

public class AdminController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("viewOrder", ctx -> ctx.render("viewOrder.html"));
        app.get("userViewPage", ctx -> ctx.render("userViewPage.html"));
        app.get("homepage", ctx -> ctx.render("orderpage.html"));
    }
}
