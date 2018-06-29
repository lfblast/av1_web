<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Av1_web</title>
    </head>

    <body>
        <h1>Lista de Ingredientes</h1>
        <div>
            <a href="cadastro-ingrediente-form" title="Incluir Ingrediente">Incluir Ingrediente</a> /
            <a href="index.html" title="Voltar para o Menu">Voltar</a>
        </div>
        <c:choose>
            <c:when test = "${ingredientes.size() > 0}">
                <div>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nome</th>
                                <th>###</th>
                            </tr>
                            <c:forEach items="${ingredientes}" var="ingrediente">
                                <tr>
                                    <td>${ingrediente.id}</td>
                                    <td>${ingrediente.nome}</td>                                    
                                    <td><a href="detalhes-ingrediente?ingrediente=${ingrediente.id}">Ver Detalhes</a></td>
                                </tr>
                            </c:forEach>
                        </thead> 
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    Não há Ingredientes cadastrados!
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>