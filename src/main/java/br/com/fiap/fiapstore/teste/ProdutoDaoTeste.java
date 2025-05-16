package br.com.fiap.fiapstore.teste;

import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.exception.DBException;
import br.com.fiap.fiapstore.factory.DaoFactory;
import br.com.fiap.fiapstore.model.Produto;

import java.time.LocalDate;

public class ProdutoDaoTeste {

    public static void main(String[] args) {

        // Cadastrar um produto
        ProdutoDao dao = DaoFactory.getProdutoDAO();

        Produto produto = new Produto(
                0,
                "Teclado ABNT2",
                28.99,
                200,
                LocalDate.of(2024, 6, 10)
        );

        try {
            dao.cadastrar(produto);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }

        // Buscar um produto pelo código e atualizar
        produto = dao.buscar(1);
        produto.setNome("Monitor LED 24P");
        produto.setValor(891.99);

        try {
            dao
        }



    }
}
