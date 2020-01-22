//package facades;
//
//import DTO.BookingDateDTO;
//import DTO.ImageDTO;
//import DTO.KayakDTO;
//import entities.BookingDate;
//import static entities.BookingDate_.bookingDate;
//import entities.Image;
//import utils.EMF_Creator;
//import entities.Kayak;
//import entities.User;
//import errorhandling.NotFoundException;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import utils.Settings;
//import utils.EMF_Creator.DbSelector;
//import utils.EMF_Creator.Strategy;
//
//
//public class KayakFacadeTest {
//
//    private static EntityManagerFactory emf;
//    private static KayakFacade facade;
//    
//    private static Kayak kayak;
//    private static Kayak kayak1;
//    
//    private static Image image;
//    private static Image image1;
//    
//    private static BookingDate date;
//    private static BookingDate date1;
//    
//    private static User user;
//    private static User user1;
//    
//    public KayakFacadeTest() {
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//        emf = EMF_Creator.createEntityManagerFactory(
//                "pu",
//                "jdbc:mysql://localhost:3307/kayak_test",
//                "sj1234",
//                "sj1234",
//                EMF_Creator.Strategy.CREATE);
//        facade = KayakFacade.getKayakFacade(emf);
//    }
//
//    
//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        kayak = new Kayak("Old Viking", "XVP-01", "Getting Viking Vibes", 1998 , "Grey", 3);
//        //kayak1 = new Kayak("New Viking", "XVP-99", "Maradonas favorite", 2012 , "white", 4);
//        
//        user = new User("user", "user");
//        user1 = new User("bro", "bro");
//
//        
//        date = new BookingDate("2019-12-10", kayak, user);
//        date1 = new BookingDate("2010-01-01", kayak, user1);
//        
//        image = new Image("data:image/jpeg;base64,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", kayak);
//        image1 = new Image("data:image/jpeg;base64,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", kayak1);
//                
//        
//        kayak.addBookingDate(date);
//        kayak.addBookingDate(date1);
//        kayak.addImages(image);
//        kayak.addImages(image1);
//        
//     
//        try {
//            em.getTransaction().begin();;
//            em.persist(user);
//            em.persist(user1);
//            em.persist(date);
//            em.persist(date1);
//            em.persist(image);
//            em.persist(image1);
//            em.persist(kayak);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    @Test
//    public void addKayak() throws NotFoundException {
//        Image _image = new Image("noflexzone", kayak);
//        
//        BookingDate _date = new BookingDate("2010-00-00", kayak, user);
//        
//        Kayak _kayak = new Kayak("Banditten", "XVZ-39S", "hygge", 1990, "blå", 4);
//        _kayak.addBookingDate(_date);
//        _kayak.addImages(_image);
//
//        KayakDTO expected = new KayakDTO(_kayak);
//        KayakDTO actual = facade.addKayak(_kayak);
//
//        assertEquals(expected.getName(), actual.getName());
//    }
//
//    @Test
//    public void addBookingDate() {
//        Image _image = new Image("yes.dk", kayak);
//        ImageDTO expected = new ImageDTO(_image);
//        ImageDTO actual = facade.addImage(_image);
//
//        assertEquals(expected.getUrl(), actual.getUrl());
//
//    }
//   
//   @Test
//    public void deleteKayaks() throws NotFoundException {
//        int expected = facade.getAllKayaks().size() - 1;
//        KayakDTO deletedKayaks = facade.deleteKayak(1L);
//        int actual = facade.getAllKayaks().size();
//        
//        assertEquals(expected, actual);
//        Assertions.assertNotNull(deletedKayaks);
//    }
//}
