package uz.pdp.task1.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Hotel;
import uz.pdp.task1.entity.Room;
import uz.pdp.task1.exeptions.PageSizeException;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.payload.RoomDto;
import uz.pdp.task1.repo.HotelRepo;
import uz.pdp.task1.repo.RoomRepo;
import uz.pdp.task1.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    RoomRepo roomRepo;

    @Autowired
    HotelRepo hotelRepo;

    public ApiResponse saveOrEdit(RoomDto roomDto) {
        try {
            Room room = generateRoom(roomDto);
            roomRepo.save(room);
        } catch (Exception e) {
            return new ApiResponse(false, "not saved");
        }

        return new ApiResponse(true, "saved");


    }

    public ApiResponse delete(Long id) {
        try {
            roomRepo.deleteById(id);
        } catch (Exception e) {
            return new ApiResponse(false, "not deleted");
        }
        return new ApiResponse(true, "deleted");


    }

    public Room generateRoom(RoomDto roomDto) {
        Room room = new Room();
        room.setId(room.getId());
        room.setFloor(roomDto.getFloor());
        room.setNumber(roomDto.getNumber());
        room.setSize(roomDto.getSize());

        room.setHotel(hotelRepo.getById(roomDto.getHotelId()));
        return room;
    }

    public RoomDto generateRoomDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setHotelId(room.getHotel().getId());
        roomDto.setFloor(room.getFloor());
        roomDto.setNumber(room.getNumber());
        roomDto.setSize(room.getSize());
        return roomDto;

    }

    public ApiResponse getAll(Long hotelId, Integer page, Integer size, boolean all) throws PageSizeException {
        Long totalElements = 0L;
        List<RoomDto> roomDtoList = new ArrayList<>();
        if (hotelId != null) {

            if (all) {
                roomDtoList = roomRepo.getByHotelId(hotelId)
                        .stream()
                        .map(this::generateRoomDto)
                        .collect(Collectors.toList());

            } else {
                Page<Room> roomPage = roomRepo.getByHotelId(hotelId, CommonUtils.descOrAscByCreatedAtPageable(page, size, false));
                roomDtoList = roomPage.getContent()
                        .stream()
                        .map(this::generateRoomDto)
                        .collect(Collectors.toList());
                totalElements = roomPage.getTotalElements();
            }
        }else {
            Page<Room> allPage = roomRepo.findAll(CommonUtils.descOrAscByCreatedAtPageable(page, size, false));
            roomDtoList = allPage.getContent()
                    .stream()
                    .map(this::generateRoomDto)
                    .collect(Collectors.toList());
            totalElements = allPage.getTotalElements();
        }
        return new ApiResponse(true,"pageable ",roomDtoList,totalElements);


    }
}
