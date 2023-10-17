package org.unibrasil.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
import org.unibrasil.entity.TipoEstabelecimento;
import org.unibrasil.repository.TipoEstabelecimentoRepository;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class TipoEstabelecimentoService {

    @Inject
    TipoEstabelecimentoRepository repository;

    @Transactional
    public void gravarTipoEstabelecimento(TipoEstabelecimento tipoEstabelecimento) throws ValidationException {
        if (tipoEstabelecimento.getDescricao().isBlank()) {
            throw new ValidationException("Estabelecimento está vazio!");
        }

        if (tipoEstabelecimento.getIcon().isBlank()) {
            throw new ValidationException("Icon está vazio!");
        }

        var tipoEstabelecimentoBuscada = repository.buscarAcessiblidadePorDescricao(tipoEstabelecimento.getDescricao());

        if (tipoEstabelecimentoBuscada.isPresent()) {
            throw new ValidationException("Estabelecimento já cadastrado!");
        }

        tipoEstabelecimento.setDataCriacao(LocalDateTime.now());
        repository.persist(tipoEstabelecimento);
    }

    @Transactional
    public List<TipoEstabelecimento> buscarTodosTiposEstabelecimentos() {
        return repository.buscarTodos();
    }

    @Transactional
    public TipoEstabelecimento buscarPorId(long id) throws ValidationException {
        var tipoEstabelecimento = repository.findById(id);

        if (tipoEstabelecimento == null) {
            throw new ValidationException("TipoEstabelecimento não encontrado");
        }

        return tipoEstabelecimento;
    }

    @Transactional
    public TipoEstabelecimento atualizar(long id, TipoEstabelecimento tipoEstabelecimento) throws ValidationException {
        if (tipoEstabelecimento.getDescricao().isBlank()) {
            throw new ValidationException("Descrição está vazia");
        }

        var tipoEstabelecimentoBuscada = buscarPorId(id);

        tipoEstabelecimentoBuscada.setDescricao(tipoEstabelecimento.getDescricao());
        tipoEstabelecimentoBuscada.setDataEdicao(LocalDateTime.now());
        repository.persist(tipoEstabelecimentoBuscada);

        return buscarPorId(id);
    }

    @Transactional
    public void deletarPorId(long id) throws ValidationException {
        var tipoEstabelecimento = repository.findById(id);

        if (tipoEstabelecimento == null) {
            throw new ValidationException("Tipo de estabelecimento não encontrada para deleção");
        }

        tipoEstabelecimento.setDataRemocao(LocalDateTime.now());
        repository.persist(tipoEstabelecimento);
    }
}
