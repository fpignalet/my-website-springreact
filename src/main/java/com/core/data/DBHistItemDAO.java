package com.core.data;

import com.core.data.impl.DBHistItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
 * CRUD refers Create, Read, Update, Delete
 */
@Repository
public interface DBHistItemDAO extends CrudRepository<DBHistItem, Integer> {
}
