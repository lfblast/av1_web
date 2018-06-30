package br.edu.infnet.av1_web.view.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;


public class EnderecoFormValidacao {
    
    public static void valida(HttpServletRequest request) throws ServiceException {        
        
        if(request.getParameter("rua").length() < 1) {
            throw new ServiceException("Epecifique uma rua para o endereço!");
        }
        else if(request.getParameter("numero").length() < 1) {
            throw new ServiceException("Epecifique um número para o endereço!");
        }
        else {
            try {
                new Integer(request.getParameter("numero"));
            }
            catch(NumberFormatException e) {
                Logger.getLogger(PedidoForm.class.getName()).log(Level.WARNING, null, e);
                throw new ServiceException("Especifique um número válido!");
            }
        }
    }
}