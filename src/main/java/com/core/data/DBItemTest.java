package com.core.data;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@Slf4j
@Entity
@XmlRootElement
@Table(name = "fpi_spring1")
@NamedQueries(value = {
        @NamedQuery(name = "DBItemTest.findAll",
                query = "SELECT a FROM DBItemTest a"),
        @NamedQuery(name = "DBItemTest.findAllOrderedByIdDescending",
                query = "SELECT c FROM DBItemTest c ORDER BY c.id DESC"),
        @NamedQuery(name = "DBItemTest.findAllOrderedByNameDescending",
                query = "SELECT c FROM DBItemTest c ORDER BY c.name DESC"),

        @NamedQuery(name = "DBItemTest.findById",
                query = "SELECT a FROM DBItemTest a WHERE a.id = :id"),

        @NamedQuery(name = "DBItemTest.findByName",
                query = "SELECT a FROM DBItemTest a WHERE a.name = :name")
})
public class DBItemTest {

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
