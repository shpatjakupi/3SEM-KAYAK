/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTO.BookingDateDTO;
import DTO.ImageDTO;
import DTO.KayakDTO;
import entities.Image;
import entities.Kayak;
import java.util.List;

/**
 *
 * @author shpattt
 */
public interface IKayakFacade {
    public KayakDTO addKayak(Kayak kayak);
    public ImageDTO addImage(Image i);
    public List<BookingDateDTO> getAllBookings();
    public List<KayakDTO> getAllKayaks();
    public List<KayakDTO> getKayakBySpace(int personsAllowed);
    public KayakDTO deleteKayak(Long id);
}
