package com.core.data.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@Slf4j
@Entity
@XmlRootElement
@Table(name = "itemtest")
@NamedQueries(value = {
        @NamedQuery(
            name = "DBItemTest.findAll",
            query = "SELECT a FROM DBItemTest a"
        ),
        @NamedQuery(
            name = "DBItemTest.findAllOrderedByIdAscending",
            query = "SELECT c FROM DBItemTest c ORDER BY c.id ASC"
        ),
        @NamedQuery(
            name = "DBItemTest.findAllOrderedByNameAscending",
            query = "SELECT c FROM DBItemTest c ORDER BY c.name ASC"
        ),

        @NamedQuery(
            name = "DBItemTest.findById",
            query = "SELECT a FROM DBItemTest a WHERE a.id = :id"
        ),

        @NamedQuery(
            name = "DBItemTest.findByName",
            query = "SELECT a FROM DBItemTest a WHERE a.name = :name"
        )
})
public class DBItemTest extends ADBBaseItem {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name).append("\n", "")
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
    @Column(unique = true, nullable = false)
    private String name;

}
