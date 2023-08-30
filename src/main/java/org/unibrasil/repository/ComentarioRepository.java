package org.unibrasil.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.unibrasil.entity.Comentario;

import java.util.List;

@ApplicationScoped
public class ComentarioRepository implements PanacheRepository<Comentario> {
   
    public List<Comentario> buscarTodos() {
        var sql = "SELECT c FROM Comentario c WHERE c.dataRemocao IS NULL";
        return find(sql).stream().toList();
    }
}
