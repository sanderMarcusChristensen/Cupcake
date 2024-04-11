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
        app.get("/orders", ctx -> getAllOrders(ctx, connectionPool));
        //app.get("pay", ctx -> ctx.redirect("orderpage.html"));
    }

    private static void pay(Context ctx, ConnectionPool connectionPool) {
//        ctx.req().getSession().invalidate();

        Cart cart = ctx.sessionAttribute("cart");
        User user = ctx.sessionAttribute("currentUser");

        int total_price = cart.getTotalPrice();

        int orderline_amount = cart.getCount();

        int user_id = user.getUser_id();

        try {
            // Call the addOrderToDatabase method to save the order into the database
            int orderId = OrderMapper.addOrderToDatabase(new Order(0, total_price, orderline_amount, user_id), connectionPool);

            for (CartLine cartLine : cart.cartLines){
                int totalPrice = cartLine.getPrice();
                int topping_id = cartLine.getToppings().getTopping_id();
                int bottom_id = cartLine.getBottoms().getBottom_id();
                int amount = cartLine.getAmount();
                OrderLineMapper.addOrderLine(orderId, totalPrice, topping_id, bottom_id, amount, connectionPool);
            }


            cart.cartLines.clear();

            List<Bottoms> bottomsList = ctx.sessionAttribute("bottomsList");
            List<Toppings> toppingsList = ctx.sessionAttribute("toppingsList");
            ctx.attribute("bottomsList", bottomsList);
            ctx.attribute("toppingsList", toppingsList);
            ctx.render("orderpage.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.redirect("/");
        }
    }
    private static void getAllOrders(Context ctx, ConnectionPool connectionPool) {
        try {
            // Retrieve all orders from the database
            List<Order> orders = OrderMapper.getAllOrder(connectionPool);

            // Set the orders as an attribute in the context
            ctx.attribute("orders", orders);

            // Render the HTML template to display the orders
            ctx.render("orders.html");
        } catch (DatabaseException e) {
            // If an error occurs while retrieving orders, redirect to the homepage with an error message
            ctx.attribute("message", e.getMessage());
            ctx.redirect("/");
        }
    }
}




