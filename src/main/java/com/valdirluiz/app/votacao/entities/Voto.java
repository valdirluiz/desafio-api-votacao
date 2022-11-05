package com.valdirluiz.app.votacao.entities;

import com.valdirluiz.app.votacao.exceptions.BadRequestException;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "voto")
@Access(AccessType.FIELD)
public class Voto {

    @Id
    @Column(name = "id_voto")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf_associado")
    private String cpfAssociado;

    @Column(name = "valor_voto")
    private Boolean valor;

    @Column(name = "id_sessao")
    private Long idSessao;

    @Column(name = "id_pauta")
    private Long idPauta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfAssociado() {
        return cpfAssociado;
    }

    public void setCpfAssociado(String cpfAssociado) {
        this.cpfAssociado = cpfAssociado;
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

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    public void validarCampos() {
        if(Objects.isNull(cpfAssociado) || cpfAssociado.isBlank()){
            throw new BadRequestException("É obrigatório informar o cpf do associado");
        }

        if(Objects.isNull(idSessao) || idSessao.equals(0l)){
            throw new BadRequestException("É obrigatório informar o id da sessão");
        }

        if(Objects.isNull(valor)){
            throw new BadRequestException("É obrigatório informar o valor do voto");
        }
    }
}
