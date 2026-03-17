package org.example.Repository;

import java.util.Optional;

import org.example.Model.PalpitesModel;
import org.example.config.Database;
import org.jdbi.v3.core.Jdbi;
import java.util.List;

/*
criarPalpite
buscarPalpite(por id, valor[double] e todos os palpites)
deleter palpite
*/

public class PalpitesRepository {
    
    Jdbi jdbi = Database.getJdbi();

    public PalpitesModel criarPalpite(int usuario_id, int aposta_id, int opcao_id, double valor) {
        int id = jdbi.withHandle(handle -> {
            return handle.createUpdate("INSERT INTO palpites (usuario_id, aposta_id, opcao_id, valor) VALUES (:usuario_id, :aposta_id, :opcao_id, :valor)")
                .bind("usuario_id",usuario_id)
                .bind("aposta_id",aposta_id)
                .bind("opcao_id",opcao_id)
                .bind("valor",valor)
                .executeAndReturnGeneratedKeys("id")
                .mapTo(Integer.class)
                .findOne()
                .orElseThrow();
        });
        PalpitesModel palpitesModel = buscarPorId(id);
        return palpitesModel;
    }
    public List<PalpitesModel> buscarPorIdDoUsuarioEIdDeAposta(int usuario_id, int aposta_id) {
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM palpites WHERE usuario_id = :usuario_id AND aposta_id = :aposta_id")
                .bind("usuario_id", usuario_id)
                .bind("aposta_id", aposta_id)
                .mapToBean(PalpitesModel.class)
                .list();
        });
    }
    public PalpitesModel buscarPorId(int id) {
        PalpitesModel palpite = jdbi.withHandle(handle -> {
            Optional<PalpitesModel> result = handle.createQuery("SELECT * FROM palpites WHERE id = :id")
                .bind("id", id)
                .mapToBean(PalpitesModel.class)
                .findOne();
            return result.orElse(null);
        });
        return palpite;
    }
    public List<PalpitesModel> buscarPorIdeDeUsuario(int usuario_id) {
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM palpites WHERE usuario_id = :usuario_id")
            .bind("usuario_id", usuario_id)
            .mapToBean(PalpitesModel.class)
            .list();
        });
    }
    public List<PalpitesModel> buscarPorValor(double valor) {
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM palpites WHERE valor = :valor")
            .bind("valor", valor)
            .mapToBean(PalpitesModel.class)
            .list();
        });
    }
    public List<PalpitesModel> buscarTodosOsPalpites(){
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM palpites")
            .mapToBean(PalpitesModel.class)
            .list();
        });
    }
    public void deletarPalpite(int id) {
        jdbi.withHandle(handle -> {
            return handle.createUpdate("DELETE FROM palpites WHERE id = :id")
            .bind("id", id)
            .execute();
        });
    }
}
