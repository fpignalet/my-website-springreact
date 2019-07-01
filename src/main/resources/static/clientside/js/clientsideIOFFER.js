'use strict';

/*************************************************************************************
 * INCLUDES CODE
 *************************************************************************************/
import {
    CLISIDE_PAGE,
    CLISIDE_LOADER,
    cliside_BASEIDENT,
    cliside_disctoggled
} from "./lib/clientside.js";

/*************************************************************************************
 * INCLUDES DATA
 *************************************************************************************/

/*************************************************************************************
 * GLOBAL VARIABLES
 *************************************************************************************/
let cliside_OFFERpage = null;

/*************************************************************************************
 * UTILS
 *************************************************************************************/
export class CLISIDE_IOFFER extends CLISIDE_PAGE {

    /*************************************************************************************
     * CONSTANTS: GRAMMAR
     *************************************************************************************/
    static LOAD() { return "load"; }

    /*************************************************************************************
     * CONSTANTS: ITEMS
     *************************************************************************************/
    static CONTENER() { return "contener"; }

    static IDHEADER() { return "headercard"; }
    static IDGRIDOFFER() { return "gridoffer"; }
    static IDFOOTER() { return "footer"; }
    static IDABOUT() { return "aboutcard"; }

    /*************************************************************************************
     * CONSTANTS: CARDS
     *************************************************************************************/
    static CARDHEADER() { return "/clientside/cards/cardheader.html"; }
    static CARDGRIDOFFER() { return "/clientside/cards/OFFERgrid.html"; }
    static CARDFOOTER() { return "/clientside/cards/cardfooter.html"; }
    static CARDABOUT() { return "/clientside/cards/cardabout.html"; }

    /*************************************************************************************
     * IMPLEM
     *************************************************************************************/
    /// @brief main entry function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pageload(contener, param) {
        cliside_OFFERpage = new CLISIDE_IOFFER(param);
        cliside_OFFERpage.loadtop(contener,
            CLISIDE_IOFFER.CARDHEADER()
        );
        cliside_OFFERpage.loadbody(contener,
            CLISIDE_IOFFER.CARDGRIDOFFER()
        );
        cliside_OFFERpage.loadbottom(contener, [
            CLISIDE_IOFFER.CARDFOOTER(),
            CLISIDE_IOFFER.CARDABOUT()
        ]);
    }

    /// @brief leav function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pageunload(contener, param) {
//        alert("notyetimplemented")
    }

    /// @brief scroll function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pagescroll(contener, param) {
        /*
        cliside_BLOGTECHlazyload(contener);
        CLISIDE_PAGE.pagescroll(contener, param);
        */

    }

    /// @brief print function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pageprint(contener, param) {
//        alert("notyetimplemented")
    }

    /*************************************************************************************
     * INTERNAL
     *************************************************************************************/
    /// ctor
    /// @param id
    constructor(param) {
        super(-1);

        this.loader = new CLISIDE_LOADER(cliside_BASEIDENT + param[CLISIDE_IOFFER.LOAD()]);

    }

    loadtop(contener, file) {
        this.loader.localgetfile(contener, file, CLISIDE_IOFFER.CONTENER(), CLISIDE_IOFFER.IDHEADER(),
            () => {
                contener.getElementById("titlepart").appendChild(contener.createTextNode(
                    "What can I do for you? And how much it costs...")
                )
            }
        );

    }
    /// @brief maiin entry function
    /// @param contener is the target DOM
    /// @param param maybe anything
    loadbody(contener, file) {
        this.loader.localgetfile(contener, file, CLISIDE_IOFFER.CONTENER(), CLISIDE_IOFFER.IDGRIDOFFER(), null);

    }

    loadbottom(contener, file) {
        this.loader.localgetfile(contener, file[0], CLISIDE_IOFFER.CONTENER(), CLISIDE_IOFFER.IDFOOTER());
        this.loader.localgetfile(contener, file[1], CLISIDE_IOFFER.CONTENER(), CLISIDE_IOFFER.IDABOUT());

    }

}
