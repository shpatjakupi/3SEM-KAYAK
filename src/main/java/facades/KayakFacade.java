package facades;

import entities.Kayak;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class KayakFacade {

    private static KayakFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private KayakFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static KayakFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new KayakFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    private static Kayak getKayak(String name){
        EntityManager em = emf.createEntityManager();
        try{
            if (name.isEmpty() || name == null) return null;
            Kayak kayak = em.createNamedQuery("Kayak.getByName", Kayak.class).setParameter("name", name).getSingleResult();
            return kayak;
        }catch(Exception e){
            return null;
        }finally{
            em.close();
        }
    }

}
