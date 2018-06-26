package br.edu.infnet.av1_web.view.form;

import br.edu.infnet.av1_web.exception.ServiceException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;


public class ProdutoFormValidacao {
    
    public static void valida(HttpServletRequest request) throws ServiceException {        
        
        DecimalFormat DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getInstance(new Locale("pt", "BR"));
    }
}