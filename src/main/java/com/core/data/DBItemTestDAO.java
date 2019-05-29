package com.core.data;

import com.core.data.impl.DBItemTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
 * CRUD refers Create, Read, Update, Delete
 */
@Repository
public interface DBItemTestDAO extends CrudRepository<DBItemTest, Integer> {

    List<DBItemTest> findAll();
    List<DBItemTest> findAllOrderedByIdDescending();
    List<DBItemTest> findAllOrderedByNameDescending();

    List<DBItemTest> findById(@Param("id") int id);
    List<DBItemTest> findByName(@Param("name") String name);

}
