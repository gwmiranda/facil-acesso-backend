package org.unibrasil.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.unibrasil.entity.Favorito;

import java.util.List;

@ApplicationScoped
public class FavoritoRepository implements PanacheRepository<Favorito> {

    public List<Favorito> buscarFavoritoPorUsuario(long idUsuario) {
        var sql = "SELECT f FROM Favorito f JOIN FETCH f.comentario c JOIN FETCH c.usuario u WHERE f.usuario.id = :idUsuario AND f.dataRemocao IS NULL";
        return find(sql, Parameters.with("idUsuario", idUsuario)).list();
    }
}
