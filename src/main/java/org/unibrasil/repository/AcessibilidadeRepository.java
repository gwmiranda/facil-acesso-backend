package org.unibrasil.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.unibrasil.entity.Acessibilidade;
import org.unibrasil.entity.Usuario;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AcessibilidadeRepository implements PanacheRepository<Acessibilidade> {

    public List<Acessibilidade> buscarTodos() {
        var sql = "SELECT a FROM Acessibilidade a WHERE a.dataRemocao IS NULL";
        return find(sql).stream().toList();
    }

    public Optional<Acessibilidade> buscarAcessiblidadePorDescricao(String descricao) {
        var sql = "SELECT a FROM Acessibilidade a WHERE a.descricao = ?1 AND a.dataRemocao IS NULL";
        return find(sql, descricao).singleResultOptional();
    }
}
