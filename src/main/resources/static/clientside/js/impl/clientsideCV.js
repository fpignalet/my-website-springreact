'use strict';

/*************************************************************************************
 * INCLUDES CODE
 *************************************************************************************/
import {
    CLISIDE_BASE,
    CLISIDE_DOM,
    CLISIDE_LOADER
} from "../lib/clientside.js";

/*************************************************************************************
 * INCLUDES DATA
 *************************************************************************************/

/*************************************************************************************
 * IMPLEMENTATION: CV CREATION UTILITIES
 *************************************************************************************/
/// @brief class to make boite/boulot items add easy
export class CLISIDE_CVCREATEHISTORY extends CLISIDE_DOM {

    /// ctor
    /// @param id contains an identifier used for construction
    constructor(id) {
        super(id);
    }

    //------------------------------------------------------------------
    // BOITE INFO
    //------------------------------------------------------------------
    /// @brief add boite info
    /// @param contener is the destination DOM
    /// @param data contains data_CVboulotXX
    addENTRY(contener, data) {
        const keys = Object.keys(data);

        // -------------------------------------
        // boulotentryXXdate => [
        //      data    -> data[iddate][0]
        // ]
        const iddate = keys[0];
        const valuedate = data[iddate][0];
        this.addtext(contener, iddate, valuedate);

        // -------------------------------------
        // boulotentryXXboite => [
        //      null | href,       -> data[idboite][0]
        //      [ data ]           -> data[idboite][1]
        // ]
        const idboite = keys[1];
        const it = contener.getElementById(idboite);
        if(null == it) {
            //sometimes boulotentryXXboite is null
            return;
        }

        //so, when boulotentryXXboite is not null (meaning that there is an idboite):
        let valueboite;

        valueboite = data[idboite][0]; //"http..."
        if(null != valueboite) {
            //sometimes there is an href
            it.setAttribute("href", valueboite);
        }

        valueboite = data[idboite][1];
        if(null != valueboite){

            if(null != valueboite[0]){
                it.appendChild(contener.createTextNode(valueboite[0]));
            }

            if(null != valueboite[1]){
                it.parentNode.appendChild(contener.createElement("br"));
                it.parentNode.appendChild(contener.createTextNode(valueboite[1]));
            }

        }

//        console.log(this.getFuncName() + "OK");

    }

    //------------------------------------------------------------------
    // BOULOTS LEFT COLUMN
    //------------------------------------------------------------------
    /// @brief add a th element in current tr with id = data[0]
    /// @param contener is the destination DOM
    /// @param tr is the current contener in table
    /// @param data is formatted as
    ///     "boulotentry1item" => [ data ]
    addLcol(contener, tr, data) {
        //---------------------------
        //then tr left part with th containing title
        const th = tr.appendChild(contener.createElement("th"));

        // -------------------------------------
        // boulotentryXXitem,            ->  data[0]
        const idboulot = data[0];
        th.setAttribute("id", idboulot);
        // [
        //     Project / Product / ...,  ->  data[1][0]
        //     Name,                     ->  data[1][1]
        //     Duration,                 ->  data[1][2]
        //     ?                         ->  data[1][3]
        // ],
        if(null == data[1]) {
            //data[1] may be null
            return;
        }

        let valueboulot;

        valueboulot = data[1][0];
        if(null != valueboulot) {
            th.appendChild(contener.createTextNode(valueboulot));
            th.appendChild(contener.createElement('br'));
        }

        valueboulot = data[1][1];
        if(null != valueboulot) {
            const span = contener.createElement("span");
            span.className = "w3-tag w3-teal w3-round";
            span.innerHTML = valueboulot;
            th.appendChild(span);
        }

        valueboulot = data[1][2];
        if(null != valueboulot) {
            th.appendChild(contener.createElement("br"));
            th.appendChild(contener.createTextNode(valueboulot));
        }

        valueboulot = data[1][3];
        if(null != valueboulot) {
            th.appendChild(contener.createElement("br"));
            th.appendChild(contener.createTextNode(valueboulot));
        }

//        console.log(this.getFuncName() + "OK");

    }

    //------------------------------------------------------------------
    // BOULOTS RIGHT COLUMN
    //------------------------------------------------------------------
    /// @brief add a column in a table row. The new column contains a table
    /// @param contener is the destination DOM
    /// @param tr is the table row
    /// @param data contains what is to be added
    /// @returns [ the new column, the table created inside the new column ]
    addRcol(contener, tr, data) {
        const td = tr.appendChild(contener.createElement("td"));
        const table = this.fillRcol(contener, td, data);
        return [ td, table ];
    }

    /// @brief push data in an existing column
    /// @param contener is the destination DOM
    /// @param td is the table div
    /// @param data contains what is to be added
    fillRcol(contener, td, data) {
        //-------------------------------
        //inside the right td there is 2 divs
        const div = this.adddisclosdivs(contener, td);
        //then there is the disclosure button
        this.adddisclosbutton(contener, div, data);

        //-------------------------------
        //then one more div which contains the details table
        // which will be disclosed when clicking disclosure button
        const table = this.adddiscloscontent(contener, div, data);
        return table;
    }

