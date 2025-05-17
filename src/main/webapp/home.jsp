<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <title>Money Panda - Home</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./resources/css/bootstrap.css">
  <link rel="stylesheet" href="./resources/css/home.css">
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
<%@include file="footer.jsp" %>
<script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>