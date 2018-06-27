<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Av1_web</title>
    </head>
    <body>
        <h1>Detalhes do Ingrediente:</h1>        
        <div>
            <a href="lista-ingredientes">Voltar</a>
        </div>
        <div>
            <form action="alterar-ingrediente-persiste" method="post">
                <table>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" name="nome" size="60" maxlength="80" value="${ingrediente.nome}"/></td>
                    </tr>
                </table>                
                <input type="hidden" name="id" value="${ingrediente.id}"/>
                <input type="submit" value="Alterar">
                <a href="remover-ingrediente-persiste?ingrediente=${ingrediente.id}">Remover</a>
            </form>
        </div>
    </body>
</html>