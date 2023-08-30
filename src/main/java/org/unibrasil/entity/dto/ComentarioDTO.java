package org.unibrasil.entity.dto;

public class ComentarioDTO {
    private Long idComentario;
    private long estabelecimentoId;
    private String complemento;
    private String rua;
    private int numero;
    private String bairro;
    private String estado;
    private String cidade;
    private String cep;
    private Integer nivelSatisfacao;
    private String comentario;
    private long usuario;
    private long acessibilidade;

    public Long getIdComentario() {
        return idComentario;
    }

    public long getEstabelecimentoId() {
        return estabelecimentoId;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getRua() {
        return rua;
    }

    public int getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCep() {
        return cep;
    }

    public Integer getNivelSatisfacao() {
        return nivelSatisfacao;
    }

    public String getComentario() {
        return comentario;
    }

    public long getUsuario() {
        return usuario;
    }

    public long getAcessibilidade() {
        return acessibilidade;
    }
}
