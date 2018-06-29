<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Av1_web</title>
    </head>
    <body>
        <h1>Detalhes do Produto:</h1>
        <div>
            <a href="lista-produtos">Voltar</a>
        </div>
        <div>
            <form action="alterar-produto-persiste" method="post">
                <table>
                    <tr>
                        <td>Código:</td>
                        <td><input type="text" name="codigo" size="8" maxlength="8" value="${produto.codigo}"/></td>
                    </tr>                    
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" name="nome" size="20" maxlength="40" value="${produto.nome}"/></td>
                    </tr>
                    <tr>
                        <td>Categoria:</td>
                        <td>
                            <select id="categoria" name="categoria">
                                <option	value="-1">- Categorias -</option>
                                <c:forEach items="${categorias}" var="cat">
                                    <option value="${cat.id}"
                                        <c:if test="${cat.id == produto.categoria.id}">
                                            selected="true"
                                        </c:if>
                                    >${cat.nome}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Ingredientes:</td>
                        <c:choose>
                            <c:when test="${ingredientes.size() > 0}">
                                <td>
                                    <table border="1">
                                        <c:forEach items="${ingredientes}" var="ingrediente">
                                            <tr>
                                                <td>
                                                    <input type="checkbox" name="ingredientes" value="${ingrediente.id}"
                                                        <c:forEach items="${produto.ingredientes}" var="produtoIngrediante">
                                                            <c:if test="${ingrediente.id == produtoIngrediante.id}">
                                                                checked="true"
                                                            </c:if>
                                                        </c:forEach>      
                                                     />${ingrediente.nome}
                                                </td>
                                            </tr>                                
                                        </c:forEach>
                                    </table>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>Não há ingredientes cadastrados!</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <tr>
                        <td>Preço:</td>
                        <td><input type="text" name="preco" size="8" maxlength="8" value="${produto.preco}"/></td>
                    </tr>
                </table>
                    <input type="hidden" name="id" value="${produto.id}"/>
                <input type="submit" value="Enviar">
                <a href="remover-produto-persiste?produto=${produto.id}">Remover</a>
            </form>
        </div>
    </body>
</html>