package org.unibrasil.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.unibrasil.entity.Curtida;

@ApplicationScoped
public class CurtidaRepository implements PanacheRepository<Curtida> {
    public Curtida buscarPorUsuarioEComentario(Curtida curtida) {
        String jpql = "SELECT c FROM Curtida c WHERE c.usuario.id = :idUsuario AND c.comentario.id = :idComentario";

        return find(
                jpql,
                Parameters.with("idUsuario", curtida.getUsuario().getId()).and("idComentario", curtida.getComentario().getId()))
                .firstResult();
    }
}
