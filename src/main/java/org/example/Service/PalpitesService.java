package org.example.Service;

import java.util.List;

import org.example.Model.PalpitesModel;
import org.example.Repository.PalpitesRepository;

public class PalpitesService {
    PalpitesRepository palpitesRepository = new PalpitesRepository();
    public PalpitesModel criarPalpite(int usuario_id, int aposta_id, int opcao_id, double valor) {
        if (buscarPorIdDoUsuarioEIdDeAposta(usuario_id, aposta_id).isEmpty()) {
            return palpitesRepository.criarPalpite(usuario_id, aposta_id, opcao_id, valor);
        } else {
            throw new RuntimeException("O usuario ja deu palpite nessa aposta.");
        }
    }
    public List<PalpitesModel> buscarPorIdDoUsuarioEIdDeAposta(int usuario_id, int aposta_id) {
        return palpitesRepository.buscarPorIdDoUsuarioEIdDeAposta(usuario_id, aposta_id);
    }
    public List<PalpitesModel> buscarPorIdeDeUsuario(int usuario_id) {
        return palpitesRepository.buscarPorIdeDeUsuario(usuario_id);
    }
    public PalpitesModel buscarPorId(int id) {
        return palpitesRepository.buscarPorId(id);
    }
    public List<PalpitesModel> buscarPorValor(double valor) {
        return palpitesRepository.buscarPorValor(valor);
    }
    public List<PalpitesModel> buscarTodosOsPalpites(){
        return palpitesRepository.buscarTodosOsPalpites();
    }
    public void deletarPalpite(int id) {
        palpitesRepository.deletarPalpite(id);
    }
}
