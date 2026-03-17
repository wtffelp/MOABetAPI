package org.example.Repository;

import java.util.List;
import java.util.Optional;

import org.example.Model.ApostasModel;
import org.example.config.Database;
import org.jdbi.v3.core.Jdbi;

/*criarAposta
buscarTodos
buscarPorId
buscarPorTitulo
buscarPorStatus
atualizarStatus
deletarAposta*/

public class ApostasRepository {
    Jdbi jdbi = Database.getJdbi();

    public ApostasModel criarAposta(String titulo, String descricao) {
        jdbi.withHandle(handle -> {
            return handle.createUpdate("""
                INSERT INTO apostas (titulo, descricao) VALUES (:titulo, :descricao)
            """)
            .bind("titulo", titulo)
            .bind("descricao", descricao)
            .execute();
        });
        ApostasModel apostasModel = buscarPorTitulo(titulo);
        return apostasModel;
    }
    public List<ApostasModel> buscarTodasAsApostas(){
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM apostas")
                    .mapToBean(ApostasModel.class)
                    .list();
        });
    }
    public ApostasModel buscarPorTitulo(String titulo) {
        ApostasModel aposta = jdbi.withHandle(handle -> {
            Optional<ApostasModel> result = handle.createQuery("""
                    SELECT * FROM apostas WHERE titulo = :titulo
                    """)
                    .bind("titulo", titulo)
                    .mapToBean(ApostasModel.class)
                    .findOne();
            return result.orElse(null);
        });
        return aposta;
    }
    public ApostasModel buscarPorDescricao(String descricao) {
        ApostasModel apostas = jdbi.withHandle(handle -> {
            Optional<ApostasModel> result = handle.createQuery("""
                    SELECT * FROM apostas WHERE descricao = :descricao
                    """)
                    .bind("descricao", descricao)
                    .mapToBean(ApostasModel.class)
                    .findOne();
            return result.orElse(null);
        });
        return apostas;
    }
    public ApostasModel buscarPorId(int id) {
        ApostasModel apostas = jdbi.withHandle(handle -> {
            Optional<ApostasModel> result = handle.createQuery("""
                    SELECT * FROM apostas WHERE id = :id
                    """)
                    .bind("id", id)
                    .mapToBean(ApostasModel.class)
                    .findOne();
            return result.orElse(null);
        });
        return apostas;
    }
    public ApostasModel buscarPorStatus(String status) {
        ApostasModel apostas = jdbi.withHandle(handle -> {
            Optional<ApostasModel> result = handle.createQuery("""
                    SELECT * FROM apostas WHERE status = :status
                    """)
                    .bind("status", status)
                    .mapToBean(ApostasModel.class)
                    .findOne();
            return result.orElse(null);
        });
        return apostas;
    }
    public ApostasModel finalziarAposta(String status, String opcao_vencedora,int id) {
        jdbi.withHandle(handle -> {
            return handle.createUpdate("UPDATE apostas SET status = :status, opcao_vencedora = :opcao_vencedo WHERE id = :id")
            .bind("status", status)
            .bind("opcao_vencedora", opcao_vencedora)
            .bind("id", id)
            .execute();
        });
        ApostasModel apostasModel = buscarPorId(id);
        return apostasModel;
    }
    public void deletarApostas(int id){
        jdbi.withHandle(handle -> {
            return handle.createUpdate("DELETE FROM apostas WHERE id = :id")
            .bind("id", id)
            .execute();
        });
    }
}
