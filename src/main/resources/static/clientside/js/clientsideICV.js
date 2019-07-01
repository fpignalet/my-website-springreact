'use strict';

/*************************************************************************************
 * INCLUDES CODE
 *************************************************************************************/
import {
    CLISIDE_PAGE,
    CLISIDE_LOADER,
    cliside_disctoggled,
    cliside_BASEIDENT
} from "./lib/clientside.js";

import {
    CLISIDE_CVCREATE,
    CLISIDE_CVLOADER
} from "./impl/clientsideCV.js";

/*************************************************************************************
 * INCLUDES DATA
 *************************************************************************************/
import {
    data_CVmap1,
    data_CVmap2,
    data_CVmap3
} from "../data/CVdata.js";

/*************************************************************************************
 * GLOBAL VARIABLES
 *************************************************************************************/
let cliside_CVpage = null;

/*************************************************************************************
 * IMPLEMENTATION: PAGE UTILS
 *************************************************************************************/
export class CLISIDE_ICV extends CLISIDE_PAGE {

    /*************************************************************************************
     * CONSTANTS: GRAMMAR
     *************************************************************************************/
    static CREATE() { return "create"; }
    static LOAD() { return "load"; }

    static LOADED() { return "loaded"; }
    static DATA() { return "data"; }
    static BOITE() { return "boite"; }
    static BOULOTS() { return "boulots"; }
    static PROGRESS() { return "progress"; }
    static CBK() { return "cbk"; }

    static SIDE() { return "side"; }
    static COLLEFT() { return "left"; }
    static COLRIGHT() { return "right"; }

    /*************************************************************************************
     * CONSTANTS: ITEMS
     *************************************************************************************/
    static CONTENER() { return "contener"; }

    static IDPRES() { return "prescard"; }
    static IDBUTTONS() { return "buttonscard"; }
    static IDEXP() { return "expcard"; }
    static IDSKILLS() { return "skillscard"; }
    static IDLANGS() { return "langscard"; }
    static IDGRIDHIST() { return "gridhistory"; }
    static IDBILDUNG() { return "bildungcard"; }
    static IDHOBBY() { return "hobbycard"; }
    static IDFOOTER() { return "footer"; }

    /*************************************************************************************
     * CONSTANTS: CARDS
     *************************************************************************************/
    static CARDPRES() { return "/clientside/cards/CVcardpres.html"; }
    static CARDBUTTONS() { return "/clientside/cards/CVcardbuttons.html"; }
    static CARDEXP() { return "/clientside/cards/CVcardexp.html"; }
    static CARDSKILLS() { return "/clientside/cards/CVcardskills.html"; }
    static CARDLANGS() { return "/clientside/cards/CVcardlangs.html"; }
    static CARDGRIDHIST() { return "/clientside/cards/CVgridHIST.html"; }
    static CARDBILDUNG() { return "/clientside/cards/CVcardbildung.html"; }
    static CARDHOBBY() { return "/clientside/cards/CVcardhobby.html"; }
    static CARDFOOTER() { return "/clientside/cards/cardfooter.html"; }

    /*************************************************************************************
     * IMPLEMENTATION: PAGE ENTRYPOINTs
     *************************************************************************************/
    /// @brief main entry function
    /// @param contener is the target DOM
    /// @param param may be anything
    /// @returns 1 desc
    static pageload(contener, param) {
        //-----------------------------------------------------------
        window.CVpageprint = CLISIDE_ICV.pageprint;
        window.CVtoggleall = CLISIDE_PAGE.disctoggle;

        //-----------------------------------------------------------
        cliside_CVpage = new CLISIDE_ICV(param);
        cliside_CVpage.loadtop(contener, [
            CLISIDE_ICV.CARDPRES(),
            CLISIDE_ICV.CARDBUTTONS(),
            CLISIDE_ICV.CARDEXP(),
            CLISIDE_ICV.CARDSKILLS(),
            CLISIDE_ICV.CARDLANGS()
        ]);
        cliside_CVpage.loadbody(contener, [
            CLISIDE_ICV.CARDGRIDHIST(),
            CLISIDE_ICV.CARDBILDUNG(),
            CLISIDE_ICV.CARDHOBBY()
        ]);
        cliside_CVpage.loadbottom(contener,
            CLISIDE_ICV.CARDFOOTER(),
        );

        //-----------------------------------------------------------
//        loader.getdatajson([ "cliside_CVexport" ], (text) => {
//        alert(text);
//        });

    }

