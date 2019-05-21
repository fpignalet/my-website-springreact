package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@Slf4j
@Entity
@XmlRootElement
@JsonRootName("DBHistContener")
@Table(name = "fpi_springcontener")
@NamedQueries(value = {
        @NamedQuery(name = "DBHistContener.findAll",
                query = "SELECT a FROM DBHistContener a"),
        @NamedQuery(name = "DBHistContener.findAllOrderedByIdDescending",
                query = "SELECT c FROM DBHistContener c ORDER BY c.id DESC"),
        @NamedQuery(name = "DBHistContener.findAllOrderedByThemeDescending",
                query = "SELECT c FROM DBHistContener c ORDER BY c.entrytheme DESC"),

        @NamedQuery(name = "DBHistContener.findById",
                query = "SELECT a FROM DBHistContener a WHERE a.id = :id"),

        @NamedQuery(name = "DBHistContener.findByTheme",
                query = "SELECT a FROM DBHistContener a WHERE a.entrytheme = :entrytheme")
})
public class DBHistContener {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("entryPHOTO", entryPHOTO).append("\n", "")
            .append("entrydate", entrydate).append("\n", "")
            .append("entrytheme", entrytheme).append("\n", "")
            .append("entryitems", entryitems).append("\n", "")
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

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<String> entryPHOTO;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<String> entrydate;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<String> entrytheme;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<DBHistItem> entryitems;
}
