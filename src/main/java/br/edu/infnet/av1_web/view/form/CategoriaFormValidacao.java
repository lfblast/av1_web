package br.edu.infnet.av1_web.view.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import javax.servlet.http.HttpServletRequest;


public class CategoriaFormValidacao {
    
    public static void valida(HttpServletRequest request) throws ServiceException {
        
        if(request.getParameter("nome").length() < 1) {
            throw new ServiceException("Epecifique um nome para a categoria!");
        }
    }
}
