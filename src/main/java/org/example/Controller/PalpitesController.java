package org.example.Controller;

import java.util.List;

import org.example.Model.PalpitesModel;
import org.example.Service.PalpitesService;

import com.google.gson.Gson;

import io.javalin.Javalin;

public class PalpitesController {
    PalpitesService palpitesService = new PalpitesService();
    private static Gson gson = new Gson();
    public void palpitesRotas(Javalin app){
        app.get("/palpites/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            PalpitesModel palpites = palpitesService.buscarPorId(id);
            String jsonConversor = gson.toJson(palpites);
            ctx.result(jsonConversor);
        });
        app.get("/palpites", ctx -> {
            String valor = ctx.queryParam("valor");
            String usuario_id = ctx.queryParam("usuario_id");
            if (valor != null) {
                List<PalpitesModel> palpite = palpitesService.buscarPorValor(Integer.parseInt(valor));
                ctx.result(gson.toJson(palpite));
            } else if (usuario_id != null) {
                List<PalpitesModel> palpite = palpitesService.buscarPorIdeDeUsuario(Integer.parseInt(usuario_id));
                ctx.result(gson.toJson(palpite));
            } else {
                List<PalpitesModel> palpites = palpitesService.buscarTodosOsPalpites();
                ctx.result(gson.toJson(palpites));
            }
        });
        app.post("/palpites", ctx -> {
            String json = ctx.body();
            PalpitesModel postPalpite = gson.fromJson(json, PalpitesModel.class);
            palpitesService.criarPalpite(
                postPalpite.getUser_id(),
                postPalpite.getAposta_id(),
                postPalpite.getOpcao_id(),
                postPalpite.getValor()
            );
            ctx.status(201);
        });
        app.delete("/palpites/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            palpitesService.deletarPalpite(id);
            ctx.status(204);
        });
    }
}
