<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Av1_web</title>
    </head>

    <body>
        <h1>Lista de Pedidos:</h1>
        <div>
            <a href="cadastro-pedido-cliente" title="Incluir Pedido">Incluir Pedido</a>
        </div>
    <c:if test = "${pedidos != null}">
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Endereço</th>
                        <th>Entrega</th>
                        <th>Taxa de Entrega</th>
                        <th>Troco</th>
                        <th>Valor</th>
                        <th>Data/hora</th>
                        <th>Status</th>
                    </tr>
                <c:forEach items="${pedidos}" var="pedido">
                    <tr>
                        <td>${pedido.id}</td>
                        <td>${pedido.endereco.rua}</td>
                    </tr>
                </c:forEach>
                </thead> 
            </table>
        </div>
    </c:if>
</body>
</html>
