<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Av1_web</title>
    </head>
    <body>
        <h1>Cadastro de Endereco:</h1>
        <div>
            <a href="lista-enderecos">Voltar</a>
        </div>
        <div>
            <form action="cadastro-endereco-persiste" method="post">
                <table>
                    <tr>
                        <td>Rua:</td>
                        <td><input type="text" name="rua" size="20" maxlength="100"/></td>
                    </tr>
                    <tr>
                        <td>Número:</td>
                        <td><input type="text" name="numero" size="20" maxlength="5"/></td>
                    </tr>
                    <tr>
                        <td>Referência:</td>
                        <td><input type="text" name="referencia" size="20" maxlength="100"/></td>
                    </tr>
                </table>
                <input type="hidden" name="cliente" value="${cliente}"/>
                <input type="submit" value="Enviar">
            </form>
        </div>
    </body>
</html>