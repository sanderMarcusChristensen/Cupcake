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

    }


}

