/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author shpattt
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "BookingDate.getAll", query = "SELECT new DTO.BookingDateDTO(d) FROM BookingDate d"),
    @NamedQuery(name = "BookingDate.deleteAll", query = "DELETE FROM BookingDate d"),
})
public class BookingDate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookingDate;
    
    @ManyToOne
    private Kayak kayak;
    
    @ManyToOne
    private User user;

    public BookingDate() {};

    public BookingDate(String bookingDate, User user) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.user = user;
    }
    
    public Kayak getKayak() {
        return kayak;
    }

    public void setKayak(Kayak kayak) {
        this.kayak = kayak;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookingDate)) {
            return false;
        }
        BookingDate other = (BookingDate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BookingDate{" + "id=" + id + ", bookingDate=" + bookingDate + '}';
    }

   
}
