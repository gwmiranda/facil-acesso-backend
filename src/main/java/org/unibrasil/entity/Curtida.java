package org.unibrasil.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.unibrasil.entity.dto.CurtidaDTO;

import java.time.LocalDateTime;

@Entity
public class Curtida {

    @Id
    @ManyToOne
    @JoinColumn(name = "comentario_id")
    private Comentario comentario;

    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataRemocao;

    public Curtida() {
    }

    public Curtida(Comentario comentario, Usuario usuario, LocalDateTime dataCriacao, LocalDateTime dataRemocao) {
        this.comentario = comentario;
        this.usuario = usuario;
        this.dataCriacao = dataCriacao;
        this.dataRemocao = dataRemocao;
    }

    public Curtida(CurtidaDTO curtidaDTO) {
        this.comentario = new Comentario(curtidaDTO.getIdComentario());
        this.usuario = new Usuario(curtidaDTO.getIdUsuario());
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
