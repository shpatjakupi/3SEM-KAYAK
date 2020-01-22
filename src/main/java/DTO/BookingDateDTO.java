/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.BookingDate;
import entities.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shpattt
 */
public class BookingDateDTO {
    private Long id;
    private String bookingDate;
    
    public BookingDateDTO(BookingDate date) {
        this.id = date.getId();
        this.bookingDate = date.getBookingDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "BookingDateDTO{" + "id=" + id + ", bookingDate=" + bookingDate + '}';
    }
    
    
    
}
