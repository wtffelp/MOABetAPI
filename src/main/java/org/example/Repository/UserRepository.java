package org.example.Repository;

import org.example.Model.UserModel;
import org.example.config.Database;
import org.jdbi.v3.core.Jdbi;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    Jdbi jdbi = Database.getJdbi();

    public UserModel criarUsuario(String email, String nome_completo, String username, String senha){
        jdbi.withHandle(handle -> {
            return handle.createUpdate("""
                            INSERT INTO usuarios (email, nome_completo, username, senha) VALUES ( :email, :nome_completo, :username, :senha)
                            """)
                    .bind("email", email)
                    .bind("nome_completo", nome_completo)
                    .bind("username", username)
                    .bind("senha", senha)
                    .execute();
        });
        UserModel userModel = buscarPorUsername(username);
        return userModel;
    }
    public List<UserModel> buscarTodosOsUsuarios(){
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM usuarios")
                    .mapToBean(UserModel.class)
                    .list();
        });
    }
    public UserModel buscarPorNome(String nome_completo) {
        UserModel user = jdbi.withHandle(handle -> {
            Optional<UserModel> result = handle.createQuery("SELECT * FROM usuarios WHERE nome_completo = :nome_completo")
                    .bind("nome_completo", nome_completo)
                    .mapToBean(UserModel.class)
                    .findOne();
            return result.orElse(null);
        });
        return user;
    }
    public UserModel buscarPorEmail(String email) {
        UserModel user = jdbi.withHandle(handle -> {
            Optional<UserModel> result = handle.createQuery("SELECT * FROM usuarios WHERE email = :email")
                    .bind("email", email)
                    .mapToBean(UserModel.class)
                    .findOne();
            return result.orElse(null);
        });
        return user;
    }
    public UserModel buscarPorId(int id){
        UserModel user = jdbi.withHandle(handle -> {
           Optional<UserModel> result = handle.createQuery("SELECT * FROM usuarios WHERE id = :id")
                   .bind("id", id)
                   .mapToBean(UserModel.class)
                   .findOne();
           return result.orElse(null);
        });
        return user;
    }
    public UserModel buscarPorUsername(String username){
        UserModel user = jdbi.withHandle(handle -> {
           Optional<UserModel> result= handle.createQuery("SELECT * FROM usuarios WHERE username = :username")
                   .bind("username", username)
                   .mapToBean(UserModel.class)
                   .findOne();
            return result.orElse(null);
        });
        return user;
    }
    public UserModel atualizarUsuario(int id, String username, String senha){
        jdbi.withHandle(handle -> {
            return handle.createUpdate("UPDATE usuarios SET username = :username, senha = :senha WHERE id = :id")
                    .bind("username", username)
                    .bind("senha", senha)
                    .bind("id", id)
                    .execute();
        });
        UserModel userModel = buscarPorId(id);
        return userModel;
    }
    public void deletarUsuario(int id){
        jdbi.withHandle(handle -> {
            return handle.createUpdate("DELETE FROM usuarios WHERE id = :id")
                    .bind("id", id)
                    .execute();
        });
    }
    public void adicionaSaldo(int id, double saldo){
        jdbi.withHandle(handle -> {
            return handle.createUpdate("UPDATE usuarios SET saldo = saldo + :saldo WHERE id = :id")
                    .bind("id", id)
                    .bind("saldo", saldo)
                    .execute();
        });
    }
}