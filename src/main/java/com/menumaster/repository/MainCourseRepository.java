package com.menumaster.repository;

import com.menumaster.entity.menu.MainCourse;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainCourseRepository extends JpaRepository<MainCourse, Long> {
    @EntityGraph(attributePaths = {"cuisine"})
    @Override
    @NonNull
    List<MainCourse> findAll();

    @EntityGraph(attributePaths = {"cuisine"})
    @Override
    @NonNull
    List<MainCourse> findAllById(@NonNull Iterable<Long> longs);
}
