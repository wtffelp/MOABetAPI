package org.example.Model;

public class OpcoesApostasModel {
    private int id;
    private int aposta_id;
    private String texto;
    private double odd;

    public OpcoesApostasModel () {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAposta_id() {
        return aposta_id;
    }

    public void setAposta_id(int aposta_id) {
        this.aposta_id = aposta_id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }


}
