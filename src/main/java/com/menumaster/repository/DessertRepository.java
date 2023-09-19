package com.menumaster.repository;

import com.menumaster.entity.menu.Dessert;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DessertRepository extends JpaRepository<Dessert, Long> {
    @EntityGraph(attributePaths = {"cuisine"})
    @Override
    @NonNull
    List<Dessert> findAll();

    @EntityGraph(attributePaths = {"cuisine"})
    @Override
    @NonNull
    List<Dessert> findAllById(@NonNull Iterable<Long> longs);
}
