package facades;

import DTO.KayakDTO;
import entities.BookingDate;
import entities.Image;
import entities.Kayak;
import errorhandling.NotFoundException;
import java.util.ArrayList;
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
    
    public BookingDate getBookingDateById(long id) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            if (id == 0) {
                throw new NotFoundException("BookingDate not found on the id: " + id);
                
            }
            return em.find(BookingDate.class, id);
            
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
     public Image getImageById(long id) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            if (id == 0) {
                throw new NotFoundException("Image not found on the id: " + id);
                
            }
            return em.find(Image.class, id);
            
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
     
    public KayakDTO addKayak(Kayak kayak) throws NotFoundException{
        EntityManager em = emf.createEntityManager();
        
        Image image;
        BookingDate date;
        
        List<Image> images = new ArrayList<>();
        List<BookingDate> dates = new ArrayList<>();
        
        try{
            em.getTransaction().begin();
            em.persist(kayak);
            
            for(Image i : kayak.getImage()){ //  
                image = getImageById(i.getId());  
                if(image == null){ // hvis director ikke findes så skal han ligges i databasen, 
                    em.persist(i);
                }else{ 
                    images.add(image); // hvis han er i databasen så skal han tilknyttes en movie
                }
            }
            
            for(BookingDate d : kayak.getBookingdate()){
                date = getBookingDateById(d.getId());
                if(date == null){
                    em.persist(d);     
                }else{
                    dates.add(date);
                }
            }
            

            
          kayak.setBookingdate(dates);
          kayak.setImage(images);
            
            em.getTransaction().commit();
            return new KayakDTO(kayak);
        }finally{
            em.close();
        }
      
     }
   
    }
    
    


