package org.unibrasil.entity.response;

import org.unibrasil.entity.Comentario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ComentarioFavoritadoResponse {
    private long id;
    private String comentario;
    private List<AcessibilidadeResponse> acessibilidade;
    private int curtidas;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataRemocao;

    public ComentarioFavoritadoResponse(Comentario comentario) {
        this.id = comentario.getId();
        this.comentario = comentario.getComentario();
        this.acessibilidade = comentario.getAcessibilidades().stream().map(AcessibilidadeResponse::new).collect(Collectors.toList());
        this.dataCriacao = comentario.getDataCriacao();
        this.dataRemocao = comentario.getDataRemocao();
        this.curtidas = comentario.getCurtidas().size();
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

    public List<AcessibilidadeResponse> getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(List<AcessibilidadeResponse> acessibilidade) {
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

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }
}
