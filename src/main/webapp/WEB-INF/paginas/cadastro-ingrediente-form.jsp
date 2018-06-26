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
            <form action="cadastro-ingrediente-persiste" method="post">
                <table>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" name="nome" size="60" maxlength="80"/></td>
                    </tr>
                </table>                                
                <input type="submit" value="Enviar">                
            </form>
        </div>
    </body>
</html>