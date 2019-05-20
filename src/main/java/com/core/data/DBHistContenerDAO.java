package com.core.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
 * CRUD refers Create, Read, Update, Delete
 */
@Repository
public interface DBHistContenerDAO extends CrudRepository<DBHistContener, Integer> {

    List<DBHistContener> findAll();
    List<DBHistContener> findAllOrderedByIdDescending();
    List<DBHistContener> findAllOrderedByThemeDescending();

    List<DBHistContener> findById(@Param("id") int id);

    List<DBHistContener> findByTheme(@Param("theme") String theme);

}
