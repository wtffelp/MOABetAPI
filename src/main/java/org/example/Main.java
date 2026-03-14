package org.example;
import io.javalin.Javalin;
import org.example.Controller.UserController;

public class Main {
    public static void main(String[] args) {
        Javalin javalin = Javalin.create().start(7000);
        UserController userController = new UserController();
        userController.registrarRotas(javalin);
    }
}