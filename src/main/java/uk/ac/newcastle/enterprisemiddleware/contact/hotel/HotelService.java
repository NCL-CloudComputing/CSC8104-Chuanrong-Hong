package uk.ac.newcastle.enterprisemiddleware.contact.hotel;

import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Hotel;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class HotelService {
    @Inject
    HotelRepository hotelRepository;

    void createHotel(Hotel hotel){ hotelRepository.create(hotel); }


    List<Hotel> getAllHotels(){ return hotelRepository.getHotel();}

    Boolean checkHotel(long id){ return hotelRepository.checkHotel(id);}
    Boolean delHotel(long id){ return hotelRepository.delHotel(id);}
}
