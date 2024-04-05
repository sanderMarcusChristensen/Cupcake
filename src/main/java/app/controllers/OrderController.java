package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class OrderController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        //app.post("addtobasket", ctx -> addtobasket(ctx, connectionPool));

    }

    /*private static void addtobasket(Context ctx, ConnectionPool connectionPool) {
        String topping = ctx.formParam("topping");
        User user = ctx.sessionAttribute("currentUser");

        try {
            if (taskname.length() > 3) {
                Task newTask = TaskMapper.addTask(user, taskname, connectionPool);
            } else {
                ctx.attribute("message", "En task skal være længere end 3 tegn!");
            }
            List<Task> taskList = TaskMapper.getAllTasksPerUser(user.getUserId(), connectionPool);
            ctx.attribute("taskList", taskList);
            ctx.render("task.html");

        } catch (DatabaseException e) {
            ctx.attribute("message", "Noget gik galt. Prøv evt. igen");
            ctx.render("task.html");
        }
    }*/
}

