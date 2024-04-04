package app.controllers;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class OrderController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("addtobasket", ctx -> addtobasket(ctx, connectionPool));

    }

    private static void addtobasket(Context ctx, ConnectionPool connectionPool) {

    }
}
