<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Av1_web</title>
    </head>
    <body>
        <h1>Detalhes do Cliente:</h1>        
        <div>
            <a href="lista-clientes">Voltar</a>
        </div>
        <div>
            <form action="alterar-cliente-persiste" method="post">
                <table>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" name="nome" size="60" maxlength="40" value="${cliente.nome}"/></td>
                    </tr>
                    <tr>
                        <td>Telefone:</td>
                        <td><input type="text" name="telefone" size="20" maxlength="9" value="${cliente.telefone}"/></td>
                    </tr>
                    <tr>
                        <td>Endereços:</td>
                        <td>
                            <table border="1">
                                <c:choose>
                                    <c:when test = "${enderecos.size() > 0}">
                                        <c:forEach items="${enderecos}" var="endereco">
                                            <tr>
                                                <td>${endereco.rua} - ${endereco.numero} - ${endereco.referencia}</td>
                                                <td><a href="remover-endereco-persiste?endereco=${endereco.id}">Remover</a></td>
                                            <tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <div>
                                            <tr><td>Não há Endereços cadastrados para este Cliente!</td></tr>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </table>
                        </td>
                    </tr>
                </table>                
                <input type="hidden" name="id" value="${cliente.id}"/>
                <input type="submit" value="Alterar">
                <a href="remover-cliente-persiste?cliente=${cliente.id}">Remover</a>
            </form>
        </div>
    </body>
</html>