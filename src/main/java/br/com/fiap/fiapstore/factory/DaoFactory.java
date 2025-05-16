package br.com.fiap.fiapstore.factory;

import br.com.fiap.fiapstore.dao.CategoriaDao;
import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.dao.impl.OracleCategoriaDao;
import br.com.fiap.fiapstore.dao.impl.OracleProdutoDao;

public class DaoFactory {

    public static ProdutoDao getProdutoDao() {
        return new OracleProdutoDao();
    }

    public static CategoriaDao getCategoriaDao() {
        return new OracleCategoriaDao();
    }

}