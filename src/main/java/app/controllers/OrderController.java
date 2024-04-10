package app.controllers;

import app.entities.*;
import app.exceptions.DatabaseException;
import app.persistence.*;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class OrderController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("/pay", ctx -> pay(ctx, connectionPool));
        //app.get("pay", ctx -> ctx.redirect("orderpage.html"));
    }
}

