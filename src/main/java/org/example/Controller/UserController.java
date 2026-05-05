package org.example.Controller;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import org.example.Annotations.AnnotationExclusionStrategy;
import org.example.Model.UserModel;
import org.example.Service.UserService;
import com.google.gson.Gson;

import java.util.List;

public class UserController {
    UserService userService = new UserService();
    private static Gson gson = new GsonBuilder()
            .setExclusionStrategies(new AnnotationExclusionStrategy())
            .create();
    public void registrarRotas(Javalin app){
        app.get("/usuarios/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            UserModel user = userService.buscarPorId(id);
            String jsonConversor = gson.toJson(user);
            ctx.result(jsonConversor);
        });
        app.get("/usuarios", ctx -> {
            String email = ctx.queryParam("email");
            String nome_completo = ctx.queryParam("nome_completo");
            if (email != null){
                UserModel user = userService.buscarPorEmail(email);
                ctx.result(gson.toJson(user));
            } else if (nome_completo != null) {
                UserModel user = userService.buscarPorNome(nome_completo);
                ctx.result(gson.toJson(user));
            } else {
                List<UserModel> users = userService.buscarTodosOsUsuarios();
                String jsonConversor = gson.toJson(users);
                ctx.result(jsonConversor);
            }
        });
        app.post("/usuarios", ctx -> {
            String json = ctx.body();
            UserModel postUser = gson.fromJson(json, UserModel.class);
            userService.criarUsuario(postUser.getEmail(), postUser.getNome_completo(), postUser.getUsername(), postUser.getSenha());
            ctx.status(201);
        });

        app.put("/usuarios/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            UserModel atualizarUser = gson.fromJson(ctx.body(), UserModel.class);
            userService.atualizarUsuario(
                    id,
                    atualizarUser.getUsername(),
                    atualizarUser.getSenha()
            );
            ctx.status(200);
        });
        app.delete("/usuarios/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            userService.deletarUsuario(id);
            ctx.status(204);
        });
    }
}
