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
@Table(name = "main_course")
public class MainCourse extends Meal {
    @OneToMany(mappedBy = "mainCourse")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Lunch> lunches;
}
