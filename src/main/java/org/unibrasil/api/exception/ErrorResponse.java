package org.unibrasil.api.exception;

import org.unibrasil.entity.ObjectJson;

import java.time.Instant;
import java.util.Objects;

public class ErrorResponse implements ObjectJson {
    private String mensagem;
    private Instant instant;

    public ErrorResponse(String mensagem) {
        this.mensagem = mensagem;
        this.instant = Instant.now();
    }

    public String getMensagem() {
        return mensagem;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    @Override
    public String toJson() {
        return ObjectJson.super.toJson();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(mensagem, that.mensagem) && Objects.equals(instant, that.instant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mensagem, instant);
    }
}
