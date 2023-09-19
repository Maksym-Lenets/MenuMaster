package com.menumaster.entity.menu;

import com.menumaster.entity.Lunch;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "dessert")
public class Dessert extends Meal {
    @OneToMany(mappedBy = "dessert")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Lunch> lunches;
}
