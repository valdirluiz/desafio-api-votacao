package com.valdirluiz.app.votacao.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valdirluiz.app.votacao.entities.*;
import com.valdirluiz.app.votacao.transportlayers.dto.SalvarVotoDTO;

import java.util.ArrayList;
import java.util.List;

public class Factory {

    private final static ObjectMapper objectMapper = new ObjectMapper();

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

    public static String getVotoAsJson() throws JsonProcessingException {
        var dto = new SalvarVotoDTO();
        dto.setCpfAssociado("12312312311");
        dto.setValor(true);
        dto.setIdSessao(1l);
        return objectMapper.writeValueAsString(dto);
    }

    public static String getSessaoAsJson() throws JsonProcessingException {
        var sessao = new Sessao();
        sessao.setIdPauta(1l);
        sessao.setValidadeMinutos(1);
        sessao.setNome("teste");
        return objectMapper.writeValueAsString(sessao);
    }

    public static String getPautaAsJson() throws JsonProcessingException {
        var pauta = new Pauta();
        pauta.setNome("nome");
        return objectMapper.writeValueAsString(pauta);
    }
}
