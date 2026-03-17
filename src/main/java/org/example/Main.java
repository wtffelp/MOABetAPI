package org.example;
import io.javalin.Javalin;

import org.example.Controller.ApostasController;
import org.example.Controller.OpcoesApostaController;
import org.example.Controller.PalpitesController;
import org.example.Controller.UserController;

public class Main {
    public static void main(String[] args) {
        Javalin javalin = Javalin.create().start(7000);
        UserController userController = new UserController();
        ApostasController apostasController = new ApostasController();
        OpcoesApostaController opcoesApostaController = new OpcoesApostaController();
        PalpitesController palpitesController = new PalpitesController();
        userController.registrarRotas(javalin);
        apostasController.apostasRotas(javalin);
        opcoesApostaController.opcoesApostaRotas(javalin);
        palpitesController.palpitesRotas(javalin);
    }
}