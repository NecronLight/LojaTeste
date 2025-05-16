package br.com.fiap.fiapstore.dao.impl;

import br.com.fiap.fiapstore.dao.ConnectionManager;
import br.com.fiap.fiapstore.dao.ProdutoDao;
import br.com.fiap.fiapstore.exception.DBException;
import br.com.fiap.fiapstore.model.Categoria;
import br.com.fiap.fiapstore.model.Produto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleProdutoDao implements ProdutoDao {

    private Connection conexao;

    @Override
    public void cadastrar(Produto produto) throws DBException {

        PreparedStatement stmt = null;

        conexao = ConnectionManager.getInstance().getConnection();

        String sql = "INSERT INTO TB_PRODUTO " +
                "(COD_PRODUTO, NOME_PRODUTO, VALOR_PRODUTO, " +
                "QTDE_PRODUTO, DATA_FABRICACAO, COD_CATEGORIA) " +
                "VALUES (SQ_TB_PRODUTO.NEXTVAL, ?, ?, ?, ?, ?)";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValor());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setDate(4, Date.valueOf(produto.getDataFabricacao()));
            stmt.setInt(5, produto.getCategoria().getCodigo());
            stmt.executeUpdate();

            System.out.println("Produto cadastrado com sucesso!");

        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar."+ e.getMessage());

        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void atualizar(Produto produto) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE TB_PRODUTO SET " +
                    "NOME_PRODUTO = ?, " +
                    "VALOR_PRODUTO = ?, " +
                    "QTDE_PRODUTO = ?, " +
                    "DATA_FABRICACAO = ?, " +
                    "COD_CATEGORIA = ? " +
                    "WHERE COD_PRODUTO = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValor());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setDate(4, Date.valueOf(produto.getDataFabricacao()));
            stmt.setInt(5, produto.getCategoria().getCodigo());
            stmt.setInt(6, produto.getCodigo());
            stmt.executeUpdate();

            System.out.println("Produto atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar.");
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void remover(int codigo) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM TB_PRODUTO WHERE COD_PRODUTO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();

            System.out.println("Produto removido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover.");
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Produto buscar(int id) {

        Produto produto = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM TB_PRODUTO " +
                    "INNER JOIN TB_CATEGORIA " +
                    "ON TB_PRODUTO.COD_CATEGORIA = TB_CATEGORIA.COD_CATEGORIA " +
                    "WHERE TB_PRODUTO.COD_PRODUTO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigo = rs.getInt("COD_PRODUTO");
                String nome = rs.getString("NOME_PRODUTO");
                double valor = rs.getDouble("VALOR_PRODUTO");
                int qtd = rs.getInt("QTDE_PRODUTO");
                LocalDate data = rs.getDate("DATA_FABRICACAO").toLocalDate();
                int codigoCategoria = rs.getInt("COD_CATEGORIA");
                String nomeCategoria = rs.getString("NOME_CATEGORIA");

                produto = new Produto(codigo, nome, valor, qtd, data);

                Categoria categoria = new Categoria(codigoCategoria, nomeCategoria);

                produto.setCategoria(categoria);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return produto;
    }

    @Override
    public List<Produto> listar() {

        List<Produto> lista = new ArrayList<Produto>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM TB_PRODUTO " +
                    "INNER JOIN TB_CATEGORIA " +
                    "ON TB_PRODUTO.COD_CATEGORIA = TB_CATEGORIA.COD_CATEGORIA";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            //Percorre todos os registros encontrados
            while (rs.next()) {
                int codigo = rs.getInt("COD_PRODUTO");
                String nome = rs.getString("NOME_PRODUTO");
                double valor = rs.getDouble("VALOR_PRODUTO");
                int qtd = rs.getInt("QTDE_PRODUTO");
                java.sql.Date data = rs.getDate("DATA_FABRICACAO");
                LocalDate dataFabricacao = rs.getDate("DATA_FABRICACAO")
                        .toLocalDate();
                int codigoCategoria = rs.getInt("COD_CATEGORIA");
                String nomeCategoria = rs.getString("NOME_CATEGORIA");

                Produto produto = new Produto(
                        codigo, nome, valor, qtd, dataFabricacao
                );

                Categoria categoria = new Categoria(
                        codigoCategoria, nomeCategoria
                );
                produto.setCategoria(categoria);

                lista.add(produto);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

}
