package com.core.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@Entity
@Table(name = "fpi_spring1")
@XmlRootElement

@NamedQueries(value = {
        @NamedQuery(name = "DBItem1.findAll",
                query = "SELECT a FROM DBItem1 a"),
        @NamedQuery(name = "DBItem1.findById",
                query = "SELECT a FROM DBItem1 a WHERE a.id = :id"),
        @NamedQuery(name = "DBItem1.findByName",
                query = "SELECT a FROM DBItem1 a WHERE a.name = :name"),
        @NamedQuery(name = "DBItem1.findAllOrderedByNameDescending",
                query = "SELECT c FROM DBItem1 c ORDER BY c.name DESC")
})

public class DBItem1 {

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
