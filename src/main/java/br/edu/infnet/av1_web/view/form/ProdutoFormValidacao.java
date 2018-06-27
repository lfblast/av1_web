package br.edu.infnet.av1_web.view.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;


public class ProdutoFormValidacao {
    
    public static void valida(HttpServletRequest request) throws ServiceException {        
        
        DecimalFormat DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getInstance(new Locale("pt", "BR"));
        
        //Valida Categoria
        if(request.getParameter("codigo").length() < 1) {
            throw new ServiceException("Epecifique um código para o produto!");
        }
        else if(request.getParameter("nome").length() < 1) {
            throw new ServiceException("Epecifique um nome para o produto!");
        }
        else if(request.getParameter("categoria").equals("-1")) {
            throw new ServiceException("Ecolha uma Categoria!");
        }
        
        if((request.getParameterValues("ingredientes") == null) || (request.getParameterValues("ingredientes").length == 0)) {
            throw new ServiceException("Ecolha ao menos um Ingrediente!");
        }
        
         if(request.getParameter("preco").length() > 0) {
            try {
                DECIMAL_FORMAT.parse(request.getParameter("preco"));
            } 
            catch (ParseException e) {
                Logger.getLogger(PedidoForm.class.getName()).log(Level.WARNING, null, e);
                throw new ServiceException("Especifique um preço válido!");
            }
        }
        else {
             throw new ServiceException("Especifique um preço para o produto!");
        }
    }
}