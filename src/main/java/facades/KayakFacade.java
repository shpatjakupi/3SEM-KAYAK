package facades;

import DTO.BookingDateDTO;
import DTO.ImageDTO;
import DTO.KayakDTO;
import entities.BookingDate;
import entities.Image;
import entities.Kayak;
import errorhandling.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class KayakFacade {

    private static KayakFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private KayakFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static KayakFacade getKayakFacade(EntityManagerFactory _emf) {
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

    public static void main(String[] args) {
        KayakFacade f = KayakFacade.getKayakFacade(EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE));
        Kayak k = new Kayak("blabla", "bro", "van damm", 1998, "grå", 1);
        k.addImages(new Image("blablabla.dk"));
        System.out.println(k);
        try {
            KayakDTO kdto = f.addKayak(k);
            System.out.println(kdto);
        } catch (NotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public KayakDTO addKayak(Kayak kayak) throws NotFoundException {
        EntityManager em = emf.createEntityManager();

        Image image;
        BookingDate date;

        List<Image> images = new ArrayList<>();
        List<BookingDate> dates = new ArrayList<>();

        try {
            em.getTransaction().begin();
            em.persist(kayak);

//            for (Image i : kayak.getImage()) { //  
//                image = getImageById(i.getId());
//                if (image == null) { // hvis images ikke findes så skal images ligges i databasen, 
//                    em.persist(i);
//                } else {
//                    images.add(image); // hvis images er i databasen så skal images tilknyttes en movie
//                }
//            }
//
//            for (BookingDate d : kayak.getBookingdate()) {
//                date = getBookingDateById(d.getId());
//                if (date == null) {
//                    em.persist(d);
//                } else {
//                    dates.add(date);
//                }
//            }
//
//            kayak.setBookingdate(dates);
//            kayak.setImage(images);

            em.getTransaction().commit();
            return new KayakDTO(kayak);
        } finally {
            em.close();
        }

    }

    public ImageDTO addImage(Image i) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(i);
            em.getTransaction().commit();
            return new ImageDTO(i);
        } finally {
            em.close();
        }
    }

    public List<KayakDTO> getAllKayaks() throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<KayakDTO> tq = em.createNamedQuery("Kayak.getAll", KayakDTO.class);
            List<KayakDTO> k = tq.getResultList();
            if (k.isEmpty()) {
                throw new NotFoundException("No kayaks found in the database");
            }
            return k;
        } finally {
            em.close();
        }
    }

    public List<BookingDateDTO> getAllBookings() throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BookingDateDTO> tq = em.createNamedQuery("BookingDate.getAll", BookingDateDTO.class);
            List<BookingDateDTO> dates = tq.getResultList();
            if (dates.isEmpty()) {
                throw new NotFoundException("No movies has been added to the database yet.");
            }
            return dates;
        } finally {
            em.close();
        }
    }

    public List<KayakDTO> getKayakBySpace(int personsAllowed) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<KayakDTO> tq = em.createNamedQuery("Kayak.getByPersonAllowed", KayakDTO.class).setParameter("personsAllowed", personsAllowed);
            List<KayakDTO> kayaks = tq.getResultList();
            if (kayaks.isEmpty()) {
                throw new NotFoundException("No kayaks with room for: " + personsAllowed + " exists in the database.");
            }
            return kayaks;
        } finally {
            em.close();
        }
    }

    public KayakDTO deleteKayak(Long id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            Kayak kayak = em.find(Kayak.class, id);
            if (kayak == null) {
                throw new NotFoundException("a kayak with the id: " + id + " doesnt exist.");
            }
            em.getTransaction().begin();
            em.remove(kayak);
            em.getTransaction().commit();
            return new KayakDTO(kayak);
        } finally {
            em.close();
        }
    }

}
