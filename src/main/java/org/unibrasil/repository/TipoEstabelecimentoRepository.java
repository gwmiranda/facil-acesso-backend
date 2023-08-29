package org.unibrasil.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.unibrasil.entity.TipoEstabelecimento;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TipoEstabelecimentoRepository implements PanacheRepository<TipoEstabelecimento> {

    public List<TipoEstabelecimento> buscarTodos() {
        var sql = "SELECT te FROM TipoEstabelecimento te WHERE te.dataRemocao IS NULL";
        return find(sql).stream().toList();
    }

    public Optional<TipoEstabelecimento> buscarAcessiblidadePorDescricao(String descricao) {
        var sql = "SELECT te FROM TipoEstabelecimento te WHERE te.descricao = ?1 AND te.dataRemocao IS NULL";
        return find(sql, descricao).singleResultOptional();
    }
}
