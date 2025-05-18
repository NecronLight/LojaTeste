<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="pt-BR"> <head>
    <meta charset="UTF-8"> <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Suas transações</title>
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/style.css">
</head>
<body>
<%@include file="header.jsp"%>

<div class="container">
    <div class="mt-5 ms-lg-5 me-lg-5"> <div class="card mb-3">
        <div class="card-header bg-primary text-white"> <i class="bi bi-list-check me-2"></i> LISTA DE TRANSAÇÕES
        </div>
        <div class="card-body">
            <h5 class="card-title"><i class="bi bi-piggy-bank-fill me-2"></i> Gestão do seu Dinheiro</h5> <p class="card-text">Mantenha os dados das suas transações sempre atualizados para um controle financeiro eficiente.</p>

            <table class="table table-striped table-bordered table-hover"> <thead>
            <tr class="table-primary"> <th>Nome</th>
                <th class="text-end">Valor</th>
                <th class="text-end">Quantidade de cotas</th>
                <th class="text-center">Data do Investimento</th> <th class="text-center">Categoria</th>
                <th class="text-center">Ações</th> </tr>
            </thead>
                <tbody>
                <c:forEach items="${transacoes}" var="transacao">
                    <tr>
                        <td>${transacao.nome}</td>
                        <td class="text-end"><fmt:formatNumber value="${transacao.valor}" type="currency"/></td> <td class="text-end">${transacao.quantidade}</td>
                        <td class="text-center">
                            <fmt:parseDate value="${transacao.dataTransacao}" pattern="yyyy-MM-dd" var="dataTransacaoFmt"/>
                            <fmt:formatDate value="${dataTransacaoFmt}" pattern="dd/MM/yyyy"/>
                        </td>
                        <td class="text-center">${transacao.categoria.nome}</td>
                        <td class="text-center">
                            <c:url value="transacoes" var="linkEditar">
                                <c:param name="acao" value="abrir-form-edicao"/>
                                <c:param name="codigo" value="${transacao.codigo}"/>
                            </c:url>
                            <a href="${linkEditar}" class="btn btn-sm btn-primary" title="Editar transação"><i class="bi bi-pencil-fill"></i> Editar</a> <button
                                type="button"
                                class="btn btn-sm btn-danger ms-2" data-bs-toggle="modal"
                                data-bs-target="#excluirModal"
                                onclick="codigoExcluir.value = (${transacao.codigo})"
                                title="Excluir transação"
                        >
                            <i class="bi bi-trash-fill"></i> Excluir </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a href="transacoes?acao=abrir-form-cadastro" class="btn btn-success"><i class="bi bi-plus-circle-fill me-2"></i> Adicionar transação</a> </div>
    </div>
    </div>
</div>

<div
        class="modal fade"
        id="excluirModal"
        tabindex="-1"
        aria-labelledby="excluirModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white"> <h1 class="modal-title fs-5" id="excluirModalLabel"><i class="bi bi-exclamation-triangle-fill me-2"></i> Confirmar Exclusão</h1> <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button> </div>
            <div class="modal-body">
                <h4>Você tem certeza que deseja excluir esta transação?</h4>
                <p class="text-danger"><i class="bi bi-exclamation-circle-fill me-2"></i> <strong>Atenção!</strong> Esta ação é irreversível e os dados serão perdidos.</p> </div>
            <div class="modal-footer">
                <form action="transacoes" method="post">
                    <input type="hidden" name="acao" value="excluir">
                    <input type="hidden" name="codigoExcluir" id="codigoExcluir">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><i class="bi bi-x-circle-fill me-2"></i> Não</button> <button type="submit" class="btn btn-danger"><i class="bi bi-trash-fill me-2"></i> Sim, Excluir</button> </form>
            </div>
        </div>
    </div>
</div>
<%-- Fim do modal de exclusão --%>

<%@include file="footer.jsp"%>

<script src="resources/js/bootstrap.bundle.js"></script>
<script src="resources/js/script.js"></script>
</body>
</html>