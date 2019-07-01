'use strict';

/*************************************************************************************
 * INCLUDES CODE
 *************************************************************************************/
import {
    CLISIDE_PAGE,
    CLISIDE_LOADER,
    cliside_BASEIDENT
} from "./lib/clientside.js";

import {
    CLISIDE_INDEXCREATE,
    CLISIDE_INDEXLOADER
} from "./impl/clientsideINDEX.js";

/*************************************************************************************
 * INCLUDES DATA
 *************************************************************************************/
import {
    data_INDEXfiles,
    data_INDEXmailitems
} from "../data/INDEXdata.js";

import {
    data_BNEWSmap,
    data_BTECHmap1,
    data_BTECHmap2
} from "../data/BLOGdata.js";

/*************************************************************************************
 * GLOBAL VARIABLES
 *************************************************************************************/
let cliside_INDEXpage = null;

/*************************************************************************************
 * IMPLEMENTATION: PAGE UTILS
 *************************************************************************************/
export class CLISIDE_IINDEX extends CLISIDE_PAGE {

    /*************************************************************************************
     * CONSTANTS: GRAMMAR
     *************************************************************************************/
    static LOAD() { return "load"; }

    static DESC() { return "desc"; }

    /*************************************************************************************
     * CONSTANTS: ITEMS
     *************************************************************************************/
    static CONTENER() { return "contener"; }

    static IDGRIDNEWS() { return "gridnews"; }
    static IDGRIDTECH() { return "gridtech1"; }
    static IDNAVI() { return "navi"; }
    static IDFOOTER() { return "footer"; }
    static IDFEEDBACK() { return "feedbackcard"; }
    static IDMODALUTIL() { return "modalutil"; }

    /*************************************************************************************
     * CONSTANTS: CARDS
     *************************************************************************************/
    static CARDPARALLAX1STRIPE() { return "/clientside/cards/cardparallax1stripe.html"; }
    static CARDPARALLAX1ABOUT() { return "/clientside/cards/cardparallax1about.html"; }
    static CARDPARALLAX2() { return "/clientside/cards/cardparallax2.html"; }
    static CARDPARALLAX3() { return "/clientside/cards/cardparallax3.html"; }
    static CARDNAVI() { return "/clientside/cards/cardnavi.html"; }
    static CARDPRES() { return "/clientside/cards/CVcardpres.html"; }
    static CARDGRIDNEWS() { return "/clientside/cards/BLOGgridNEWS.html"; }
    static CARDGRIDTECH() { return "/clientside/cards/BLOGgridTECH1.html"; }
    static CARDFOOTER() { return "/clientside/cards/cardfooter.html"; }
    static CARDMODAL() { return "/clientside/cards/cardmodal.html"; }
    static CARDFEEDBACK() { return "/clientside/cards/cardfeedback.html"; }

    /*************************************************************************************
     * IMPLEMENTATION: PAGE ENTRYPOINTs
     *************************************************************************************/
    /// @brief fills the page with required data
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pageload(contener, param) {
//        document.cookie = "fpiwebsite";

        //-----------------------------------------------------------
        window.INDEXmodalnews = CLISIDE_IINDEX.modalnews;
        window.INDEXmodaltech = CLISIDE_IINDEX.modaltech;
        window.INDEXpagefbk = CLISIDE_IINDEX.pagefbk;
        window.INDEXnavtoggle = CLISIDE_PAGE.navtoggle;

        //-----------------------------------------------------------
        cliside_INDEXpage = new CLISIDE_IINDEX(param);
        cliside_INDEXpage.loadtop(contener,
            CLISIDE_IINDEX.CARDNAVI()
        );
        cliside_INDEXpage.loadbody(contener, [
            CLISIDE_IINDEX.CARDPRES(),
            CLISIDE_IINDEX.CARDGRIDNEWS(),
            CLISIDE_IINDEX.CARDGRIDTECH()
        ]);
        cliside_INDEXpage.loadbottom(contener, [
            CLISIDE_IINDEX.CARDFOOTER(),
            CLISIDE_IINDEX.CARDMODAL(),
            CLISIDE_IINDEX.CARDFEEDBACK()
        ]);

    }

    /// @brief leaving the INDEX TECH page
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pageunload(contener, param) {
        //...
    }

