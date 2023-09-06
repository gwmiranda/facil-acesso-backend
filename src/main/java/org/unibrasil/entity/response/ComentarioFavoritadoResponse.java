package org.unibrasil.entity.response;

import org.unibrasil.entity.Acessibilidade;

import java.time.LocalDateTime;

public class ComentarioFavoritadoResponse {
    private long id;
    private String comentario;
    private Acessibilidade acessibilidade;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataRemocao;

    public ComentarioFavoritadoResponse(long id, String comentario, Acessibilidade acessibilidade, LocalDateTime dataCriacao, LocalDateTime dataRemocao) {
        this.id = id;
        this.comentario = comentario;
        this.acessibilidade = acessibilidade;
        this.dataCriacao = dataCriacao;
        this.dataRemocao = dataRemocao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Acessibilidade getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(Acessibilidade acessibilidade) {
        this.acessibilidade = acessibilidade;
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
}
