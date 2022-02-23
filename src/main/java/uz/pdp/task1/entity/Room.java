package uz.pdp.task1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.task1.entity.template.AbsLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room extends AbsLong {
    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private Integer size;

    @ManyToOne(optional = false)
    private Hotel hotel;
}
