package br.com.fiap.moneypanda.dao;

import br.com.fiap.moneypanda.exception.DBException;
import br.com.fiap.moneypanda.model.Transacao;

import java.util.List;

public interface TransacaoDao {

    void cadastrar(Transacao transacao) throws DBException;
    void atualizar(Transacao transacao) throws DBException;
    void remover(int codigo) throws DBException;
    Transacao buscar(int codigo);
    List<Transacao> listar();
}
