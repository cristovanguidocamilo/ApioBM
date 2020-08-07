package com.cristovancamilo.apoiobm.model;

public class AbatesPecuarista {

    private String dataAbate;
    private String nome;
    private String quant;
    private String status;
    private String lote;
    private String pesoAbatido;

    public AbatesPecuarista() {
    }

    public String getDataAbate() {
        return dataAbate;
    }

    public void setDataAbate(String dataAbate) {
        this.dataAbate = dataAbate;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuant() {
        return quant;
    }

    public void setQuant(String quant) {
        this.quant = quant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getPesoAbatido() {
        return pesoAbatido;
    }

    public void setPesoAbatido(String pesoAbatido) {
        this.pesoAbatido = pesoAbatido;
    }
}
