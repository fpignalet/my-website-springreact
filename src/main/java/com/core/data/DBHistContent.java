package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Slf4j
@Entity
@JsonRootName("contentitems")
@Table(name = "fpi_springcontent")
public class DBHistContent implements Serializable {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("entrydesc", entrydesc).append("\n", "")
            .append("contentlist", contentlist).append("\n", "")
            .toString();
    }

    /**
     *
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, columnDefinition = "int(11)")
    private int id;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @ManyToOne
    private DBHistItem parent;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(columnDefinition = "VARCHAR(256)")
    private String entrydesc;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(nullable = false)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<DBHistSub> contentlist;
}
