package com.menumaster.repository;

import com.menumaster.entity.menu.CuisineOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuisineOriginRepository extends JpaRepository<CuisineOrigin, Long> {
    /**
     * Retrieves a {@link CuisineOrigin} entity by its description.
     *
     * <p>This method queries the database for a {@link CuisineOrigin} entity with the
     * specified description and returns it if found, wrapped in an
     * {@link Optional}. If no matching entity is found, an empty {@link Optional}
     * is returned.
     *
     * @param description The description of the {@link CuisineOrigin} to retrieve.
     * @return An {@link Optional} containing the matching {@link CuisineOrigin}
     *         entity, or an empty {@link Optional} if no match is found.
     */
    Optional<CuisineOrigin> findByDescription(String description);
}
