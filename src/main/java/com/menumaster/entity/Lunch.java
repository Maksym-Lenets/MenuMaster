package com.menumaster.entity;

import com.menumaster.entity.menu.Dessert;
import com.menumaster.entity.menu.MainCourse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "lunch")
@NoArgsConstructor
@AllArgsConstructor
public class Lunch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "main_course_id")
    private MainCourse mainCourse;

    @ManyToOne
    @JoinColumn(name = "dessert_id")
    private Dessert dessert;
}
