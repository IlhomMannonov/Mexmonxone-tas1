package uz.pdp.task1.payload;

import lombok.Data;
import uz.pdp.task1.entity.Hotel;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
public class RoomDto {
    private Long id;

    private Integer number;

    private Integer floor;

    private Integer size;

    private Long hotelId;
}
