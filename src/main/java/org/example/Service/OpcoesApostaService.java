package org.example.Service;

import java.util.List;

import org.example.Model.OpcoesApostasModel;
import org.example.Repository.OpcoesApostaRepository;

public class OpcoesApostaService {
    OpcoesApostaRepository opcoesApostaRepository = new OpcoesApostaRepository();
    public OpcoesApostasModel criarOpcao(int aposta_id, String texto, double odd) {
        if(buscarporTextoEIdDeAposta(texto, aposta_id) == null) {
            return opcoesApostaRepository.criarOpcao(aposta_id, texto, odd);
        } else {
            throw new RuntimeException("Essa opção ja existe.");
        }
    }
    public OpcoesApostasModel buscarporTextoEIdDeAposta(String texto, int aposta_id) {
        return opcoesApostaRepository.buscarporTextoEIdDeAposta(texto, aposta_id);
    }
    public List<OpcoesApostasModel> buscarTodasAsOpcoes() {
        return opcoesApostaRepository.buscarTodasAsOpcoes();
    }
    public OpcoesApostasModel buscarPorTexto(String texto) {
        return opcoesApostaRepository.buscarPorTexto(texto);
    }
    public List<OpcoesApostasModel> buscarPorODD(double odd){
        return opcoesApostaRepository.buscarPorODD(odd);
    }
    public OpcoesApostasModel buscarPorId(int id) {
        return opcoesApostaRepository.buscarPorId(id);
    }
    public List<OpcoesApostasModel> buscarPorideDeAposta(int aposta_id) {
        return opcoesApostaRepository.buscarOpcoesPorIdDeAposta(aposta_id);
    }
    public void deletarOpcao(int opcao_id) {
        opcoesApostaRepository.deletarOpcao(opcao_id);
    }
    public OpcoesApostasModel atualizarODD(int id, double odd) {
        if (buscarPorId(id) != null) {
            return opcoesApostaRepository.atualizarODD(id, odd);
        } else {
            throw new RuntimeException("ODD não encontrada.");
        }
    }
}
