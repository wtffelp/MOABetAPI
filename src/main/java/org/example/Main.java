package org.example;
import io.javalin.Javalin;

public class Main {
    Javalin javalin = Javalin.create().start(7000);
    public static void main(String[] args) {
        System.out.println("Servidor ligado!");
    }
}