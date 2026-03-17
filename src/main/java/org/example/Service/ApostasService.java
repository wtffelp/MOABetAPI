package org.example.Service;

import java.util.List;

import org.example.Model.ApostasModel;
import org.example.Repository.ApostasRepository;

public class ApostasService {
    ApostasRepository apostasRepository = new ApostasRepository();
    public ApostasModel criarApostas(String titulo, String descricao) {
        return apostasRepository.criarAposta(titulo, descricao);
    }
    public List<ApostasModel> buscarTodasAsApostas(){
        return apostasRepository.buscarTodasAsApostas();
    }
    public ApostasModel buscarPorTitulo(String titulo){
        return apostasRepository.buscarPorTitulo(titulo);
    }
    public ApostasModel buscarPorDescricao(String descricao) {
        return apostasRepository.buscarPorDescricao(descricao);
    }
    public ApostasModel buscarPorId(int id) {
        return apostasRepository.buscarPorId(id);
    }
    public ApostasModel buscarPorStatus(String status) {
        return apostasRepository.buscarPorStatus(status);
    }
    public ApostasModel finalizarAposta(String status, String opcao_vencedora, int id) {
        if (buscarPorId(id) != null) {
            return apostasRepository.finalziarAposta(status, opcao_vencedora, id);
        } else {
            throw new RuntimeException("Aposta não encontrada.");
        }
    }
    public void deletarAposta(int id) {
        apostasRepository.deletarApostas(id);
    }
}
