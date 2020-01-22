/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.BookingDate;
import entities.Role;
import entities.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shpattt
 */
public class UserDTO {
    private String userName;
    private List<Role> roleList;
    private List<BookingDate> bookingdates;

    public UserDTO(User u) {
        this.userName = userName;
        this.bookingdates = new ArrayList<>();
        this.roleList = new ArrayList<>();
    }
    
}
