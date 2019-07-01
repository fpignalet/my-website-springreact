'use strict';

/*************************************************************************************
 * INCLUDES CODE
 *************************************************************************************/
import {
    CLISIDE_DOM,
    CLISIDE_LOADER
} from "../lib/clientside.js";

import {
    CLISIDE_CVLOADER,
    CLISIDE_CVCREATE
} from "./clientsideCV.js";

import {
    CLISIDE_BLOGLOADER
} from "./clientsideBLOG.js";

/*************************************************************************************
 * INCLUDES DATA
 *************************************************************************************/
import {
    data_INDEXaboutdata,
    data_INDEXcontentdata,
    data_INDEXfiles,
    data_INDEXaboutitems,
    data_INDEXnavitems1,
    data_INDEXnavitems2,
    data_INDEXnewsitems,
    data_INDEXpresitems,
    data_INDEXtechitems,
    data_INDEXtitleitems,
    data_INDEXmenubars,
    data_INDEXtitledata
} from "../../data/INDEXdata.js";

import {
    data_CVmap1
} from "../../data/CVdata.js";

import {
    data_BTECHlasts,
    data_BNEWSlasts,
    data_BNEWSmap,
    data_BTECHmap1
} from "../../data/BLOGdata.js";

/*************************************************************************************
 * IMPLEMENTATION: ENTRY PAGE ITEMS CREATION
 *************************************************************************************/
/// @brief specific DOM utilities for ENTRY page
export class CLISIDE_INDEXCREATE extends CLISIDE_DOM {

    /// ctor
    /// @param id
    constructor(id) {
        super(id);

    }

    /// @brief prepare navigation menu/bar
    /// @param contener is the target DOM
    displayNAVBARS(contener) {
        data_INDEXnavitems1.forEach((item, index) => {
            if("" === item) return;
            contener.getElementById(item).appendChild(contener.createTextNode(data_INDEXmenubars[index]));
        });
        data_INDEXnavitems2.forEach((item, index) => {
            if("" === item) return;
            contener.getElementById(item).appendChild(contener.createTextNode(data_INDEXmenubars[index]));
        });

//        console.log(this.getFuncName() + "OK");
    }

    /// @brief prepare title
    /// @param contener is the target DOM
    displayTITLE(contener) {
        data_INDEXtitleitems.forEach((item, index) => {
            if("" === item) return;
            contener.getElementById(item).appendChild(contener.createTextNode(data_INDEXtitledata[index]));
        });

//        console.log(this.getFuncName() + "OK");
    }

    /// @brief prepare about part
    /// @param contener is the target DOM
    displayABOUT(contener) {
        data_INDEXaboutitems.forEach((item, index) => {
            if("" === item) return;
            contener.getElementById(item[1]).appendChild(contener.createTextNode(data_INDEXaboutdata[index][0]));
            contener.getElementById(item[0]).appendChild(contener.createTextNode(data_INDEXaboutdata[index][1]));
        });

//        console.log(this.getFuncName() + "OK");
    }

    /// @brief creates the presentation preview
    /// @param contener is the destination DOM
    displayPRESENTATION(contener){
        const nameid = data_INDEXpresitems[0];
        contener.getElementById(nameid).appendChild(contener.createTextNode(data_INDEXcontentdata[0][0]));
        const photoid = data_INDEXpresitems[1];
        contener.getElementById(photoid).setAttribute("src", data_INDEXcontentdata[0][1]);

        //----------------------
        this.addbutton(contener, data_INDEXpresitems[4], data_INDEXfiles[0]);
        this.addbutton(contener, data_INDEXpresitems[5], data_INDEXfiles[3]);

    }

    /// @brief creates the BLOG NEWS preview
    /// @param contener is the destination DOM
    previewNEWS(contener){
        const titleid = data_INDEXnewsitems[0];
        contener.getElementById(titleid).appendChild(contener.createTextNode(data_INDEXcontentdata[1][0]));

        const textid = data_INDEXnewsitems[1];
        const it = contener.getElementById(textid);
        const text = data_INDEXcontentdata[1][1];
        it.appendChild(contener.createTextNode(text[0]));
        it.appendChild(contener.createElement("br"));
        it.appendChild(contener.createTextNode(text[1]));

        //----------------------
        this.addbutton(contener, data_INDEXnewsitems[4], data_INDEXfiles[1]);

    }

