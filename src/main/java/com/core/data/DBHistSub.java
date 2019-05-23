package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.ArrayList;

@Slf4j
@Entity
@JsonRootName("contentlist")
@Table(name = "fpi_springsubs")
public class DBHistSub {

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
    @Column(nullable = false, columnDefinition = "int(11)")
    private int parentId;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    @Column(nullable = false)
    private ArrayList<String> data;
}
