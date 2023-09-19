package com.menumaster.entity;

import com.menumaster.entity.menu.Drink;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordered_drink")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderedDrink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "drink_id")
    private Drink drink;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "ice_cubes")
    private boolean iceCubes;

    private boolean lemons;
}
