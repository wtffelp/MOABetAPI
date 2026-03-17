package org.example.Repository;

import java.util.List;
import java.util.Optional;

import org.example.Model.OpcoesApostasModel;
import org.example.config.Database;
import org.jdbi.v3.core.Jdbi;

/*
criarOpcao (vinculada com a ApostasRepository)
buscarOpçõesPorAposta
deletarOpção
atualziarODD
*/

public class OpcoesApostaRepository {
    
    Jdbi jdbi = Database.getJdbi();

    public OpcoesApostasModel criarOpcao(int aposta_id, String texto, double odd) {
        int id = jdbi.withHandle(handle -> {
            return handle.createUpdate("""
                    INSERT INTO opcoes_aposta (aposta_id, texto, odd) VALUES (:aposta_id, :texto, :odd)
                    """)
                    .bind("aposta_id", aposta_id)
                    .bind("texto", texto)
                    .bind("odd", odd)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .findOne()
                    .orElseThrow();
        });
        OpcoesApostasModel opcoesApostasModel = buscarPorId(id);
        return opcoesApostasModel;
    }
    public OpcoesApostasModel buscarPorTexto(String texto) {
        OpcoesApostasModel aposta = jdbi.withHandle(handle -> {
            Optional<OpcoesApostasModel> result = handle.createQuery("SELECT * FROM opcoes_aposta WHERE texto = :texto")
            .bind("texto", texto)
            .mapToBean(OpcoesApostasModel.class)
            .findOne();
            return result.orElse(null);
        });
        return aposta;
    }
    public OpcoesApostasModel buscarporTextoEIdDeAposta(String texto, int aposta_id) {
        OpcoesApostasModel aposta =  jdbi.withHandle(handle -> {
            Optional<OpcoesApostasModel> result = handle.createQuery("SELECT * FROM opcoes_aposta WHERE texto = :texto AND aposta_id = :aposta_id")
            .bind("texto", texto)
            .bind("aposta_id", aposta_id)
            .mapToBean(OpcoesApostasModel.class)
            .findOne();
            return result.orElse(null);
        });
        return aposta;
    }
    public List<OpcoesApostasModel> buscarTodasAsOpcoes (){
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM opcoes_aposts")
            .mapToBean(OpcoesApostasModel.class)
            .list();
        });
    }
    public List<OpcoesApostasModel> buscarPorODD(double odd){
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM opcoes_aposta WHERE odd => :odd")
            .bind("odd", odd)
            .mapToBean(OpcoesApostasModel.class)
            .list();
        });
    }
    public OpcoesApostasModel buscarPorId(int id) {
        OpcoesApostasModel aposta = jdbi.withHandle(handle -> {
            Optional<OpcoesApostasModel> result = handle.createQuery("SELECT * FROM opcoes_aposta WHERE id = :id")
                .bind("id", id)
                .mapToBean(OpcoesApostasModel.class)
                .findOne();
            return result.orElse(null);
        });
        return aposta;

    }
    public List<OpcoesApostasModel> buscarOpcoesPorIdDeAposta(int aposta_id) {
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM opcoes_aposta WHERE aposta_id = :aposta_id")
            .bind("aposta_id", aposta_id)
            .mapToBean(OpcoesApostasModel.class)
            .list();
        });
    }
    public void deletarOpcao(int opcao_id) {
        jdbi.withHandle(handle -> {
            return handle.createUpdate("DELETE FROM opcoes_aposta WHERE opcao_id = :opcao_id")
            .bind("opcao_id", opcao_id)
            .execute();
        });
    }
    public OpcoesApostasModel atualizarODD(int id, double odd) {
        jdbi.withHandle(handle -> {
            return handle.createUpdate("UPDATE opcoes_aposta SET odd = :odd WHERE id = :id")
            .bind("id", id)
            .bind("odd", odd)
            .execute();
        });
        OpcoesApostasModel opcoesApostasModel = buscarPorId(id);
        return opcoesApostasModel;
    }
}