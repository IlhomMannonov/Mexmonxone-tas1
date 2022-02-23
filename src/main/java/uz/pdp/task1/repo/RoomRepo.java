package uz.pdp.task1.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Room;


import java.util.List;

@Service
public interface RoomRepo extends JpaRepository<Room, Long> {

    Page<Room> getByHotelId(Long hotel_id, Pageable pageable);
    List<Room>getByHotelId(Long hotel_id );
}
