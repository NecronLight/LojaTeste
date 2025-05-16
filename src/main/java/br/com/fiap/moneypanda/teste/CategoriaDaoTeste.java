package br.com.fiap.moneypanda.teste;

import br.com.fiap.moneypanda.dao.CategoriaDao;
import br.com.fiap.moneypanda.factory.DaoFactory;
import br.com.fiap.moneypanda.model.Categoria;

import java.util.List;

public class CategoriaDaoTeste {

    public static void main(String[] args) {
        CategoriaDao dao = DaoFactory.getCategoriaDao();

        List<Categoria> lista = dao.listar();
        for (Categoria categoria : lista) {
            System.out.println(categoria.getCodigo() + " " + categoria.getNome());
        }
    }
}