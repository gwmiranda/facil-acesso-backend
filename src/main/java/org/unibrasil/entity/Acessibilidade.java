package org.unibrasil.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Acessibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataRemocao;
    private LocalDateTime dataEdicao;
    private String icon;

    @ManyToMany(mappedBy = "acessibilidades")
    private List<Usuario> usuario;

    @ManyToMany(mappedBy = "acessibilidades")
    private List<Comentario> comentarios = new ArrayList<>();

    public Acessibilidade() {
    }

    public Acessibilidade(long id) {
        this.id = id;
    }

    public Acessibilidade(String descricao, String icon) {
        this.descricao = descricao;
        this.icon = icon;
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataRemocao() {
        return dataRemocao;
    }

    public void setDataRemocao(LocalDateTime dataRemocao) {
        this.dataRemocao = dataRemocao;
    }

    public LocalDateTime getDataEdicao() {
        return dataEdicao;
    }

    public void setDataEdicao(LocalDateTime dataEdicao) {
        this.dataEdicao = dataEdicao;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
