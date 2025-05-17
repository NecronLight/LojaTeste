package br.com.fiap.moneypanda.factory;

import br.com.fiap.moneypanda.dao.CategoriaDao;
import br.com.fiap.moneypanda.dao.TransacaoDao;
import br.com.fiap.moneypanda.dao.UsuarioDao;
import br.com.fiap.moneypanda.dao.impl.OracleCategoriaDao;
import br.com.fiap.moneypanda.dao.impl.OracleTransacaoDao;
import br.com.fiap.moneypanda.dao.impl.OracleUsuarioDao;

public class DaoFactory {

    public static TransacaoDao getTransacaoDao() {
        return new OracleTransacaoDao();
    }

    public static CategoriaDao getCategoriaDao() {
        return new OracleCategoriaDao();
    }

    public static UsuarioDao getUsuarioDao() { return new OracleUsuarioDao(); }

}