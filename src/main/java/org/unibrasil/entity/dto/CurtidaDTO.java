package org.unibrasil.entity.dto;

public class CurtidaDTO {
    private int idComentario;
    private int idUsuario;

    public CurtidaDTO(int idComentario, int idUsuario) {
        this.idComentario = idComentario;
        this.idUsuario = idUsuario;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
