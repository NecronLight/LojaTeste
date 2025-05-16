<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edição de Transações</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <div class="mt-5 ms-5 me-5">
        <div class="card mb-3">
            <div class="card-header">
                ATUALIZAR TRANSAÇÃO
            </div>

            <c:if test="${not empty mensagem}">
                <div class="alert alert-success ms-2 me-2 mt-2">${mensagem}</div>
            </c:if>

            <c:if test="${not empty erro}">
                <div class="alert alert-danger ms-2 me-2 mt-2">${erro}</div>
            </c:if>

            <div class="card-body">
                <form action="transacoes?acao=editar" method="post">

                    <input type="hidden" value="${transacao.codigo}" name="codigo">


                    <div class="form-group">
                        <label for="id-nome">Nome</label>
                        <input type="text" name="nome" id="id-nome" class="form-control" value="${transacao.nome}">
                    </div>

                    <div class="form-group">
                        <label for="id-valor">Valor</label>
                        <input type="text" name="valor" id="id-valor" class="form-control" value="${transacao.valor}">
                    </div>

                    <div class="form-group">
                        <label for="id-quantidade">Quantidade</label>
                        <input type="text" name="quantidade" id="id-quantidade" class="form-control" value="${transacao.quantidade}">
                    </div>

                    <div class="form-group">
                        <label for="id-data-transacao">Data da Transação</label>
                        <input type="date" name="data-transacao" id="id-data-transacao" class="form-control" value="${transacao.dataTransacao}">
                    </div>

                    <div class="form-group">
                        <label
                                for="id-categoria">Categoria
                        </label>
                        <select
                                name="categoria"
                                id="id-categoria"
                                class="form-control"
                        >
                            <option value="0">Selecione</option>
                            <c:forEach items="${categorias}" var="c">
                                <option value="${c.codigo }" >${c.nome }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <input type="submit" value="Salvar" class="btn btn-primary mt-3">
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>
