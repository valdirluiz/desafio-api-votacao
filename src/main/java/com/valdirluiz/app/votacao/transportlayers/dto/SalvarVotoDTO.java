package com.valdirluiz.app.votacao.transportlayers.dto;

public class SalvarVotoDTO {

    private String cpf;
    private Boolean valor;
    private Long idSessao;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getValor() {
        return valor;
    }

    public void setValor(Boolean valor) {
        this.valor = valor;
    }

    public Long getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Long idSessao) {
        this.idSessao = idSessao;
    }
}
