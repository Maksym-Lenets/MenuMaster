package com.menumaster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime created = LocalDateTime.now();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "orders_lunches",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "lunch_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Lunch> lunches = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
        orphanRemoval = true,
        mappedBy = "order")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.PRIVATE)
    private List<OrderedDrink> drinks = new ArrayList<>();

    /**
     * Adds a list of ordered drinks to the order.
     *
     * <p>This method takes a list of {@code OrderedDrink} objects and associates them
     * with the current order. It iterates through the list, sets the order
     * reference for each ordered drink, and adds them to the order's list of
     * drinks.
     *
     * @param orderedDrinks The list of ordered drinks to be added to the order.
     */
    public void addOrderedDrinks(List<OrderedDrink> orderedDrinks) {
        orderedDrinks.forEach(od -> od.setOrder(this));
        drinks.addAll(orderedDrinks);
    }
}
