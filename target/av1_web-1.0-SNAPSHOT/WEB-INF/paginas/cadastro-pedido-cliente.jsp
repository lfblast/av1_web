<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Av1_web</title>
    </head>
    <body>
        <h1>Escolha um Cliente:</h1>        
        <div>
            <a href="lista-pedidos">Voltar</a>
        </div>        
        <form action="cadastro-pedido-form" method="post">
            <select id="cliente" name="clienteId">
                <option	value="-1">- Clientes -</option>
                <c:forEach items="${clientes}" var="cliente">
                    <option value="${cliente.id}">${cliente.nome} - tel: ${cliente.telefone}</option>
                </c:forEach>
            </select>
            
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
