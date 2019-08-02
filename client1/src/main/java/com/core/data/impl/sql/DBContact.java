package com.core.data.impl.sql;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Address book item
 */
@Slf4j
@Entity
@XmlRootElement
@Table(name = "contact")
@NamedQueries(value = {
        @NamedQuery(
            name = "DBContact.findAll",
            query = "SELECT a FROM DBContact a"
        ),
        @NamedQuery(
            name = "DBContact.findAllOrderedByIdAscending",
            query = "SELECT c FROM DBContact c ORDER BY c.id ASC"
        ),
        @NamedQuery(
            name = "DBContact.findAllOrderedByVornameAscending",
            query = "SELECT c FROM DBContact c ORDER BY c.vorname ASC"
        ),
        @NamedQuery(
            name = "DBContact.findAllOrderedByNachnameAscending",
            query = "SELECT c FROM DBContact c ORDER BY c.nachname ASC"
        ),

        @NamedQuery(
            name = "DBContact.findById",
            query = "SELECT a FROM DBContact a WHERE a.id = :id"
        ),

        @NamedQuery(
            name = "DBContact.findByVorname",
            query = "SELECT a FROM DBContact a WHERE a.vorname = :vorname"
        ),

        @NamedQuery(
            name = "DBContact.findByNachname",
            query = "SELECT a FROM DBContact a WHERE a.nachname = :nachname"
        ),

        @NamedQuery(
            name = "DBContact.findByVorNNachname",
            query = "SELECT a FROM DBContact a WHERE a.vorname = :vorname AND a.nachname = :nachname "
        ),

        @NamedQuery(
            name = "DBContact.findByEmailaddresse",
            query = "SELECT a FROM DBContact a WHERE a.emailadresse = :emailadresse AND a.nachname = :emailadresse "
        )
})
public class DBContact extends ADBBaseItem {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("vorname", vorname).append("\n", "")
                .append("nachname", nachname).append("\n", "")
                .append("emailadresse", emailadresse).append("\n", "")
                .toString();
    }

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Basic
    @Column(columnDefinition = "VARCHAR(256)", nullable=false)
    private String vorname;

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Basic
    @Column(columnDefinition = "VARCHAR(256)", nullable=false)
    private String nachname;

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Basic
    @Column(columnDefinition = "VARCHAR(256)", nullable=false)
    private String emailadresse;

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Basic
    @Column(columnDefinition = "BOOLEAN", nullable=false)
    private boolean enabled;

}