    //------------------------------------------------------------------
    // UTILS
    //------------------------------------------------------------------
    /// @brief to add an entry in right colum
    /// @param contener is the destination DOM
    /// @param rcol is the column which shall be filled
    /// @param item is the content of a boulotentryXXcontent
    /// @param index is the iter rank
    rcolinit(contener, rcol, item, index) {
        let tr;

        // boulotentryXXtitle
        tr = rcol[1].appendChild(contener.createElement("tr"));
        this.filltr(contener, tr, item[0], true);

        // boulotentryXXcontent
        tr = rcol[1].appendChild(contener.createElement("tr"));
        this.filltr(contener, tr, item[1], false);
        if(null != item[2]) {
            this.addtrsubs(contener, tr, item[2]);
        }
    }

    /// @brief to add a sub entry in right colum
    /// @param contener is the destination DOM
    /// @param table is the table to be filled
    /// @param item contains the data boulotentryXXtitle / boulotentryXXcontent
    /// @param index is the iter rank
    rcolappend(contener, table, item, index) {
        let tr;

        // boulotentryXXtitle
        tr = table.appendChild(contener.createElement("tr"));
        this.filltr(contener, tr, item[0], true);

        // boulotentryXXcontent
        tr = table.appendChild(contener.createElement("tr"));
        this.filltr(contener, tr, item[1], false);
        if(null != item[2]) {
            this.addtrsubs(contener, tr, item[2]);
        }
    }

}

/*************************************************************************************
 * IMPLEMENTATION: PRESENTATION
 *************************************************************************************/
/// CV creator helper
/// wrapper above CLISIDE_DOM and CLISIDE_CVCREATEHISTORY
/// uses CLISIDE_DOM to fill left side column
/// uses CLISIDE_CVCREATEHISTORY to fill right side column
export class CLISIDE_CVCREATE extends CLISIDE_BASE {

    /// ctor
    /// @param id contains an identifier used for construction
    constructor(id) {
        super(id);

        this.leftcreator = new CLISIDE_DOM(id);
        this.rightcreator = new CLISIDE_CVCREATEHISTORY(id);
    }

    //------------------------------------------------------------------
    // LEFT SIDE: CV TITLE
    //------------------------------------------------------------------
    /// @brief display CVtitle in CV left column
    /// @param contener is the destination DOM
    /// @param data is an array which contains details
    addTITLE(contener, data) {
        const keys = Object.keys(data);

        //data_CVtitle
        const idphoto = keys[0];
        const valuephoto = data[idphoto];
        this.leftcreator.addimage(contener, idphoto, valuephoto);

        const idname = keys[1];
        const valuename = data[idname];
        this.rightcreator.addtext(contener, idname, valuename);

    }

    //------------------------------------------------------------------
    // LEFT SIDE: CV PERSONAL INFO
    //------------------------------------------------------------------
    /// @brief display CVInfo in CV left column
    /// @param contener is the destination DOM
    /// @param data is an array which contains details
    addINFO(contener, data) {
        const local = this;

        Object.keys(data).forEach((item, index) => {

            //data_CVinfo
            const idinfo = item;
            const valueinfo = data[idinfo];
            //--------------------------
            if(true === Array.isArray(valueinfo)) {
                if(1 < valueinfo.length){
                    const it = contener.getElementById(idinfo);
                    local.leftcreator.addhref(contener, it, valueinfo[0], valueinfo[1]);
                }
                else {
                    local.leftcreator.addtext(contener, idinfo, valueinfo[0]);
                }
            }
            //--------------------------
            else {
                local.leftcreator.addtext(contener, idinfo, valueinfo[0]);
            }

        });
    }

    //------------------------------------------------------------------
    // LEFT SIDE: CV PERSONAL XP
    //------------------------------------------------------------------
    /// @brief display CVexperience in CV left column
    /// @param contener is the destination DOM
    /// @param data is an array which contains details
    addEXPERIENCE(contener, data) {
        const local = this;

        Object.keys(data).forEach((item, index) => {

            //data_CVexperience
            const id = item;
            const value = data[id];
            //--------------------------
            if(true === Array.isArray(value)) {
                value.forEach((_item, index) => {
                    const it = contener.getElementById(id);
                    local.leftcreator.addlistitem(contener, it, _item);
                });
            }
            //--------------------------
            else {
                local.leftcreator.addtext(contener, id, value);
            }

        });
    }

