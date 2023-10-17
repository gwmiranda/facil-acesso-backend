package org.unibrasil.entity.response;

import org.unibrasil.entity.Acessibilidade;

public class AcessibilidadeResponse {

    private long id;

    private String descricao;

    private String icon;

    public AcessibilidadeResponse() {
    }

    public AcessibilidadeResponse(Acessibilidade acessibilidade) {
        this.id = acessibilidade.getId();
        this.descricao = acessibilidade.getDescricao();
        this.icon = acessibilidade.getIcon();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
