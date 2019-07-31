package com.core.data.impl.sql;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "token")
@NamedQueries(value = {
    @NamedQuery(
        name = "DBToken.findByConfirmationToken",
        query = "SELECT a FROM DBToken a WHERE a.confirmationToken = :confirmationToken"
    )
})
public class DBToken {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("confirmationToken", confirmationToken).append("\n", "")
            .toString();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private long tokenid;

    @Column(name="confirmationToken")
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private Date createdDate;

    @OneToOne(targetEntity = DBContact.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id")
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private DBContact user;

}
