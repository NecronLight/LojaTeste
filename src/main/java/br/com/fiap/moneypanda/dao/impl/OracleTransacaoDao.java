package br.com.fiap.moneypanda.dao.impl;

import br.com.fiap.moneypanda.dao.ConnectionManager;
import br.com.fiap.moneypanda.dao.TransacaoDao;
import br.com.fiap.moneypanda.exception.DBException;
import br.com.fiap.moneypanda.model.Categoria;
import br.com.fiap.moneypanda.model.Transacao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleTransacaoDao implements TransacaoDao {

    private Connection conexao;

    @Override
    public void cadastrar(Transacao transacao) throws DBException {

        PreparedStatement stmt = null;

        conexao = ConnectionManager.getInstance().getConnection();

        String sql = "INSERT INTO TB_TRANSACAO " +
                "(COD_TRANSACAO, NOME_TRANSACAO, VALOR_TRANSACAO, " +
                "QTDE_TRANSACAO, DATA_TRANSACAO, COD_CATEGORIA) " +
                "VALUES (SQ_TB_TRANSACAO.NEXTVAL, ?, ?, ?, ?, ?)";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, transacao.getNome());
            stmt.setDouble(2, transacao.getValor());
            stmt.setInt(3, transacao.getQuantidade());
            stmt.setDate(4, Date.valueOf(transacao.getDataTransacao()));
            stmt.setInt(5, transacao.getCategoria().getCodigo());
            stmt.executeUpdate();

            System.out.println("Transação cadastrada com sucesso!");

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
    public void atualizar(Transacao transacao) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE TB_TRANSACAO SET " +
                    "NOME_TRANSACAO = ?, " +
                    "VALOR_TRANSACAO = ?, " +
                    "QTDE_TRANSACAO = ?, " +
                    "DATA_TRANSACAO = ?, " +
                    "COD_CATEGORIA = ? " +
                    "WHERE COD_TRANSACAO = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, transacao.getNome());
            stmt.setDouble(2, transacao.getValor());
            stmt.setInt(3, transacao.getQuantidade());
            stmt.setDate(4, Date.valueOf(transacao.getDataTransacao()));
            stmt.setInt(5, transacao.getCategoria().getCodigo());
            stmt.setInt(6, transacao.getCodigo());
            stmt.executeUpdate();

            System.out.println("Transação atualizada com sucesso!");

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
            String sql = "DELETE FROM TB_TRANSACAO WHERE COD_TRANSACAO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();

            System.out.println("Transação removida com sucesso!");
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
    public Transacao buscar(int id) {

        Transacao transacao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM TB_TRANSACAO " +
                    "INNER JOIN TB_CATEGORIA " +
                    "ON TB_TRANSACAO.COD_CATEGORIA = TB_CATEGORIA.COD_CATEGORIA " +
                    "WHERE TB_TRANSACAO.COD_TRANSACAO = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()){
                int codigo = rs.getInt("COD_TRANSACAO");
                String nome = rs.getString("NOME_TRANSACAO");
                double valor = rs.getDouble("VALOR_TRANSACAO");
                int qtd = rs.getInt("QTDE_TRANSACAO");
                LocalDate data = rs.getDate("DATA_TRANSACAO").toLocalDate();
                int codigoCategoria = rs.getInt("COD_CATEGORIA");
                String nomeCategoria = rs.getString("NOME_CATEGORIA");

                transacao = new Transacao(codigo, nome, valor, qtd, data);

                Categoria categoria = new Categoria(codigoCategoria, nomeCategoria);

                transacao.setCategoria(categoria);
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
        return transacao;
    }

    @Override
    public List<Transacao> listar() {

        List<Transacao> lista = new ArrayList<Transacao>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM TB_TRANSACAO " +
                    "INNER JOIN TB_CATEGORIA " +
                    "ON TB_TRANSACAO.COD_CATEGORIA = TB_CATEGORIA.COD_CATEGORIA";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            //Percorre todos os registros encontrados
            while (rs.next()) {
                int codigo = rs.getInt("COD_TRANSACAO");
                String nome = rs.getString("NOME_TRANSACAO");
                double valor = rs.getDouble("VALOR_TRANSACAO");
                int qtd = rs.getInt("QTDE_TRANSACAO");
                java.sql.Date data = rs.getDate("DATA_TRANSACAO");
                LocalDate dataTransacao = rs.getDate("DATA_TRANSACAO")
                        .toLocalDate();
                int codigoCategoria = rs.getInt("COD_CATEGORIA");
                String nomeCategoria = rs.getString("NOME_CATEGORIA");

                Transacao transacao = new Transacao (
                        codigo, nome, valor, qtd, dataTransacao
                );

                Categoria categoria = new Categoria(
                        codigoCategoria, nomeCategoria
                );
                transacao.setCategoria(categoria);

                lista.add(transacao);

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
