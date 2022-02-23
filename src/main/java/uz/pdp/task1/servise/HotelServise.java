package uz.pdp.task1.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Hotel;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.repo.HotelRepo;

import java.util.List;

@Service
public class HotelServise {

    @Autowired
    HotelRepo hotelRepo;

    public ApiResponse saveOrEdit(Hotel hotel) {

        try {
            hotelRepo.save(hotel);
        } catch (Exception e) {
            return new ApiResponse(false, hotel.getId() != null ? "not saved" : "not edited");
        }
        return new ApiResponse(true, hotel.getId() != null ? "saved" : "edited");


    }

    public ApiResponse delete(Long id) {
        try {
            hotelRepo.deleteById(id);
        } catch (Exception e) {
            return new ApiResponse(true, "not deleted");
        }
        return new ApiResponse(true, "deleted");

    }

    public ApiResponse get(Long id) {
        final Hotel hotel = hotelRepo.getById(id);
        return new ApiResponse(true,"success",hotel);
    }

    public ApiResponse getAll( ){
        List<Hotel> all = hotelRepo.findAll();
        return new ApiResponse(true,"success",all);
    }

}
