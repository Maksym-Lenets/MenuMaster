package com.menumaster.entity.menu;

import com.menumaster.entity.OrderedDrink;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "drink")
public class Drink extends MenuItem {
    @OneToMany(mappedBy = "drink")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<OrderedDrink> orderedDrinks;
}
