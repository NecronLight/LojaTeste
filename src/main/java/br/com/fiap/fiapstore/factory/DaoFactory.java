package br.com.fiap.fiapstore.factory;

import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.dao.impl.OracleProdutoDao;

public class DaoFactory {

    public static ProdutoDao getProdutoDAO() {
        return new OracleProdutoDao();
    }

}