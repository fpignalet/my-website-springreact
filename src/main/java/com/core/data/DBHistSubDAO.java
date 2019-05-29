package com.core.data;

import com.core.data.impl.DBHistSub;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
 * CRUD refers Create, Read, Update, Delete
 */
@Repository
public interface DBHistSubDAO extends CrudRepository<DBHistSub, Integer> {
}
