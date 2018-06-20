package br.edu.infnet.av1_web.view.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;


public class PedidoFormValidacao {
    
    public static void valida(HttpServletRequest request) throws ServiceException {        
        
        DecimalFormat DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getInstance(new Locale("pt", "BR"));
        
        //Valida endereço
        if(request.getParameter("endereco").equals("-1")) {
            throw new ServiceException("Ecolha um Endereço!");
        }        
        
        //Valida produtos e suas quantidades.
        
        if((request.getParameterValues("produtos") == null) || (request.getParameterValues("produtos").length == 0)) {
            throw new ServiceException("Ecolha ao menos um Produto!");
        }
        else {
            for(String prod : request.getParameterValues("produtos")) {
                if(request.getParameter("quantidade"+prod).length() > 0) {
                    try {
                        Integer quantidade = new Integer(request.getParameter("quantidade"+prod));
                        if(quantidade < 1) {
                            throw new ServiceException("Especifique uma quantidade válida para o Produto!");
                        }
                    }
                    catch(NumberFormatException e) {
                        Logger.getLogger(PedidoForm.class.getName()).log(Level.WARNING, null, e);
                        throw new ServiceException("Especifique uma quantidade válida para o Produto!");
                    }
                }
                else {
                    throw new ServiceException("Especifique uma quantidade para o Produto!");
                }
            }
        }
        
        //Valida desconto, troco e taxa de entrega
        if(request.getParameter("desconto").length() > 0) {
            try {
                DECIMAL_FORMAT.parse(request.getParameter("desconto"));
            } 
            catch (ParseException e) {
                Logger.getLogger(PedidoForm.class.getName()).log(Level.WARNING, null, e);
                throw new ServiceException("Especifique um desconto válido!");
            }
        }
        
        if(request.getParameter("troco").length() > 0) {
            try {
                DECIMAL_FORMAT.parse(request.getParameter("troco"));
            } 
            catch (ParseException e) {
                Logger.getLogger(PedidoForm.class.getName()).log(Level.WARNING, null, e);
                throw new ServiceException("Especifique um troco válido!");
            }
        }
        
        if(request.getParameter("taxaEntrega").length() > 0) {
            try {
                DECIMAL_FORMAT.parse(request.getParameter("taxaEntrega"));
            } 
            catch (ParseException e) {
                Logger.getLogger(PedidoForm.class.getName()).log(Level.WARNING, null, e);
                throw new ServiceException("Especifique uma taxa de entrega válida!");
            }
        }
    }
}