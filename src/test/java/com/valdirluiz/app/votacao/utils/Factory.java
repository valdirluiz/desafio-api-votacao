package com.valdirluiz.app.votacao.utils;

import com.valdirluiz.app.votacao.entities.*;

import java.util.ArrayList;
import java.util.List;

public class Factory {

    public static Pauta getPauta(){
        var pauta = new Pauta();
        pauta.setId(1l);
        pauta.setApuracao(new Apuracao(1l, 2l));
        pauta.setNome("testes");
        return pauta;
    }

    public static List<Pauta> getPautas() {
        var pautas = new ArrayList<Pauta>();
        pautas.add(getPauta());
        return pautas;
    }

    public static Sessao getSessao() {
        var sessao = new Sessao();
        sessao.setApuracao(new Apuracao(1l, 2l));
        sessao.setId(1l);
        sessao.setIdPauta(1l);
        sessao.setNome("teste");
        sessao.setValidadeMinutos(3);
        sessao.calcularValidade();
        return sessao;
    }

    public static List<Sessao> getSessoes() {
        var sessoes = new ArrayList<Sessao>();
        sessoes.add(getSessao());
        return sessoes;
    }

    public static CpfStatus statusCpf() {
        var statusCpf = new CpfStatus();
        statusCpf.setStatus("ABLE_TO_VOTE");
        return statusCpf;
    }

    public static Voto getVoto() {
        var voto = new Voto();
        voto.setIdPauta(1l);
        voto.setValor(true);
        voto.setIdSessao(1l);
        voto.setCpfAssociado("12312312311");
        return voto;
    }
}