    //------------------------------------------------------------------
    // LEFT SIDE: CV MAIN SKILLS AND SPOKEN LANGUAGES
    //------------------------------------------------------------------
    /// @brief display CVskill / CVlangh in CV left column
    /// @param contener is the destination DOM
    /// @param adata is an array which contains details
    addDETAILS(contener, adata) {
        let keys = null;

        //--------------------------
        const head = adata[0];

        //data_CVskillshead / data_CVlanghead
        keys = Object.keys(head);
        const id = keys[0];
        const value = head[id];
        this.leftcreator.addtext(contener, id, value);

        //--------------------------
        const data = adata[1];
        if(null == data) {
            return;
        }

        const local = this;

        //data_CVskillsentries / data_CVlangentries
        data.forEach((item, index) => {
            keys = Object.keys(item);

            const iddesc = keys[0];
            const valuedesc = item[iddesc];
            local.rightcreator.addtext(contener, iddesc, valuedesc);

            const idlevel = keys[1];
            const valueevel = item[idlevel];
            const it = contener.getElementById(idlevel);
            it.setAttribute("style", "width:" + valueevel);

            const idtext = keys[2];
            const valuetext = item[idtext];
            local.rightcreator.addtext(contener, idtext, valuetext);

        });
    }

    //------------------------------------------------------------------
    // RIGHT SIDE: CV BOITE & BOULOTS & BILDUNG & HOBBIES ENTRIES
    // base principle: display the following content:
    // boulotentry1date
    // boulotentry1boite
    // boulotentry1desc     boulotentry1item
    //                      boulotentry1title
    //                      boulotentry1content
    //                      ...
    //------------------------------------------------------------------
    /// @brief handles right side contents with history entries (CVboulot) and extra info (CVbildung, CVhobby)
    /// @param contener is the destination DOM
    /// @param boite ...
    /// @param boulots ...
    fillrightside(contener, boite, boulots) {
        const local = this;

        const table = local.addHISTORYENTRY(contener, boite);
        Object.keys(boulots).forEach((key, _index) => {
            local.addHISTORYSUBENTRY(contener, table, boulots[key]);
        });
    }

    //------------------------------------------------------------------
    // RIGHT SIDE: INTERNAL IMPLEMENTATION
    //------------------------------------------------------------------
    /// @brief function to add boite/boulot item
    /// @param contener is the destination DOM
    /// @param data is an array which contains details
    addHISTORYENTRY(contener, data) {
        this.rightcreator.addENTRY(contener, data);

        //table containing boulotentries
        // desc = boulotentryXXdesc
        const desc = Object.keys(data)[2];
        const table = contener.getElementById(desc);
        return table;
    }

    /// @brief function to add boite/boulot item
    /// @param contener is the destination DOM
    /// @param table is the name of the table to be filled
    /// @param data is an array which contains details
    addHISTORYSUBENTRY(contener, table, data) {
        //MAIN ROW
        const tr = table.appendChild(contener.createElement("tr"));

        const keys = Object.keys(data);

        // LEFT COLUMN ----------
        // boulotentryXXitem
        const iditem = keys[0];
        const valueitem = data[iditem];
        this.rightcreator.addLcol(contener, tr, [ iditem, valueitem ]);

        // RIGHT COLUMN ----------
        const local = this;

        let rcol = null;
        let table_ = null;
        [
            // N *                boulotentryXXtitle, boulotentryXXcontent
            /*pair[0, 1]*/[       keys[1],            keys[2] ],
            /*pair[0, 1]*/[       keys[3],            keys[4] ]
        ].forEach((pair, idx) => {
            if(null == data[pair[0]]) {
                return;
            }

            // boulotentryXXtitle
            const idtitle = pair[0];
            const valuetitle = data[idtitle];
            // boulotentryXXcontent
            const idcontent = pair[1];
            const valuecontent = data[idcontent];

            if(null == rcol) {
                //this is the first item, there is nothing yet, rcol must be created
                rcol = this.rightcreator.addRcol(contener, tr,  [ idtitle, valuetitle, idcontent ]);
                if(null != valuecontent) {
                    valuecontent.forEach((item, index) => {
                        local.rightcreator.rcolinit(contener, rcol, item, index);
                    });
                }
            }
            else {
                //this is additional item, there is already something in rcol
                table_ = this.rightcreator.fillRcol(contener, rcol[0], [ idtitle, valuetitle, idcontent ]);
                if(null != valuecontent) {
                    valuecontent.forEach((item, index) => {
                        local.rightcreator.rcolappend(contener, table_, item, index);
                    });
                }
            }

        });

    }

}

/*************************************************************************************
 * IMPLEMENTATION: COMMUNICATION WITH SERVER
 *************************************************************************************/

///@brief CV asynchronous loader
export class CLISIDE_CVLOADER extends CLISIDE_LOADER {

    /****************************************
     * CONSTANTS
     ****************************************/
    static CMD() { return "cliside_CVphpgetdata" };

    /****************************************
     * PUBLIC IMPLEMENTATION
     ****************************************/
    /// @brief ctor
    /// @param id contains an identifier used for construction
    constructor(id) {
        super(CLISIDE_CVLOADER.CMD(), id);
    }

}
