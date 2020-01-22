package facades;

import DTO.KayakDTO;
import entities.BookingDate;
import entities.Image;
import utils.EMF_Creator;
import entities.Kayak;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;


public class KayakFacadeTest {

    private static EntityManagerFactory emf;
    private static KayakFacade facade;
    
    private static Kayak kayak;
    private static Kayak kayak1;
    
    private static Image image;
    private static Image image1;
    
    private static BookingDate date;
    private static BookingDate date2;
    
    private static User user;
    private static User user2;
    
    public KayakFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/kayak_test",
                "sj1234",
                "sj1234",
                EMF_Creator.Strategy.CREATE);
        facade = KayakFacade.getFacadeExample(emf);
    }

    
    @BeforeAll
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        user = new User("user", "user");
        date = new BookingDate("2019-12-10", kayak, user);
        image = new Image("", kayak);
        
                
        kayak = new Kayak("Old Viking", "XVP-99", "Getting Viking Vibes", 1998 , "Grey", 3);
        
        kayak.addBookingDate(date);
        kayak.addImages(image);
        
        try {
            em.getTransaction().begin();;
            em.persist(user);
            em.persist(date);
            em.persist(image);
            em.persist(kayak);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

   
}
