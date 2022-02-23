package uz.pdp.task1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.task1.entity.template.AbsLong;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel extends AbsLong {

    @Column(nullable = false)
    private String name;
}