    static pageunload(contener, param) {
//        alert("notyetimplemented")
    }

    /// @brief scroll function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pagescroll(contener, param) {
        /*
        cliside_CVlazyload(contener);
        */
    }

    /// @brief print function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pageprint(contener, param) {
        try {
            const status = cliside_disctoggled;
            if(false === status) {
                CLISIDE_PAGE.disctoggle(contener);
            }
            window.print();
            if(false === status) {
                CLISIDE_PAGE.disctoggle(contener);
            }

//        console.log(this.getFuncName() + "OK");
        }
        catch (e) {
            console.log(e.toString())
        }
        finally {
            //...
        }
    }

    /*************************************************************************************
     * IMPLEMENTATION: INTERNAL
     *************************************************************************************/
    /// ctor
    /// @param id
    constructor(param) {
        super(-1);

        this.loader = new CLISIDE_LOADER(cliside_BASEIDENT + param[CLISIDE_ICV.LOAD()] + 12);

        this.cr = new CLISIDE_CVCREATE(cliside_BASEIDENT + param[CLISIDE_ICV.CREATE()]);
        this.ld = new CLISIDE_CVLOADER(cliside_BASEIDENT + param[CLISIDE_ICV.LOAD()]);

    }

    loadtop(contener, file) {
        this.loader.localgetfile(contener, file[0], CLISIDE_ICV.CONTENER(), CLISIDE_ICV.IDPRES());
        this.loader.localgetfile(contener, file[1], CLISIDE_ICV.CONTENER(), CLISIDE_ICV.IDBUTTONS());
        this.loader.localgetfile(contener, file[2], CLISIDE_ICV.CONTENER(), CLISIDE_ICV.IDEXP());
        this.loader.localgetfile(contener, file[3], CLISIDE_ICV.CONTENER(), CLISIDE_ICV.IDSKILLS());
        this.loader.localgetfile(contener, file[4], CLISIDE_ICV.CONTENER(), CLISIDE_ICV.IDLANGS());
    }

    /// @brief main entry function
    /// @param contener is the target DOM
    /// @returns 1 desc
    loadbody(contener, file) {
        const local = this;

        this.loader.localgetfile(contener, file[0], CLISIDE_ICV.CONTENER(), CLISIDE_ICV.IDGRIDHIST(),
            () => {
                data_CVmap1.forEach((entry, index) => {
                    local.loadmapitem(contener, entry);
                });
            }
        );
        this.loader.localgetfile(contener, file[1], CLISIDE_ICV.CONTENER(), CLISIDE_ICV.IDBILDUNG(),
            () => {
                data_CVmap2.forEach((entry, index) => {
                    local.loadmapitem(contener, entry);
                });
            }
        );
        this.loader.localgetfile(contener, file[2], CLISIDE_ICV.CONTENER(), CLISIDE_ICV.IDHOBBY(),
            () => {
                data_CVmap3.forEach((entry, index) => {
                    local.loadmapitem(contener, entry);
                });
            }
        );

    }

    loadbottom(contener, file) {
        this.loader.localgetfile(contener, file, CLISIDE_ICV.CONTENER(), CLISIDE_ICV.IDFOOTER());

    }

    loadmapitem(contener, entry){
        if(true === entry[CLISIDE_ICV.LOADED()]){
            return;
        }

        switch(entry[CLISIDE_ICV.SIDE()]) {

            case CLISIDE_ICV.COLLEFT():
                //-----------------------------------------------------
                // parse LEFT side using schema:
                //  entry["data"],
                //  entry["cbk"],
                //-----------------------------------------------------
                this.ld.remotegetbatch(contener, this.cr, entry[CLISIDE_ICV.DATA()], null,
                    (cr, data) => {
                        entry[CLISIDE_ICV.CBK()](contener, cr, data);
                        entry[CLISIDE_ICV.LOADED()] = true;
                    }
                );

                break;

            case CLISIDE_ICV.COLRIGHT():
            default:
                //-----------------------------------------------------
                // parse RIGHT side using schema:
                //  entry["boite"],
                //  entry["boulots"],
                //  entry["progress"],
                //-----------------------------------------------------
                this.ld.remotegetentry(contener, this.cr, entry[CLISIDE_ICV.BOITE()], entry[CLISIDE_ICV.BOULOTS()], entry[CLISIDE_ICV.PROGRESS()],
                    (cr, desc, content) => {
                        cr.fillrightside(contener, desc, content);
                        entry[CLISIDE_ICV.LOADED()] = true;
                    }
                );

                break;

        }
    };

}
