package org.example.Service;

import org.example.Model.UserModel;
import org.example.Repository.UserRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.List;

public class UserService {
    UserRepository userRepository = new UserRepository();
    public UserModel criarUsuario(String email, String nome_completo, String username, String senha){
        if (buscarPorUsername(username) == null) {
            String hashedPassword = BCrypt.withDefaults().hashToString(12, senha.toCharArray());

            return userRepository.criarUsuario(email, nome_completo, username, hashedPassword);
        } else {
            throw new RuntimeException("Usuario ja cadastrado.");
        }
    }
    public List<UserModel> buscarTodosOsUsuarios(){
        return userRepository.buscarTodosOsUsuarios();
    }
    public UserModel buscarPorNome(String nome_completo){
        return userRepository.buscarPorNome(nome_completo);
    }
    public UserModel buscarPorEmail(String email) {
        return userRepository.buscarPorEmail(email);
    }
    public UserModel buscarPorId(int id){
        return userRepository.buscarPorId(id);
    }
    public UserModel buscarPorUsername(String username){
        return userRepository.buscarPorUsername(username);
    }
    public UserModel atualizarUsuario(int id, String username, String senha){
        if (buscarPorId(id) != null) {
            String hashedPassword = BCrypt.withDefaults().hashToString(12, senha.toCharArray());
            return userRepository.atualizarUsuario(id, username, hashedPassword);
        } else {
            throw new RuntimeException("Usuario não encontrado.");
        }
    }
    public void deletarUsuario(int id){
        userRepository.deletarUsuario(id);
    }
    public void adicionaSaldo(int id, double saldo){
        if (buscarPorId(id) != null) {
            userRepository.adicionaSaldo(id, saldo);
        } else {
            throw new RuntimeException("Usuario não encontrado.");
        }
    }
}
