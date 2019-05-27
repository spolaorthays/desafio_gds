package com.gds.desafioandroidgds.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movimento {

    private int id;
    @SerializedName("numId")
    @Expose
    private String numId;
    @SerializedName("codProduto")
    @Expose
    private String codProduto;
    @SerializedName("quantidade")
    @Expose
    private String quantidade;
    @SerializedName("vlTotal")
    @Expose
    private String vlTotal;
    @SerializedName("dtOcorrencia")
    @Expose
    private String dtOcorrencia;
    @SerializedName("operacaoDC")
    @Expose
    private String operacaoDC;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("cancelado")
    @Expose
    private String cancelado;
    private int fkIdCartao;

    public Movimento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumId() {
        return numId;
    }

    public void setNumId(String numId) {
        this.numId = numId;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(String vlTotal) {
        this.vlTotal = vlTotal;
    }

    public String getDtOcorrencia() {
        return dtOcorrencia;
    }

    public void setDtOcorrencia(String dtOcorrencia) {
        this.dtOcorrencia = dtOcorrencia;
    }

    public String getOperacaoDC() {
        return operacaoDC;
    }

    public void setOperacaoDC(String operacaoDC) {
        this.operacaoDC = operacaoDC;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    public int getFkIdCartao() {
        return fkIdCartao;
    }

    public void setFkIdCartao(int fkIdCartao) {
        this.fkIdCartao = fkIdCartao;
    }
}
