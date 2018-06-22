<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Av1_web</title>
    </head>
    <body>
        <h1>Detalhes do Pedido:</h1>        
        <div>
            <a href="lista-pedidos">Voltar</a>
        </div>
        <div>
            <form action="alterar-pedido-persiste" method="post">
                <table>
                    <tr>
                        <td>Cliente:</td>
                        <td>${pedido.cliente.nome} - tel: ${pedido.cliente.telefone}</td>
                    </tr>
                    <tr>
                        <td>Endereços:</td>
                        <td>
                            <select id="endereco" name="endereco">
                                <option	value="-1">- Endereços -</option>
                                <c:forEach items="${pedido.cliente.enderecos}" var="end">
                                    <option value="${end.id}"
                                    <c:if test="${end.id == pedido.endereco.id}">
                                        selected="true"
                                    </c:if>
                                    >
                                        ${end.rua} - ${end.numero} - ${end.referencia}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Desconto:</td>
                        <td><input type="text" name="desconto" size="8" maxlength="8" value="${pedido.desconto}"/></td>
                    </tr>                    
                    <tr>
                        <td>Troco:</td>
                        <td><input type="text" name="troco" size="8" maxlength="8" value="${pedido.troco}"/></td>
                    </tr>
                    <tr>
                        <td>Entrega:</td>                        
                        <td>
                            <input type="checkbox" name="entrega"
                            <c:if test="${pedido.entrega}">
                                checked="true"
                            </c:if>
                            />
                        </td>
                    </tr>
                    <tr>
                        <td>Taxa de Entrega:</td>
                        <td><input type="text" name="taxaEntrega" size="8" maxlength="8" value="${pedido.taxaEntrega}"/></td>
                    </tr>
                    <tr>
                        <td>Produtos:</td>
                        <td>
                            <table border="1">
                                <c:forEach items="${produtos}" var="produto">                                    
                                    <tr>
                                        <td>
                                            <input type="checkbox" name="produtos" value="${produto.id}"
                                            <c:forEach items="${pedido.produtos}" var="produtoPedido">
                                                <c:if test="${produto.id == produtoPedido.produto.id}">
                                                    checked="true"
                                                </c:if>
                                            </c:forEach>
                                            />${produto.codigo} - ${produto.nome} - ${produto.preco}
                                        </td>
                                        <td>
                                            Quant: <input type="text" name="quantidade${produto.id}" size="2" maxlength="2"
                                            <c:forEach items="${pedido.produtos}" var="produtoPedido">
                                                <c:if test="${produto.id == produtoPedido.produto.id}">
                                                    value="${produtoPedido.quantidade}"
                                                </c:if>
                                            </c:forEach>
                                            />
                                        </td>
                                        <td>
                                            Obs: <input type="text" name="obs${produto.id}" size="60" maxlength="80"
                                            <c:forEach items="${pedido.produtos}" var="produtoPedido">
                                                <c:if test="${produto.id == produtoPedido.produto.id}">
                                                    value="${produtoPedido.obs}"
                                                </c:if>
                                            </c:forEach>
                                            />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>Valor:</td>
                        <td>${pedido.valor}</td>
                    </tr>
                    <tr>
                        <td>Status:</td>
                        <td>
                            <select id="status" name="status">
                                <option	value="1" <c:if test="${pedido.status.getStatus() == 1}">selected="true"</c:if> >AGUARDANDO_CONFIRM_PAGAMENTO</option>
                                <option	value="2" <c:if test="${pedido.status.getStatus() == 2}">selected="true"</c:if> >PAGAMENTO_CONFIRMADO</option>
                                <option	value="3" <c:if test="${pedido.status.getStatus() == 3}">selected="true"</c:if> >ENTREGUE_TRANSPORTADORA</option>
                                <option	value="4" <c:if test="${pedido.status.getStatus() == 4}">selected="true"</c:if> >EM_TRANSITO</option>
                                <option	value="5" <c:if test="${pedido.status.getStatus() == 5}">selected="true"</c:if> >ENTREGUE</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name="cliente" value="${pedido.cliente.id}"/>
                <input type="hidden" name="id" value="${pedido.id}"/>
                <input type="submit" value="Alterar">
                <a href="remover-pedido-persiste?pedido=${pedido.id}">Remover</a>
            </form>
        </div>
    </body>
</html>