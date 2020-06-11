package com.cristovancamilo.apoiobm.model;

public class EstoqueBloqueado {

    private String produto;
    private String dataAbate;
    private String habilitacao;
    private String quantidade;

    public EstoqueBloqueado() {
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDataAbate() {
        return dataAbate;
    }

    public void setDataAbate(String dataAbate) {
        this.dataAbate = dataAbate;
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
}
