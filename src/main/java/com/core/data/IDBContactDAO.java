package com.core.data;

import com.core.data.impl.DBContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
 * CRUD refers Create, Read, Update, Delete
 */
@Repository
public interface IDBContactDAO extends JpaRepository<DBContact, Integer> {

    List<DBContact> findAll();
    List<DBContact> findAllOrderedByIdAscending();
    List<DBContact> findAllOrderedByVornameAscending();
    List<DBContact> findAllOrderedByNachnameAscending();

    List<DBContact> findById(@Param("id") int id);
    List<DBContact> findByVorname(@Param("vorname") String vorname);
    List<DBContact> findByNachname(@Param("nachname") String nachname);
    List<DBContact> findByVorNNachname(@Param("vorname") String vorname, @Param("nachname") String nachname);
    List<DBContact> findByEmailaddresse(@Param("emailadresse") String emailadresse);

}
