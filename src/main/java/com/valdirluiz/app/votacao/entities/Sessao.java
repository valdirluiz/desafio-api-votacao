package com.valdirluiz.app.votacao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valdirluiz.app.votacao.exceptions.BadRequestException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "sessao")
@Access(AccessType.FIELD)
public class Sessao {

    @Id
    @Column(name = "id_sessao")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_sessao")
    private String nome;

    @Column(name = "id_pauta")
    private Long idPauta;

    @Column(name = "data_validade")
    private LocalDateTime dataValidade;

    @Transient
    @JsonIgnore
    private Integer validadeMinutos;

    @Transient
    private Apuracao apuracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDateTime dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Integer getValidadeMinutos() {
        return validadeMinutos;
    }

    public void setValidadeMinutos(Integer validadeMinutos) {
        this.validadeMinutos = validadeMinutos;
    }

    public Apuracao getApuracao() {
        return apuracao;
    }

    public void setApuracao(Apuracao apuracao) {
        this.apuracao = apuracao;
    }

    public void validarCampos() {
        if(Objects.isNull(this.nome) || this.nome.isBlank()){
            throw new BadRequestException("É obrigatório informar o nome da sessão");
        }

        if(Objects.isNull(this.idPauta)){
            throw new BadRequestException("É obrigatório informar a pauta da sessão");
        }
    }

    public void calcularValidade() {
        this.dataValidade = LocalDateTime.now().plusMinutes(this.validadeMinutos == null || this.validadeMinutos == 0
                ? 1 : this.validadeMinutos);
    }

    public void validaExpiracao() {
        if(LocalDateTime.now().isAfter(dataValidade)){
            throw new BadRequestException("Sessão expirada");
        }
    }
}
