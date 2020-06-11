package com.cristovancamilo.apoiobm.model;

public class Camaras {

    private String codCamara;
    private String habilitacao;
    private String quantidade;
    private String periodo;

    public Camaras() {
    }

    public String getCodCamara() {
        return codCamara;
    }

    public void setCodCamara(String codCamara) {
        this.codCamara = codCamara;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    public void setHabilitacao(String habilitacao) {
        this.habilitacao = habilitacao;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
