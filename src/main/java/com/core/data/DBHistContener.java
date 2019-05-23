package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Slf4j
@Entity
@XmlRootElement
@JsonRootName("DBHistContener")
@Table(name = "fpi_springcontener")
//@ManyToMany(cascade=CascadeType.ALL)
//@JoinColumn(name = "conteneritems")
@NamedQueries(value = {
        @NamedQuery(name = "DBHistContener.findAll",
                query = "SELECT a FROM DBHistContener a"),
        @NamedQuery(name = "DBHistContener.findAllOrderedByIdDescending",
                query = "SELECT c FROM DBHistContener c ORDER BY c.id DESC"),
        @NamedQuery(name = "DBHistContener.findAllOrderedByNameDescending",
                query = "SELECT c FROM DBHistContener c ORDER BY c.contenername DESC"),

        @NamedQuery(name = "DBHistContener.findById",
                query = "SELECT a FROM DBHistContener a WHERE a.id = :id"),

        @NamedQuery(name = "DBHistContener.findByName",
                query = "SELECT a FROM DBHistContener a WHERE a.contenername = :contenername")
})
public class DBHistContener implements Serializable {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("contenerphoto", contenerphoto).append("\n", "")
            .append("contenerdate", contenerdate).append("\n", "")
            .append("contenername", contenername).append("\n", "")
            .append("contenersubname", contenersubname).append("\n", "")
            .append("conteneritems", conteneritems).append("\n", "")
            .toString();
    }

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Id
    @Column(nullable = false, columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(columnDefinition = "VARCHAR(256)")
    private String contenerphoto;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(columnDefinition = "VARCHAR(256)")
    private String contenerdate;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(columnDefinition = "VARCHAR(256)")
    private String contenername;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(columnDefinition = "VARCHAR(256)")
    private String contenersubname;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(nullable = false)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<DBHistItem> conteneritems;
}
