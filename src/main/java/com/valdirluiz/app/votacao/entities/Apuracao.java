package com.valdirluiz.app.votacao.entities;

public class Apuracao {

    private Long votosSim;
    private Long votosNao;

    public Apuracao(Long votosSim, Long votosNao) {
        this.votosSim = votosSim;
        this.votosNao = votosNao;
    }

    public Long getVotosSim() {
        return votosSim;
    }

    public void setVotosSim(Long votosSim) {
        this.votosSim = votosSim;
    }

    public Long getVotosNao() {
        return votosNao;
    }

    public void setVotosNao(Long votosNao) {
        this.votosNao = votosNao;
    }
}
