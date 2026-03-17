package org.example.Model;

import java.sql.Timestamp;

public class ApostasModel {
    private int id;
    private String titulo;
    private String descricao;
    private String status = "Aberta";
    private String opcao_vencedora;

    public ApostasModel () {}

    public Timestamp getCriado_em() {
        return criado_em;
    }

    public void setCriado_em(Timestamp criado_em) {
        this.criado_em = criado_em;
    }

    private Timestamp criado_em;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpcao_vencedora() {
        return opcao_vencedora;
    }

    public void setOpcao_vencedora(String opcao_vencedora) {
        this.opcao_vencedora = opcao_vencedora;
    }

}