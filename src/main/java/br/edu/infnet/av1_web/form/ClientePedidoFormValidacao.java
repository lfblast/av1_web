package br.edu.infnet.av1_web.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import javax.servlet.http.HttpServletRequest;


public class ClientePedidoFormValidacao {
    
    public static void valida(HttpServletRequest request) throws ServiceException {        
        
        if(request.getParameter("clienteId").equals("-1")) {
            throw new ServiceException("Ecolha um Cliente!");
        }
    }
}