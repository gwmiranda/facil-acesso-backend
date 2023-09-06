package org.unibrasil.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.unibrasil.entity.Comentario;

import java.util.List;

@ApplicationScoped
public class ComentarioRepository implements PanacheRepository<Comentario> {

    public List<Comentario> buscarComentariosPorUsuario(long idUsuario) {
        String jpql = "SELECT c FROM Comentario c " +
                "WHERE c.usuario.id = :idUsuario AND c.dataRemocao IS NULL";

        return find(jpql, Parameters.with("idUsuario", idUsuario)).list();
    }

    public List<Comentario> buscarComentariosFavoritadosPorUsuario(long idUsuario) {
        String jpql = "SELECT c FROM Comentario c " +
                "JOIN c.favoritos f " +
                "WHERE f.usuario.id = :idUsuario AND f.dataRemocao IS NULL";

        return find(jpql, Parameters.with("idUsuario", idUsuario)).list();
    }
}
