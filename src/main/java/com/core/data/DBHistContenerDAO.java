package com.core.data;

import com.core.data.impl.DBHistContener;
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
    List<DBHistContener> findAllOrderedByIdAscending();
    List<DBHistContener> findAllOrderedByIdDescending();
    List<DBHistContener> findAllOrderedByNameAscending();
    List<DBHistContener> findAllOrderedByNameDescending();

    List<DBHistContener> findById(@Param("id") int id);
    List<DBHistContener> findByName(@Param("contenername") String contenername);

}
