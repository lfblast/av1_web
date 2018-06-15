package br.edu.infnet.av1_web.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private static EntityManagerFactory factory;

    public static EntityManager getEntityManager() {
        if(factory == null) {
            factory = Persistence.createEntityManagerFactory("av1PU");
        }
        return factory.createEntityManager();
    }
    
    public static void closeEntityManagerFactory() {
        factory.close();
    }
}