    /// @brief creates the BLOG TECH preview
    /// @param contener is the destination DOM
    previewTECH(contener){
        const titleid = data_INDEXtechitems[0];
        contener.getElementById(titleid).appendChild(contener.createTextNode(data_INDEXcontentdata[2][0]));

        const textid = data_INDEXtechitems[1];
        const it = contener.getElementById(textid);
        const text = data_INDEXcontentdata[2][1];
        it.appendChild(contener.createTextNode(text[0]));
        it.appendChild(contener.createElement("br"));
        it.appendChild(contener.createTextNode(text[1]));

        //----------------------
        this.addbutton(contener, data_INDEXtechitems[4], data_INDEXfiles[2]);

    }

    /*******************************************
     * Loading completion
     *******************************************/
    /// @brief
    /// @param contener is the destination DOM
    /// @param dom is the source DOM
    /// @param data says which source data shall be fetched
    /// @param idsrc desc...
    /// @param iddst desc...
    updatePRESENTATION(domsrc, datasrc, contndst, idsrc, iddst){
        //SRC side
        const CV = new CLISIDE_CVCREATE();
        CV.addINFO(domsrc, datasrc);
        const itsrc = domsrc.getElementById(idsrc);
        //DST side
        const itdst = contndst.getElementById(iddst);
        for(let content = itdst.firstChild; null != content; content = itdst.firstChild) {
            //target cleanup
            itdst.removeChild(content);
        }

        //target update
        contndst.getElementById(iddst).appendChild(itsrc);

    }

    /// @brief
    /// @param contener is the destination DOM
    /// @param dom is the source DOM
    /// @param data says which source data shall be fetched
    /// @param idsrc desc...
    /// @param iddst desc...
    updateSPRITE (domsrc, datasrc, contndst, idsrc, iddst){
        contndst.getElementById(iddst).setAttribute("src", datasrc);

    }

}

/*************************************************************************************
 * IMPLEMENTATION: COMMUNICATION WITH SERVER
 *************************************************************************************/

/// @brief specific loader for ENTRY page
export class CLISIDE_INDEXLOADER extends CLISIDE_LOADER {

    /****************************************
     * CONSTANTS
     ****************************************/
    static CMD() { return "" /*will use variable target attribute*/ };
    static IDPRES() { return "prescard"; }
    static MODALCAPTION() { return "modalcaption"; }

    /****************************************
     * PUBLIC IMPLEMENTATION
     ****************************************/
    /// @brief ctor
    /// @param creator
    /// @param id
    constructor(creator, id) {
        super(CLISIDE_INDEXLOADER.CMD(), id);

        //Need to use sub loaders instead of making everything directly with this loader:
        //in displayitemsfrom the target property should be fixed with the file,
        //so it's better to use intermediate loaders to be sure that it'll not mess with Http/ajax async behaviour
        this.CVpreloader = new CLISIDE_CVLOADER();
        this.BLOGpreloader = new CLISIDE_BLOGLOADER();
    }

    /*******************************************
     * Loading layer WITH PRELOAD
     *******************************************/
    /// @brief loading presentation data as an extract from CV page
    /// @param contener is the destination DOM
    /// @param creator
    /// @param file
    loadPRESENTATION(contener, creator, file) {
        creator.displayPRESENTATION(contener);

        const local = this;

        //------------------
        // PRELOAD:
        // the goal consists in loading the page which contains the data we need,
        // but it'll be empty, so we need to load the data too.
        // 1st - load remote data
        this.CVpreloader.remotegetbatch(contener, creator, data_CVmap1[1]["data"],null,
            (cr, dataremote) => {
                //------------------
                // POSTLOAD:
                // 2nd - then load reference page
                local.displayitemsfrom(contener, data_INDEXfiles[0], dataremote, data_INDEXpresitems[2], data_INDEXpresitems[3],
                    (dom, datasrc, contener, idsrc, itdst) => {
                        // 3rd - then complete the reference page with the needed card
                        local.localgetfile(dom, file,"contener",CLISIDE_INDEXLOADER.IDPRES(),
                            () => {
                                // 4rd - then fill ref page with data and transfer items to current page
                                cr.updatePRESENTATION(dom, datasrc, contener, idsrc, itdst);
                            }
                        );
                    }
                );
            }
        );
    }

