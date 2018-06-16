<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Av1_web</title>
    </head>
    <body>
        <h1>Cadastro de Pedido:</h1>        
        <div>
            <a href="cadastro-pedido-cliente">Voltar</a>
        </div>                

        <div>
            <form action="cadastro-pedido-persiste" method="post">
                <table class="table_detais">
                    <tr>
                        <td>Cliente:</td>
                        <td>${cliente.nome} - tel: ${cliente.telefone}</td>
                    </tr>
                    <tr>
                        <td>Endereços:</td>
                        <td>
                            <select id="endereco" name="endereco">
                                <option	value="-1">- Endereços -</option>
                                <c:forEach items="${enderecos}" var="end">
                                    <option value="${end.id}">${end.rua} - ${end.numero} - ${end.referencia}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Desconto:</td>
                        <td><input type="text" name="desconto" size="8" maxlength="8"/></td>
                    </tr>                    
                    <tr>
                        <td>Troco:</td>
                        <td><input type="text" name="troco" size="8" maxlength="8"/></td>
                    </tr>
                    <tr>
                        <td>Entrega:</td>
                        <td><input type="checkbox" name="entrega"></td>
                    </tr>
                    <tr>
                        <td>Taxa de Entrega:</td>
                        <td><input type="text" name="taxaEntrega" size="8" maxlength="8"/></td>
                    </tr>
                    <tr>
                        <td>Produtos:</td>
                        <td>
                            <c:forEach items="${produtos}" var="produto">
                                <input type="checkbox" name="produtos" value="${produto.id}"/>${produto.codigo} - ${produto.nome} - ${produto.preco} <br/>
                            </c:forEach>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name="cliente" value="${cliente.id}"/>
                <input type="submit" value="Enviar">
            </form>
        </div>
    </body>
</html>