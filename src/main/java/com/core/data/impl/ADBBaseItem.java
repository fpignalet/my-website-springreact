package com.core.data.impl;

import java.io.Serializable;

public abstract class ADBBaseItem implements Serializable {
    public String toJSONString() {
        /*

                    "lang_title": "DATA",
                    [
                        "lang_f1desc": ["DATA"]
                        "lang_f1level": ["DATA"]
                        "lang_f1text": ["DATA"]
                    ],
                    "mskills_title": "",
                    [
                        "mskills_f1desc": ["DATA"]
                        "mskills_f1level": ["DATA"]
                        "mskills_f1text": ["DATA"]
                    ],
                    "boulotentry0date": ["DATA"],
                    "boulotentry0boite": ["DATA",["DATA"]],
                    "boulotentry0desc": ["DATA"],
                    "loisirs_f1date":  ["DATA"],
                    "loisirs_f1boite":  null,
                    "loisirs_f1desc":  null,
                    "loisirs_f11item":  ["DATA"],
                    "loisirs_f11title":  ["DATA"],
                    "loisirs_f11content":
                        [["DATA","DATA",[["DATA","DATA"]]]]
                    "edu_f1date":  ["DATA"],
                    "edu_f1boite":  null,
                    "edu_f1desc":  null
                    "edu_f11item":  ["DATA"],
                    "edu_f11title":  ["DATA"],
                    "edu_f11content":
                        [["DATA","DATA",[["DATA","DATA"]]]]
         */
        return "{" +
            "\"moi_photo\": \"PHOTO\"," +
            "\"moi_name\": \"NAME\"," +
            "\"info_raisonsociale\": \"RAISONSOCIALE\"," +
            "\"info_adresse\": \"ADRESSE\"," +
            "\"info_email\": \"EMAIL\"," +
            "\"info_phonenum\": \"PHONENUM\"," +
            "\"info_geburstag\": \"GEBURSTAG\"," +
            "\"exp_title\": \"EXPTITLE\"," +
            "\"exp_content\": [{\"content1\": \"EXPCONTENT1\"}, {\"content2\": \"EXPCONTENT2\"}]," +
            "\"lang_title\": \"LANGTITLE\"," +
            "\"boulotentry0date\": [{\"content1\": \"DATECONTENT1\"}, {\"content2\": \"DATECONTENT2\"}]," +
            "\"boulotentry0boite\": [{\"content1\": \"BOITECONTENT1\"}, {\"content2\": \"BOITECONTENT2\"}]," +
            "\"boulotentry0desc\": [{\"content1\": \"DESCCONTENT1\"}, {\"content2\": \"DESCCONTENT2\"}]," +
            "\"boulotentry01item\": [{\"content1\": \"ITEMCONTENT1\"}, {\"content2\": \"ITEMCONTENT2\"}]," +
            "\"boulotentry01title\": [{\"content1\": \"TITLECONTENT1\"}, {\"content2\": \"TITLECONTENT2\"}]," +
            "\"boulotentry01content\": [{\"content1\": \"CONTENTCONTENT1\"}, {\"content2\": \"CONTENTCONTENT2\"}]," +
            "\"boulotentry1date\": [{\"content1\": \"DATECONTENT1\"}, {\"content2\": \"DATECONTENT2\"}]," +
            "\"boulotentry1boite\": [{\"content1\": \"BOITECONTENT1\"}, {\"content2\": \"BOITECONTENT2\"}]," +
            "\"boulotentry1desc\": [{\"content1\": \"DESCCONTENT1\"}, {\"content2\": \"DESCCONTENT2\"}]," +
            "\"boulotentry11item\": [{\"content1\": \"ITEMCONTENT1\"}, {\"content2\": \"ITEMCONTENT2\"}]," +
            "\"boulotentry11title\": [{\"content1\": \"TITLECONTENT1\"}, {\"content2\": \"TITLECONTENT2\"}]," +
            "\"boulotentry11content\": [{\"content1\": \"CONTENTCONTENT1\"}, {\"content2\": \"CONTENTCONTENT2\"}]," +
            "\"boulotentry2date\": [{\"content1\": \"DATECONTENT1\"}, {\"content2\": \"DATECONTENT2\"}]," +
            "\"boulotentry2boite\": [{\"content1\": \"BOITECONTENT1\"}, {\"content2\": \"BOITECONTENT2\"}]," +
            "\"boulotentry2desc\": [{\"content1\": \"DESCCONTENT1\"}, {\"content2\": \"DESCCONTENT2\"}]," +
            "\"boulotentry21item\": [{\"content1\": \"ITEMCONTENT1\"}, {\"content2\": \"ITEMCONTENT2\"}]," +
            "\"boulotentry21title\": [{\"content1\": \"TITLECONTENT1\"}, {\"content2\": \"TITLECONTENT2\"}]," +
            "\"boulotentry21content\": [{\"content1\": \"CONTENTCONTENT1\"}, {\"content2\": \"CONTENTCONTENT2\"}]," +
            "\"boulotentry3date\": [{\"content1\": \"DATECONTENT1\"}, {\"content2\": \"DATECONTENT2\"}]," +
            "\"boulotentry3boite\": [{\"content1\": \"BOITECONTENT1\"}, {\"content2\": \"BOITECONTENT2\"}]," +
            "\"boulotentry3desc\": [{\"content1\": \"DESCCONTENT1\"}, {\"content2\": \"DESCCONTENT2\"}]," +
            "\"boulotentry31item\": [{\"content1\": \"ITEMCONTENT1\"}, {\"content2\": \"ITEMCONTENT2\"}]," +
            "\"boulotentry31title\": [{\"content1\": \"TITLECONTENT1\"}, {\"content2\": \"TITLECONTENT2\"}]," +
            "\"boulotentry31content\": [{\"content1\": \"CONTENTCONTENT1\"}, {\"content2\": \"CONTENTCONTENT2\"}]," +
            "\"boulotentry4date\": [{\"content1\": \"DATECONTENT1\"}, {\"content2\": \"DATECONTENT2\"}]," +
            "\"boulotentry4boite\": [{\"content1\": \"BOITECONTENT1\"}, {\"content2\": \"BOITECONTENT2\"}]," +
            "\"boulotentry4desc\": [{\"content1\": \"DESCCONTENT1\"}, {\"content2\": \"DESCCONTENT2\"}]," +
            "\"boulotentry41item\": [{\"content1\": \"ITEMCONTENT1\"}, {\"content2\": \"ITEMCONTENT2\"}]," +
            "\"boulotentry41title\": [{\"content1\": \"TITLECONTENT1\"}, {\"content2\": \"TITLECONTENT2\"}]," +
            "\"boulotentry41content\": [{\"content1\": \"CONTENTCONTENT1\"}, {\"content2\": \"CONTENTCONTENT2\"}]," +
            "\"boulotentry5date\": [{\"content1\": \"DATECONTENT1\"}, {\"content2\": \"DATECONTENT2\"}]," +
            "\"boulotentry5boite\": [{\"content1\": \"BOITECONTENT1\"}, {\"content2\": \"BOITECONTENT2\"}]," +
            "\"boulotentry5desc\": [{\"content1\": \"DESCCONTENT1\"}, {\"content2\": \"DESCCONTENT2\"}]," +
            "\"boulotentry51item\": [{\"content1\": \"ITEMCONTENT1\"}, {\"content2\": \"ITEMCONTENT2\"}]," +
            "\"boulotentry51title\": [{\"content1\": \"TITLECONTENT1\"}, {\"content2\": \"TITLECONTENT2\"}]," +
            "\"boulotentry51content\": [{\"content1\": \"CONTENTCONTENT1\"}, {\"content2\": \"CONTENTCONTENT2\"}]," +
            "\"boulotentry6date\": [{\"content1\": \"DATECONTENT1\"}, {\"content2\": \"DATECONTENT2\"}]," +
            "\"boulotentry6boite\": [{\"content1\": \"BOITECONTENT1\"}, {\"content2\": \"BOITECONTENT2\"}]," +
            "\"boulotentry6desc\": [{\"content1\": \"DESCCONTENT1\"}, {\"content2\": \"DESCCONTENT2\"}]," +
            "\"boulotentry61item\": [{\"content1\": \"ITEMCONTENT1\"}, {\"content2\": \"ITEMCONTENT2\"}]," +
            "\"boulotentry61title\": [{\"content1\": \"TITLECONTENT1\"}, {\"content2\": \"TITLECONTENT2\"}]," +
            "\"boulotentry61content\": [{\"content1\": \"CONTENTCONTENT1\"}, {\"content2\": \"CONTENTCONTENT2\"}]," +
            "\"boulotentry7date\": [{\"content1\": \"DATECONTENT1\"}, {\"content2\": \"DATECONTENT2\"}]," +
            "\"boulotentry7boite\": [{\"content1\": \"BOITECONTENT1\"}, {\"content2\": \"BOITECONTENT2\"}]," +
            "\"boulotentry7desc\": [{\"content1\": \"DESCCONTENT1\"}, {\"content2\": \"DESCCONTENT2\"}]," +
            "\"boulotentry71item\": [{\"content1\": \"ITEMCONTENT1\"}, {\"content2\": \"ITEMCONTENT2\"}]," +
            "\"boulotentry71title\": [{\"content1\": \"TITLECONTENT1\"}, {\"content2\": \"TITLECONTENT2\"}]," +
            "\"boulotentry71content\": [{\"content1\": \"CONTENTCONTENT1\"}, {\"content2\": \"CONTENTCONTENT2\"}]" +
            "}";
    }
}
