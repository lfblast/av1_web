package br.edu.infnet.av1_web.run;

import br.edu.infnet.av1_web.util.JpaUtil;


public class CriarBancoLimpo {

    public static void main(String[] args) {
        JpaUtil.getEntityManager();
    }
}