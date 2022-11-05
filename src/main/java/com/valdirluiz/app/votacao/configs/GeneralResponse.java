package com.valdirluiz.app.votacao.configs;

public class GeneralResponse {
    private String mensagem;

    public GeneralResponse(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
