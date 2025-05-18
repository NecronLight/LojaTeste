<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <title>Money Panda - Home</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./resources/css/bootstrap.css">
  <link rel="stylesheet" href="./resources/css/home.css">
  <style>
    .fixed-bottom-right {
      position: fixed;
      bottom: 20px;
      right: 20px;
      background-color: #f8d7da; /* Cor de fundo vermelha clara */
      color: #721c24; /* Cor do texto vermelha escura */
      padding: 15px;
      border: 1px solid #f5c6cb;
      border-radius: 5px;
      box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.3);
      z-index: 1000; /* Garante que a caixa fique acima de outros elementos */
    }
    .fixed-bottom-right strong {
      font-weight: bold;
    }
  </style>
</head>

<body>
<%@include file="header.jsp" %>
<div class="container">
  <c:if test="${empty user}">
    <div class="message-box">
      <h1 class="not-authenticated-title">Você não está autenticado!</h1>
      <h5 class="text-muted not-authenticated-subtitle">Para acessar o sistema, identifique-se!</h5>
    </div>
  </c:if>
  <c:if test="${not empty user}">
    <div class="message-box">
      <h1 class="welcome-title">Bem-vindo ao Money Panda!</h1>
      <h5 class="welcome-subtitle">Seu login foi efetuado com sucesso! Explore o sistema.</h5>
    </div>
  </c:if>
</div>

<div class="fixed-bottom-right">
  <strong>Informação de Teste:</strong> Para testar as funcionalidades utilize esse Login: <br>
  <strong>Email:</strong> moneypanda@gmail.com <br>
  <strong>Senha:</strong> 123456
</div>

<%@include file="footer.jsp" %>
<script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>