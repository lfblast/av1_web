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
        <c:choose>
            <c:when test = "${pedidos.size() > 0}">
                <div>
                    <table border="1">
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
                                <th>###</th>
                            </tr>
                            <c:forEach items="${pedidos}" var="pedido">
                                <tr>
                                    <td>${pedido.id}</td>
                                    <td>${pedido.endereco.rua}</td>
                                    <c:choose>
                                        <c:when test="${pedido.entrega}">
                                            <td>SIM</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>NÃO</td>
                                        </c:otherwise>
                                    </c:choose>                                                        
                                    <td>${pedido.taxaEntrega}</td>
                                    <td>${pedido.troco}</td>
                                    <td>${pedido.valor}</td>
                                    <td>${pedido.data} - ${pedido.hora}</td>
                                    <td>${pedido.status}</td>
                                    <td><a href="detalhes-pedido?pedido=${pedido.id}">Ver Detalhes</a></td>
                                </tr>
                            </c:forEach>
                        </thead> 
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    Não há pedidos cadastrados!
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
