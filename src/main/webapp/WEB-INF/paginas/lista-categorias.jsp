<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Av1_web</title>
    </head>

    <body>
        <h1>Lista de Categorias:</h1>
        <div>
            <a href="cadastro-categoria" title="Incluir Categoria">Incluir Categoria</a> /
            <a href="index.html" title="Voltar para o Menu">Voltar</a>
        </div>
        <c:choose>
            <c:when test = "${categorias.size() > 0}">
                <div>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nome</th>
                                <th>###</th>
                            </tr>
                            <c:forEach items="${categorias}" var="categoria">
                                <tr>
                                    <td>${categoria.id}</td>
                                    <td>${categoria.nome}</td>                                    
                                    <td><a href="detalhes-categoria?categoria=${categoria.id}">Ver Detalhes</a></td>
                                </tr>
                            </c:forEach>
                        </thead> 
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    Não há categorias cadastradas!
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>