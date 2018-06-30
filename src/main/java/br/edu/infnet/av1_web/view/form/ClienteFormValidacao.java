package br.edu.infnet.av1_web.view.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;


public class ClienteFormValidacao {
    
    public static void valida(HttpServletRequest request) throws ServiceException {        
        
        if(request.getParameter("nome").length() < 1) {
            throw new ServiceException("Epecifique um nome para o cliente!");
        }
        else if(request.getParameter("telefone").length() < 1) {
            throw new ServiceException("Epecifique um telefone para o cliente!");
        }
        else if(request.getParameter("telefone").length() > 0) {
            try {
                new Integer(request.getParameter("telefone"));
            }
            catch(NumberFormatException e) {
                Logger.getLogger(PedidoForm.class.getName()).log(Level.WARNING, null, e);
                throw new ServiceException("Especifique um telefone válido!");
            }
        }
    }
}