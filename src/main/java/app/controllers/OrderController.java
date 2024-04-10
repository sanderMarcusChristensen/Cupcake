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

    private static void pay(Context ctx, ConnectionPool connectionPool) {
        ctx.req().getSession().invalidate();

        int total_price = Integer.parseInt(ctx.formParam("total_price"));
        String user_name = ctx.formParam("user_name");
        int orderline_amount = Integer.parseInt(ctx.formParam("orderline_amount"));
        int user_id = Integer.parseInt(ctx.formParam("user_id"));

        try {
            // Call the addOrderToDatabase method to save the order into the database
            OrderMapper.addOrderToDatabase(new Order(0, total_price, user_name, orderline_amount, user_id), connectionPool);
            // Redirect to the desired page (e.g., orderpage.html) after successful payment
            ctx.redirect("/orderpage.html");
        } catch (DatabaseException e) {
            // If an error occurs while saving the order, redirect to the homepage with an error message
            ctx.attribute("message", e.getMessage());
            ctx.redirect("/");
        }
    }
}



