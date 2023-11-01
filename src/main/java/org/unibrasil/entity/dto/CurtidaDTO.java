package org.unibrasil.entity.dto;

public class CurtidaDTO {
    private int idComentario;
    private int idUsuario;
    private boolean curtido;

    public CurtidaDTO(int idComentario, int idUsuario, boolean curtido) {
        this.idComentario = idComentario;
        this.idUsuario = idUsuario;
        this.curtido = curtido;
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

    public boolean isCurtido() {
        return curtido;
    }

    public void setCurtido(boolean curtido) {
        this.curtido = curtido;
    }
}
