package br.com.fiap.moneypanda.teste;

import br.com.fiap.moneypanda.dao.TransacaoDao;
import br.com.fiap.moneypanda.exception.DBException;
import br.com.fiap.moneypanda.factory.DaoFactory;
import br.com.fiap.moneypanda.model.Transacao;

import java.time.LocalDate;
import java.util.List;

public class TransacaoDaoTeste {

    public static void main(String[] args) {

        // Cadastrar um produto
       TransacaoDao dao = DaoFactory.getTransacaoDao();

        Transacao transacao = new Transacao(
               0,
               "Teclado ABNT2",
               28.99,
               200,
               LocalDate.of(2024, 6, 10)
       );

        try {
            dao.cadastrar(transacao);
        } catch (DBException e) {
            throw new RuntimeException(e);
       }

        // Buscar um produto pelo código e atualizar
        transacao = dao.buscar(1);
        transacao.setNome("Mouse Microsoft Wirelles");
        transacao.setValor(119.99);
//
//        try {
//            dao.atualizar(produto);
//        } catch (DBException e) {
//            e.printStackTrace();
//        }

        //Listar os produtos
        List<Transacao> lista = dao.listar();
        for(Transacao item : lista) {
            System.out.println(
                    item.getNome() + " " +
                            item.getQuantidade() + " " +
                            item.getValor());
        }

        //Remover um produto
        try {
            dao.remover(1);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
