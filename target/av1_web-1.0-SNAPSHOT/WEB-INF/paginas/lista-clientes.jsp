<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Av1_web</title>
    </head>

    <body>
        <h1>Lista de Clientes:</h1>
        <div>
            <a href="cadastro-cliente-form" title="Incluir Cliente">Incluir Cliente</a> /
            <a href="index.html" title="Voltar para o Menu">Voltar</a>
        </div>
        <c:choose>
            <c:when test = "${clientes.size() > 0}">
                <div>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nome</th>
                                <th>Telefone</th>
                                <th>###</th>
                                <th>###</th>
                            </tr>
                            <c:forEach items="${clientes}" var="cliente">
                                <tr>
                                    <td>${cliente.id}</td>
                                    <td>${cliente.nome}</td>
                                    <td>${cliente.telefone}</td>                                    
                                    <td><a href="detalhes-cliente?cliente=${cliente.id}">Ver Detalhes</a></td>
                                    <td><a href="cadastro-endereco-form?cliente=${cliente.id}">Incluir Endereço</a></td>
                                </tr>
                            </c:forEach>
                        </thead> 
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    Não há clientes cadastrados!
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
