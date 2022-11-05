package com.valdirluiz.app.votacao.transportlayers.dto;


public class SalvarSessaoDTO {

    private String nome;
    private Long idPauta;
    private Integer validadeMinutos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    public Integer getValidadeMinutos() {
        return validadeMinutos;
    }

    public void setValidadeMinutos(Integer validadeMinutos) {
        this.validadeMinutos = validadeMinutos;
    }
}