    /// @brief Modal Image Gallery handling
    /// @param contener is the destination DOM
    /// @param creator
    /// @param element
    /// @param file
    /// @param card
    /// @param grid
    /// @param data
    showMODAL(contener, creator, element, file, card, grid, data) {
        const local = this;

        //------------------
        // PRELOAD:
        // 1st - load remote data
        this.BLOGpreloader.remotegetbatch(contener, creator, data,null,
            (cr, dataremote) => {
                //------------------
                // POSTLOAD:
                // 2nd - then load reference page
                local.displayitemsfrom(contener, file, dataremote, null,CLISIDE_INDEXLOADER.MODALCAPTION(),
                    (dom, datasrc, contener, idsrc, itdst) => {
                        // 3rd - then complete the reference page with the needed card
                        local.localgetfile(dom, card,"contener", grid,
                            () => {
                                // 4rd - then fill ref page with data and transfer items to current page
                                cr.displaymodal(dom, datasrc, contener, element, itdst);
                            }
                        );
                    }
                );
            }
        );
    }

    /*******************************************
     * Loading layer
     *******************************************/
    /// @brief loading blog news data as an extract from BLOG page
    /// @param contener is the destination DOM
    /// @param creator desc...
    /// @param file desc...
    loadNEWS(contener, creator, file) {
        creator.previewNEWS(contener);

        const local = this;

        //------------------
        // PRELOAD:
        // the goal consists in loading the page which contains the data we need,
        // but it'll be empty, so we need to load the data too.
        // 1st - load remote data
        this.BLOGpreloader.remotegetbatch(contener, creator,[ data_BNEWSmap[0]["desc"], data_BNEWSmap[1]["desc"] ],null,
            (cr, dataremote) => {
                //------------------
                // POSTLOAD:
                // 2nd - then load reference page
                local.displayitemsfrom(contener, data_INDEXfiles[1],[ dataremote[0][data_BNEWSlasts[0]], dataremote[1][data_BNEWSlasts[1]] ],null,[ data_INDEXnewsitems[2], data_INDEXnewsitems[3] ],
                    (dom, datasrc, contener, idsrc, itdst) => {
                        // 3rd - then complete the reference page with the needed card
                        local.localgetfile(dom, file, "contener", "gridnews",
                            () => {
                                // 4th - then fill ref page with data and transfer items to current page
                                creator.updateSPRITE(dom, datasrc, contener, idsrc, itdst);
                            }
                        );
                    }
                );
            }
        );
    }

    /// @brief loading blog tech data as an extract from BLOG page
    /// @param contener is the destination DOM
    /// @param creator
    /// @param file
    loadTECH(contener, creator, file) {
        creator.previewTECH(contener);

        const local = this;

        //----------------------
        // PRELOAD:
        // the goal consists in loading the page which contains the data we need,
        // but it'll be empty, so we need to load the data too.
        // 1st - load remote data
        this.BLOGpreloader.remotegetbatch(contener, creator,[ data_BTECHmap1[5]["desc"], data_BTECHmap1[0]["desc"] ],null,
            (cr, dataremote) => {
                //------------------
                // POSTLOAD:
                // 2nd - then load reference page
                local.displayitemsfrom(contener, data_INDEXfiles[2],[ dataremote[0][data_BTECHlasts[0]], dataremote[1][data_BTECHlasts[1]] ],null,[ data_INDEXtechitems[2], data_INDEXtechitems[3] ],
                    (dom, datasrc, contener, idsrc, itdst) => {
                        // 3rd - then complete the reference page with the needed card
                        local.localgetfile(dom, file,"contener", "gridtech1",
                            () => {
                                // 4th - then fill ref page with data and transfer items to current page
                                creator.updateSPRITE(dom, datasrc, contener, idsrc, itdst);
                            }
                        );
                    }
                );
            }
        );
    }

}
