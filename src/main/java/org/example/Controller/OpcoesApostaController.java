package org.example.Controller;

import java.util.List;

import org.example.Model.OpcoesApostasModel;
import org.example.Service.OpcoesApostaService;

import com.google.gson.Gson;

import io.javalin.Javalin;

public class OpcoesApostaController {
    OpcoesApostaService opcoesApostaService = new OpcoesApostaService();
    private static Gson gson = new Gson();

    public void opcoesApostaRotas(Javalin app) {
        app.get("/opcoes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            OpcoesApostasModel opcoes = opcoesApostaService.buscarPorId(id);
            String jsonConversor = gson.toJson(opcoes);
            ctx.result(jsonConversor);
        });
        app.get("/opcoes", ctx -> {
            String texto = ctx.queryParam("texto");
            String aposta_id = ctx.queryParam("aposta_id");
            String odd = ctx.queryParam("odd");
            if (texto != null) {
                OpcoesApostasModel opcao = opcoesApostaService.buscarPorTexto(texto);
                ctx.result(gson.toJson(opcao));
            } else if (aposta_id != null) {
                OpcoesApostasModel opcao = opcoesApostaService.buscarPorId(Integer.parseInt(aposta_id));
                ctx.result(gson.toJson(opcao));
            } else if (odd != null) {
                List<OpcoesApostasModel> opcao = opcoesApostaService.buscarPorODD(Double.parseDouble(odd));
                ctx.result(gson.toJson(opcao));
            } else {
                List<OpcoesApostasModel> opcoes = opcoesApostaService.buscarTodasAsOpcoes();
                ctx.result(gson.toJson(opcoes));
            }
        });
        app.post("/opcoes", ctx -> {
            String json = ctx.body();
            OpcoesApostasModel postOpcao = gson.fromJson(json, OpcoesApostasModel.class);
            opcoesApostaService.criarOpcao(postOpcao.getAposta_id(), postOpcao.getTexto(), postOpcao.getOdd());
            ctx.status(201);
        });
        app.put("/opcoes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            OpcoesApostasModel atualizarODD = gson.fromJson(ctx.body(), OpcoesApostasModel.class);
            opcoesApostaService.atualizarODD(
                id,
                atualizarODD.getOdd()
            );
            ctx.status(200);
        });
        app.delete("/opcoes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            opcoesApostaService.deletarOpcao(id);
            ctx.status(204);
        });
    }
}