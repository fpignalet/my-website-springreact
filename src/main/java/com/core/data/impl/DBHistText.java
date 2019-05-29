package com.core.data.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Slf4j
@Entity
@Table(name = "histtext")
@JsonRootName("listtext")
@JsonIgnoreProperties({"id", "parent"})
public class DBHistText extends ADBBaseItem {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("data", data).append("\n", "")
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
    private DBHistSub parent;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(columnDefinition = "TEXT")
    private String data;
}
