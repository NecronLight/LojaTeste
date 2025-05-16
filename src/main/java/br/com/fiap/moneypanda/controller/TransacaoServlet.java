package br.com.fiap.moneypanda.controller;

import br.com.fiap.moneypanda.dao.CategoriaDao;
import br.com.fiap.moneypanda.dao.TransacaoDao;
import br.com.fiap.moneypanda.exception.DBException;
import br.com.fiap.moneypanda.factory.DaoFactory;
import br.com.fiap.moneypanda.model.Categoria;
import br.com.fiap.moneypanda.model.Transacao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/transacoes")
public class TransacaoServlet extends HttpServlet {

    private TransacaoDao dao;
    private CategoriaDao categoriaDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DaoFactory.getTransacaoDao();
        categoriaDao = DaoFactory.getCategoriaDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String acao = req.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "editar":
                editar(req, resp);
                break;
            case "excluir":
                excluir(req, resp);
                break;
            }
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int codigo = Integer.parseInt(req.getParameter("codigoExcluir"));

        try {
            dao.remover(codigo);
            req.setAttribute("mensagem", "Produto removido!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        }

        listar(req,resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        double valor = Double.valueOf(req.getParameter("valor"));
        int quantidade = Integer.valueOf(req.getParameter("quantidade"));
        LocalDate dataTransacao = LocalDate.parse(req.getParameter("dataTransacao"));
        int codigoCategoria = Integer.parseInt(req.getParameter("categoria"));

        Categoria categoria = new Categoria();
        categoria.setCodigo(codigoCategoria);

        Transacao transacao = new Transacao (
                0,
                nome,
                valor,
                quantidade,
                dataTransacao
        );

        transacao.setCategoria(categoria);

        try {
            dao.cadastrar(transacao);
            req.setAttribute("mensagem", "Transação cadastrado com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar a transação");
        }

        abrirFormCadastro(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigo = Integer.parseInt(req.getParameter("codigo"));
            String nome = req.getParameter("nome");
            double valor = Double.parseDouble(req.getParameter("valor"));
            int quantidade = Integer.parseInt(req.getParameter("quantidade"));
            LocalDate dataTransacao = LocalDate
                    .parse(req.getParameter("dataTransacao"));

            int codigoCategoria = Integer.parseInt(req.getParameter("categoria"));

            Categoria categoria = new Categoria();
            categoria.setCodigo(codigoCategoria);

            Transacao transacao = new Transacao(codigo, nome, valor, quantidade, dataTransacao);
            transacao.setCategoria(categoria);

            dao.atualizar(transacao);

                req.setAttribute("mensagem", "Transação atualizada!");
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados");
        }
        listar(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao){
            case "listar":
                listar(req, resp);
                break;
            case "abrir-form-edicao":
                abrirForm(req, resp);
                break;
            case "abrir-form-cadastro":
                abrirFormCadastro(req, resp);
                break;
        }

    }

    private void abrirFormCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        carregarOpcoesCategoria(req);
        req.getRequestDispatcher("cadastro-transacao.jsp").forward(req, resp);
    }

    private void carregarOpcoesCategoria(HttpServletRequest req) {
        List<Categoria> lista = categoriaDao.listar();
        System.out.println("Número de categorias carregadas: " + lista.size()); // Adicione este log
        req.setAttribute("categorias", lista);
    }

    private void abrirForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("codigo"));
        Transacao transacao = dao.buscar(id);
        req.setAttribute("transacao", transacao);
        carregarOpcoesCategoria(req);
        req.getRequestDispatcher("editar-transacao.jsp").forward(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Transacao> lista = dao.listar();
        req.setAttribute("transacoes", lista);
        req.getRequestDispatcher("lista-transacao.jsp").forward(req, resp);
    }
}