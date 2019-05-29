package com.core.data;

import com.core.data.impl.DBHistContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
 * CRUD refers Create, Read, Update, Delete
 */
@Repository
public interface DBHistContentDAO extends CrudRepository<DBHistContent, Integer> {
}
