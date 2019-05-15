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
public interface DBItem1DAO extends CrudRepository<DBItem1, Integer> {

    List<DBItem1> findAll();
    List<DBItem1> findAllOrderedByNameDescending();

    List<DBItem1> findById(@Param("id") int id);

    List<DBItem1> findByName(@Param("name") String name);

}
