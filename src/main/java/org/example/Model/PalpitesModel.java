package org.example.Model;

import java.sql.Timestamp;

public class PalpitesModel {
    private int id;
    private int user_id;
    private int aposta_id;
    private int opcao_id;
    private double valor;
    private Timestamp criado_em;

    public PalpitesModel () {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAposta_id() {
        return aposta_id;
    }

    public void setAposta_id(int aposta_id) {
        this.aposta_id = aposta_id;
    }

    public int getOpcao_id() {
        return opcao_id;
    }

    public void setOpcao_id(int opcao_id) {
        this.opcao_id = opcao_id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Timestamp getCriado_em() {
        return criado_em;
    }

    public void setCriado_em(Timestamp criado_em) {
        this.criado_em = criado_em;
    }

}
