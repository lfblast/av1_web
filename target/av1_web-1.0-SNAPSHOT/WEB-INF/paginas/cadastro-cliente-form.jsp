<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Av1_web</title>
    </head>
    <body>
        <h1>Cadastro de Cliente:</h1>
        <div>
            <a href="lista-clientes">Voltar</a>
        </div>
        <div>
            <form action="cadastro-cliente-persiste" method="post">
                <table>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" name="nome" size="20" maxlength="40"/></td>
                    </tr>
                    <tr>
                        <td>Telefone:</td>
                        <td><input type="text" name="telefone" size="20" maxlength="9"/></td>
                    </tr>
                </table>
                <input type="submit" value="Enviar">
            </form>
        </div>
    </body>
</html>