package facades;

import DTO.ImageDTO;
import DTO.KayakDTO;
import entities.BookingDate;
import entities.Image;
import entities.Kayak;
import entities.User;
import errorhandling.NotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

/**
 *
 * @author shpattt
 */
public class KayakFacadeTest {

    private static EntityManagerFactory emf;
    private static KayakFacade facade;
    
    private static Kayak kayak;
    private static Kayak kayak1;
    
    private static Image image;
    private static Image image1;
    
    private static BookingDate date;
    private static BookingDate date1;
    
    private static User user;
    private static User user1;
    
    public KayakFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
        facade = KayakFacade.getKayakFacade(emf);
    }

    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        kayak = new Kayak("Old Viking", "XVP-01", "Getting Viking Vibes", 1998 , "Grey", 3);
        //kayak1 = new Kayak("New Viking", "XVP-99", "Maradonas favorite", 2012 , "white", 4);
        
        user = new User("john", "hala");
        user1 = new User("damm", "dro");

        
        date = new BookingDate("2019-12-10", user);
        date1 = new BookingDate("2010-01-01", user1);
        
        
        image = new Image("https://shop13286.hstatic.dk/upload_dir/shop/Winner-boerne-kajak-15g.w610.h610.fill.jpg");
        image1 = new Image("https://shop13286.hstatic.dk/upload_dir/shop/Winner-boerne-kajak-15g.w610.h610.fill.jpg");
        
                
        
        kayak.addBookingDate(date);
        kayak.addBookingDate(date1);
        kayak.addImages(image);
        kayak.addImages(image1);
        
     
        try {
            em.getTransaction().begin();;
            em.persist(user);
            em.persist(user1);
            em.persist(date);
            em.persist(date1);
            em.persist(image);
            em.persist(image1);
            em.persist(kayak);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void addKayak() throws NotFoundException {
        Image _image = new Image("noflexzone");
        
        BookingDate _date = new BookingDate("2010-00-00", user);
        
        Kayak _kayak = new Kayak("Banditten", "XVZ-39S", "hygge", 1990, "blå", 4);
        _kayak.addBookingDate(_date);
        _kayak.addImages(_image);

        KayakDTO expected = new KayakDTO(_kayak);
        KayakDTO actual = facade.addKayak(_kayak);

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void addBookingDate() {
        Image _image = new Image("yes.dk");
        ImageDTO expected = new ImageDTO(_image);
        ImageDTO actual = facade.addImage(_image);

        assertEquals(expected.getUrl(), actual.getUrl());

    }
   
   @Test
    public void deleteKayaks() throws NotFoundException {
        int expected = facade.getAllKayaks().size() - 1;
        KayakDTO deletedKayaks = facade.deleteKayak(1L);
        int actual = facade.getAllKayaks().size();
        
        assertEquals(expected, actual);
        Assertions.assertNotNull(deletedKayaks);
    }
}
