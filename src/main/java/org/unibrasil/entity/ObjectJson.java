package org.unibrasil.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface ObjectJson {
    default String toJson() {
        try {
            var ow = new ObjectMapper().writer();
            return ow.writeValueAsString(this);
        } catch (Exception e) {
            throw new IllegalStateException("Ocorreu um erro ao fechar Json", e);
        }
    }
}
