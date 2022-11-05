package com.valdirluiz.app.votacao.entities;

import com.valdirluiz.app.votacao.exceptions.BadRequestException;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "pauta")
@Access(AccessType.FIELD)
public class Pauta {
    @Id
    @Column(name = "id_pauta")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_pauta")
    private String nome;

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

    public Apuracao getApuracao() {
        return apuracao;
    }

    public void setApuracao(Apuracao apuracao) {
        this.apuracao = apuracao;
    }

    public void validarCampos() {
        if(Objects.isNull(nome) || nome.isBlank()){
            throw new BadRequestException("É obrigatório informar o campo 'nome'");
        }
    }
}
