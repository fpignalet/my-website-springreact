package com.core.data.impl.sql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Entity
@Table(name = "histsubs")
@JsonRootName("contentlist")
@JsonIgnoreProperties({"id", "parent"})
public class DBHistSub extends ADBBaseItem {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("listtext", listtext).append("\n", "")
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
    private DBHistContent parent;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<DBHistText> listtext;
}
