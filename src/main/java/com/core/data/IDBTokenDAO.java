package com.core.data;

import com.core.data.impl.DBToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDBTokenDAO extends JpaRepository<DBToken, String> {
    List<DBToken> findByConfirmationToken(@Param("confirmationToken") String confirmationToken);
}