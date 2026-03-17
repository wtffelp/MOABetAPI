package org.example.Controller;

import java.util.List;

import org.example.Model.ApostasModel;
import org.example.Service.ApostasService;

import com.google.gson.Gson;

import io.javalin.Javalin;

public class ApostasController {
    ApostasService apostasService = new ApostasService();
    private static Gson gson = new Gson();

    public void apostasRotas(Javalin app){
        app.get("/apostas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ApostasModel aposta = apostasService.buscarPorId(id);
            String jsonConversor = gson.toJson(aposta);
            ctx.result(jsonConversor);
        });
        app.get("/apostas", ctx -> {
            String titulo = ctx.queryParam("titulo");
            String descricao = ctx.queryParam("descricao");
            if (titulo !=  null) {
                ApostasModel aposta = apostasService.buscarPorTitulo(titulo);
                ctx.result(gson.toJson(aposta));
            } else if (descricao != null) {
                ApostasModel aposta = apostasService.buscarPorDescricao(descricao);
                ctx.result(gson.toJson(aposta));
            } else {
                List<ApostasModel> apostas = apostasService.buscarTodasAsApostas();
                ctx.result(gson.toJson(apostas));
            }
        });
        app.post("/apostas", ctx -> {
            String json = ctx.body();
            ApostasModel postAposta = gson.fromJson(json, ApostasModel.class);
            apostasService.criarApostas(postAposta.getTitulo(), postAposta.getDescricao());
            ctx.status(201);
        });
        app.put("/apostas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ApostasModel finalziarApostas = gson.fromJson(ctx.body(), ApostasModel.class);
            apostasService.finalizarAposta(
                finalziarApostas.getStatus(),
                finalziarApostas.getOpcao_vencedora(),
                id
            );
            ctx.status(200);
        });
        app.delete("/apostas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            apostasService.deletarAposta(id);
            ctx.status(204);
        });
    }
}