    /// @brief Change style of navbar on scroll
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pagescroll(contener, param) {
        const navbar = contener.getElementById(param);
        if(null === navbar){
            return;
        }

        if (contener.body.scrollTop > 100 || contener.documentElement.scrollTop > 100) {
            navbar.className = "w3-bar" + " w3-card" + " w3-animate-top" + " w3-white";
        }
        else {
            navbar.className = navbar.className.replace(
                " w3-card w3-animate-top w3-white", ""
            );
        }

//    cliside_INDEXlazyload(contener);
    }

    /// @brief print function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pageprint(contener, param) {
//        alert("notyetimplemented")
    }

    /*************************************************************************************
     * IMPLEMENTATION: PAGE SPECIFIC ENTRYPOINTS
     *************************************************************************************/
    /// @brief wrapper function to diaplay a sprite
    /// @param contener is the target DOM
    /// @param inst is the item which requests the load
    /// @param param selects the data_BNEWSmap item
    static modalnews(contener, inst, param) {
        try {
            cliside_INDEXpage.loader.showMODAL(contener, cliside_INDEXpage.cr, inst, data_INDEXfiles[1], CLISIDE_IINDEX.CARDGRIDNEWS(), CLISIDE_IINDEX.IDGRIDNEWS(), data_BNEWSmap[param][CLISIDE_IINDEX.DESC()]);

        }
        catch (e) {
            console.log(e.toString())
        }
        finally {
            //...
        }
    }

    /// @brief wrapper function to diaplay a sprite
    /// @param contener is the target DOM
    /// @param inst is the item which requests the load
    /// @param index selects the data_BTECHmap item
    static modaltech(contener, inst, param) {
        try {
            cliside_INDEXpage.loader.showMODAL(contener, cliside_INDEXpage.cr, inst, data_INDEXfiles[2], CLISIDE_IINDEX.CARDGRIDTECH(), CLISIDE_IINDEX.IDGRIDTECH(), param[CLISIDE_IINDEX.DESC()]);

        }
        catch (e) {
            console.log(e.toString())
        }
        finally {
            //...
        }
    }

    /// @brief sends a feedback message
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pagefbk(contener, param) {
        try {
            const basename = "cliside_ENTRYphpmail";
            const loader = new CLISIDE_LOADER(basename, cliside_BASEIDENT + param[CLISIDE_IINDEX.LOAD()]);

            const params = [
                basename,
                contener.getElementById(data_INDEXmailitems[0]).value,
                contener.getElementById(data_INDEXmailitems[1]).value,
                contener.getElementById(data_INDEXmailitems[2]).value
            ];
            loader.getdataraw(params,
                (result) => {
//                alert("Mail has been sent");
                    alert("GOT ANSWER: " + result);

                }

            );

        }
        catch (e) {
            console.log(e.toString())
        }
        finally {
            //...
        }
    }

    /*************************************************************************************
     * IMPLEMENTATION: INTERNALS
     *************************************************************************************/
    /// ctor
    /// @param id
    constructor(param) {
        super(-1);

        this.loader = new CLISIDE_INDEXLOADER(cliside_BASEIDENT + param[CLISIDE_IINDEX.LOAD()]);
        this.cr = new CLISIDE_INDEXCREATE(cliside_BASEIDENT + param["create"]);

    }

    loadtop(contener, file) {
        const local = this;

        this.loader.localgetfile(contener, file, CLISIDE_IINDEX.CONTENER(), CLISIDE_IINDEX.IDNAVI(),
            () => {
                local.cr.displayNAVBARS(contener)
            }
        );

        this.cr.displayTITLE(contener);
        this.cr.displayABOUT(contener);

    }

    /// @brief fills the page with required data
    /// @param contener is the target DOM
    loadbody(contener, file) {
        this.loader.loadPRESENTATION(contener, this.cr, file[0]);
        this.loader.loadNEWS(contener, this.cr, file[1]);
        this.loader.loadTECH(contener, this.cr, file[2]);

    }

    loadbottom(contener, file) {
        this.loader.localgetfile(contener, file[0], CLISIDE_IINDEX.CONTENER(), CLISIDE_IINDEX.IDFOOTER());
        this.loader.localgetfile(contener, file[1], CLISIDE_IINDEX.CONTENER(), CLISIDE_IINDEX.IDMODALUTIL());
        this.loader.localgetfile(contener, file[2], CLISIDE_IINDEX.CONTENER(), CLISIDE_IINDEX.IDFEEDBACK());

    }

}
