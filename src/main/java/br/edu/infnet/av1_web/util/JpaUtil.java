package br.edu.infnet.av1_web.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("av1PU");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
    
    public static void closeEntityManagerFactory() {
        factory.close();
    }
}
