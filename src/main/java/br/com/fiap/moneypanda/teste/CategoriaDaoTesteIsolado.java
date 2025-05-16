package br.com.fiap.moneypanda.teste;

import br.com.fiap.moneypanda.dao.CategoriaDao;
import br.com.fiap.moneypanda.factory.DaoFactory;
import br.com.fiap.moneypanda.model.Categoria;
import java.util.List;

public class CategoriaDaoTesteIsolado {
    public static void main(String[] args) {
        CategoriaDao categoriaDao = DaoFactory.getCategoriaDao();
        try {
            List<Categoria> categorias = categoriaDao.listar();
            System.out.println("Categorias encontradas:");
            if (categorias.isEmpty()) {
                System.out.println("Nenhuma categoria encontrada.");
            } else {
                for (Categoria categoria : categorias) {
                    System.out.println(categoria.getCodigo() + " - " + categoria.getNome());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar categorias:");
            e.printStackTrace();
        }
    }
}