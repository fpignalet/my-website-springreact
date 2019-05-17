package com.core.data;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@JsonRootName("CVboulothead")
public class CVboulothead extends JSONParser {

    private static class CVboulots extends ArrayList<CVboulothead> {};

    public static void main(String[] args) {
        try {
            final String data = "{\"CVboulots\":[" +
                    "{" +
                        "\"boulotentrydate\": [ \"DATE1\" ]," +
                        "\"boulotentryboite\":[ \"BOITE1\" ]," +
                        "\"boulotentryitems\":[" +
                            "{" +
                                "\"boulotentryitem\": [ \"ITEM11\" ]," +
                                "\"boulotentrytitle\": [ \"TITLE11\" ]," +
                                "\"boulotentrycontent\": [ \"CONTENT11\" ]" +
                            "}," +
                            "{" +
                                "\"boulotentryitem\": [ \"ITEM12\" ]," +
                                "\"boulotentrytitle\": [ \"TITLE12\" ]," +
                                "\"boulotentrycontent\": [ \"CONTENT12\" ]" +
                            "}" +
                        "]" +
                    "}," +
                    "{" +
                    "\"boulotentrydate\": [ \"DATE2\" ]," +
                    "\"boulotentryboite\":[ \"BOITE2\" ]," +
                        "\"boulotentryitems\":[" +
                            "{" +
                                "\"boulotentryitem\": [ \"ITEM21\" ]," +
                                "\"boulotentrytitle\": [ \"TITLE21\" ]," +
                                "\"boulotentrycontent\": [ \"CONTENT21\" ]" +
                            "}," +
                            "{" +
                                "\"boulotentryitem\": [ \"ITEM22\" ]," +
                                "\"boulotentrytitle\": [ \"TITLE22\" ]," +
                                "\"boulotentrycontent\": [ \"CONTENT221\", \"CONTENT222\" ]" +
                            "}" +
                        "]" +
                    "}" +
                "]}";
            parse(data,
                CVboulots.class,
                new Class[]{
                    CVboulothead.class,
                    CVboulotitem.class
                }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("boulotentrydate", boulotentrydate)
            .append("boulotentryboite", boulotentryboite)
            .append("boulotentryitems", boulotentryitems)
            .toString();
    }

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<String> boulotentrydate;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<String> boulotentryboite;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private ArrayList<CVboulotitem> boulotentryitems;
}
