<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Av1_web</title>
    </head>

    <body>
        <h1>Lista de Produtos:</h1>
        <div>
            <a href="cadastro-produto-form" title="Incluir Produto">Incluir Produto</a> /
            <a href="index.html" title="Voltar para o Menu">Voltar</a>
        </div>
        <c:choose>
            <c:when test = "${produtos.size() > 0}">
                <div>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Código</th>
                                <th>Nome</th>
                                <th>Categoria</th>
                                <th>Preço</th>                                
                                <th>###</th>
                            </tr>
                            <c:forEach items="${produtos}" var="produto">
                                <tr>
                                    <td>${produto.id}</td>
                                    <td>${produto.codigo}</td>
                                    <td>${produto.nome}</td>
                                    <td>${produto.categoria.nome}</td>
                                    <td>${produto.preco}</td>
                                    <td><a href="detalhes-produto?produto=${produto.id}">Ver Detalhes</a></td>
                                </tr>
                            </c:forEach>
                        </thead> 
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    Não há produtos cadastrados!
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
