package br.edu.infnet.av1_web.util;

import javax.persistence.EntityManager;

public class CriaTabelas {

    public static void main(String[] args) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(em != null) {
                em.close();
                JpaUtil.closeEntityManagerFactory();
            }
        }
    }
}