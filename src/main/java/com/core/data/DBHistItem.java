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
@JsonRootName("conteneritems")
@Table(name = "fpi_springitem")
//@ManyToMany(cascade=CascadeType.ALL)
//@JoinColumn(name = "contentitems")
public class DBHistItem implements Serializable {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("histkind", histkind).append("\n", "")
            .append("histtitle", histtitle).append("\n", "")
            .append("histdesc", histdesc).append("\n", "")
            .append("histduration", histduration).append("\n", "")
            .append("histextra", histextra).append("\n", "")
            .append("contentitems", contentitems).append("\n", "")
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
    private DBHistContener parent;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(columnDefinition = "VARCHAR(256)")
    private String histkind;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(columnDefinition = "VARCHAR(256)")
    private String histtitle;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(columnDefinition = "VARCHAR(256)")
    private String histdesc;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(columnDefinition = "VARCHAR(256)")
    private String histduration;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(columnDefinition = "VARCHAR(256)")
    private String histextra;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(nullable = false)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<DBHistContent> contentitems;
}
