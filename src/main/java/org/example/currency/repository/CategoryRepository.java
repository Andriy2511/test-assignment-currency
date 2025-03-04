package org.example.currency.repository;

import org.example.currency.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    @Modifying
    @Query("UPDATE Category c SET c.name = :newName WHERE c.name = :categoryName")
    void updateCategoryName(@Param("categoryName") String categoryName, @Param("newName") String newName);

    void deleteByName(String name);
}
