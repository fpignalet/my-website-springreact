'use strict';

/*************************************************************************************
 * INCLUDES CODE
 *************************************************************************************/
import {
    CLISIDE_BASE,
    CLISIDE_LOADER,
    cliside_BASEIDENT,
    CLISIDE_PAGE
} from "./lib/clientside.js";

import {
    CLISIDE_BNEWSDOM,
    CLISIDE_BLOGLOADER,
    CLISIDE_BTECHLOCAL,
    CLISIDE_BTECHREMOTE
} from "./impl/clientsideBLOG.js";

import Multiple from "../game/js/lib/instances.js";
import Core from "../game/js/impl/core.js";

//import * as angular from "https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js";

/*************************************************************************************
 * INCLUDES DATA
 *************************************************************************************/
import {
    data_BNEWSmap,
    data_BTECHmap1,
    data_BTECHmap2
} from "../data/BLOGdata.js";

/*************************************************************************************
 * GLOBAL VARIABLES
 *************************************************************************************/
let cliside_BLOGNEWSpage = null;

let cliside_BLOGTECHpage = null;

let cliside_BLOGTECHmousecur = null;
let cliside_BLOGTECHmousepos = { x: 0, y: 0 };

/*************************************************************************************
 *************************************************************************************
 * PAGE ENTRYPOINTS
 *************************************************************************************
 *************************************************************************************/

/*************************************************************************************
 * IMPLEMENTATION: NEWS UTILS
 *************************************************************************************/
export class CLISIDE_IBLOGNEWS extends CLISIDE_PAGE {

    /*************************************************************************************
     * CONSTANTS: GRAMMAR
     *************************************************************************************/
    static CREATE() { return "create"; }
    static LOAD() { return "load"; }

    static DATA() { return "data"; }
    static DESC() { return "desc"; }
    static CONTENT() { return "content"; }
    static PROGRESS() { return "progress"; }

    /*************************************************************************************
     * CONSTANTS: ITEMS
     *************************************************************************************/
    static CONTENER() { return "contener"; }

    static IDTITLEPART() { return "titlepart"; }

    static IDHEADER() { return "headercard"; }
    static IDFOOTER() { return "footer"; }
    static IDABOUT() { return "aboutcard"; }

    /*************************************************************************************
     * CONSTANTS: CARDS
     *************************************************************************************/
    static CARDHEADER() { return "/clientside/cards/cardheader.html"; }
    static CARDGRIDNEWS() { return "/clientside/cards/BLOGgridNEWS.html"; }
    static CARDFOOTER() { return "/clientside/cards/cardfooter.html"; }
    static CARDABOUT() { return "/clientside/cards/cardabout.html"; }

    /*************************************************************************************
     * IMPLEMENTATION: NEWS SIDE
     *************************************************************************************/
    /// @brief maiin entry function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pageload(contener, param) {
        cliside_BLOGNEWSpage = new CLISIDE_IBLOGNEWS(param);
        cliside_BLOGNEWSpage.loadtop(contener,
            CLISIDE_IBLOGNEWS.CARDHEADER()
        );
        cliside_BLOGNEWSpage.loadbody(contener,
            CLISIDE_IBLOGNEWS.CARDGRIDNEWS()
        );
        cliside_BLOGNEWSpage.loadbottom(contener, [
            CLISIDE_IBLOGNEWS.CARDFOOTER(),
            CLISIDE_IBLOGNEWS.CARDABOUT()
        ]);

    }

    /// @brief leaving the BLOG TECH page
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pageunload(contener, param) {
        //...
    }

    /// @brief scroll function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pagescroll(contener, param) {
        /*
        cliside_BLOGNEWSlazyload(contener);
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
     * IMPLEMENTATION: INTERNALE
     *************************************************************************************/
    /// ctor
    /// @param id
    constructor(param) {
        super(-1);

        this.loader = new CLISIDE_LOADER(cliside_BASEIDENT + 8);

        this.cr = new CLISIDE_BNEWSDOM(cliside_BASEIDENT + param[CLISIDE_IBLOGNEWS.CREATE()]);
        this.ld = new CLISIDE_BLOGLOADER(cliside_BASEIDENT + param[CLISIDE_IBLOGNEWS.LOAD()]);

    }

    loadtop(contener, file) {
        this.loader.localgetfile(contener, file, CLISIDE_IBLOGNEWS.CONTENER(), CLISIDE_IBLOGNEWS.IDHEADER(),
            () => {
                contener.getElementById(CLISIDE_IBLOGNEWS.IDTITLEPART()).appendChild(contener.createTextNode("News Part"))
            }
        );

    }

    /// @brief loading function
    /// @param contener is the target DOM
    /// @param param maybe anything
    loadbody(contener, file) {
        const local = this;

        /*
        Comment when React
        this.loader.localgetfile(contener, file, CLISIDE_IBLOGNEWS.CONTENER(),"gridnews",
            () => {
                data_BNEWSmap.forEach((entry, index) => {
                    local.loadmapitem(contener, entry);
                });
            }
        );
        */
        /*
        Uncomment when React
        */
        data_BNEWSmap.forEach((entry, index) => {
            local.loadmapitem(contener, entry);
        });

    }

    loadbottom(contener, file) {
        this.loader.localgetfile(contener, file[0], CLISIDE_IBLOGNEWS.CONTENER(), CLISIDE_IBLOGNEWS.IDFOOTER());
        this.loader.localgetfile(contener, file[1], CLISIDE_IBLOGNEWS.CONTENER(), CLISIDE_IBLOGNEWS.IDABOUT());

    }

    loadmapitem(contener, entry){
        if(true === entry["loaded"]){
            return;
        }

        this.ld.remotegetentry(contener, this.cr, entry[CLISIDE_IBLOGNEWS.DESC()], entry[CLISIDE_IBLOGNEWS.CONTENT()], entry[CLISIDE_IBLOGNEWS.PROGRESS()],
            (cr, desc, content) => {
                const loadedweight = cr.fillentry(contener, desc, content);
                if(0 < loadedweight) {
                    entry["loaded"] = true;
                }
            }
        );
    };

}

/*************************************************************************************
 * IMPLEMENTATION: TECH UTILS
 *************************************************************************************/
/*************************************************************************************
 * IMPLEMENTATION: NEWS UTILS
 *************************************************************************************/
export class CLISIDE_IBLOGTECH extends CLISIDE_PAGE {

    /*************************************************************************************
     * CONSTANTS: GRAMMAR
     *************************************************************************************/
    static CREATE() { return "create"; }
    static LOAD() { return "load"; }

    static DATA() { return "data"; }
    static DESC() { return "desc"; }
    static CONTENT() { return "content"; }
    static PROGRESS() { return "progress"; }

    /*************************************************************************************
     * CONSTANTS: ITEMS
     *************************************************************************************/
    static CONTENER() { return "contener"; }

    static IDCODEBOX() { return "codebox1"; }
    static IDTREEDEMO() { return "htreedemo"; }
    static IDTITLEPART() { return "titlepart"; }
    static IDDETAILSNAME() { return "detailsname"; }

    static IDHEADER() { return "headercard"; }
    static IDGRIDTECH1() { return "gridtech1"; }
    static IDGRIDTECH2() { return "gridtech2"; }
    static IDFOOTER() { return "footer"; }
    static IDABOUT() { return "aboutcard"; }

    /*************************************************************************************
     * CONSTANTS: CARDS
     *************************************************************************************/
    static CARDHEADER() { return "./clientside/cards/cardheader.html"; }
    static CARDGRIDTECH1() { return "./clientside/cards/BLOGgridTECH1.html"; }
    static CARDGRIDTECH2() { return "./clientside/cards/BLOGgridTECH2.html"; }
    static CARDFOOTER() { return "./clientside/cards/cardfooter.html"; }
    static CARDABOUT() { return "./clientside/cards/cardabout.html"; }

    static SCRINST() { return "./clientside/game/js/lib/instances.js"; }

    /*************************************************************************************
     * IMPLEMENTATION: TECH SIDE
     *************************************************************************************/
    /// @brief filling the BLOG TECH page
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pageload(contener, param) {
        //-----------------------------------------------------------
        // INITIALISATION LOCAL TESTS:
        // wire specific tests callback
        const local = new CLISIDE_BTECHLOCAL(cliside_BASEIDENT + param[CLISIDE_IBLOGTECH.CREATE()] + 10);
        window.BLOGdomlocal = local; //for testPHP1 & angular(temp)
        (data_BTECHmap1[2][CLISIDE_IBLOGNEWS.DATA()])[2] = () => {
            local.testCODEBOX(contener, CLISIDE_IBLOGTECH.SCRINST(), cliside_BLOGTECHpage.loader, CLISIDE_IBLOGTECH.IDCODEBOX());
        };
        (data_BTECHmap1[3][CLISIDE_IBLOGNEWS.DATA()])[2] = () => {
            local.testCANVAS(contener, "canvas0");
        };
        /*
        (data_BTECHmap2[0][CLISIDE_IBLOGNEWS.DATA()])[2] = () => {
            // OK, it's still hard-coded in html page, I still don't know why when loaded from outside it doesn't functionate
            local.testANGULAR1("testapp", "testctrl");
        };
        */
//      TODO: NOT OK THERE. still dont't know why...
//      local.testANGULAR1("testapp", "testctrl");

        //-----------------------------------------------------------
        // INITIALISATION REMOTE TESTS:
        // wire specific tests callback
        const remote = new CLISIDE_BTECHREMOTE(cliside_BASEIDENT + param[CLISIDE_IBLOGTECH.CREATE()] + 11);
        window.BLOGdomremote = remote; //for testPHP1
        (data_BTECHmap1[0][CLISIDE_IBLOGNEWS.DATA()])[2] = () => {
            remote.testPHP8();
        };
        (data_BTECHmap1[4][CLISIDE_IBLOGNEWS.DATA()])[2] = () => {
            remote.testPHP67(contener, null, CLISIDE_IBLOGTECH.IDTREEDEMO());
        };
        (data_BTECHmap1[6][CLISIDE_IBLOGNEWS.DATA()])[2] = () => {
            contener.getElementById("blog_entry8").innerHTML = local.testDYN(
                // HEADER:
                null,
                'blog_entry8',
                'images/gears-686316_640.jpg',
                'Today\'s sandbox',
                'August 25, 2018',
                'Dynamic test',
                // CONTENU:
                '<ul>\n' +
                '    <li>dynamic blog entry: generating html with javascript\n' +
                '        <ul>\n' +
                '            <li>sub item 1</li>\n' +
                '            <li>sub item 2</li>\n' +
                '        </ul>\n' +
                '    </li>\n' +
                '</ul>\n'
            );
        };
        (data_BTECHmap2[1][CLISIDE_IBLOGNEWS.DATA()])[2] = () => {
            local.testHELLO(contener, "hello_area");
            local.testCOUNT(contener, "count_area");
            remote.testPHP2(contener, null, "bdResult");
            remote.testPHP3(contener, null, "params_area1");
            remote.testPHP4(contener, null, "params_area2");
            remote.testPHP5(contener, null, "params_area3");
        };

        //-----------------------------------------------------
        // PAGE CONSTRUCTION
        cliside_BLOGTECHpage = new CLISIDE_IBLOGTECH(param);

        window.BLOGreactloader = cliside_BLOGTECHpage.loader;
        window.BLOGmouseentercard = CLISIDE_IBLOGTECH.mouseentercard;
        window.BLOGmouseleavecard = CLISIDE_IBLOGTECH.mouseleavecard;

        //-----------------------------------------------------------
        cliside_BLOGTECHpage.loadtop(contener,
            CLISIDE_IBLOGTECH.CARDHEADER()
        );
        cliside_BLOGTECHpage.loadbody(contener, [
            CLISIDE_IBLOGTECH.CARDGRIDTECH1(),
            CLISIDE_IBLOGTECH.CARDGRIDTECH2()
        ]);
        cliside_BLOGTECHpage.loadbottom(contener, [
            CLISIDE_IBLOGTECH.CARDFOOTER(),
            CLISIDE_IBLOGTECH.CARDABOUT()
        ]);

        //-----------------------------------------------------------
        contener.getElementById(CLISIDE_IBLOGTECH.IDDETAILSNAME()).innerHTML = "Tech notes (coming soon)...";

    }

    /// @brief leave function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pageunload(contener, param) {
        const core = Multiple.get(Core.IDENT(), param[CLISIDE_IBLOGTECH.CREATE()] + 10);
        core.isPageOver = true;
    }

    /// @brief scroll function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pagescroll(contener, param) {
        /*
        cliside_BLOGTECHlazyload(contener);
        CLISIDE_PAGE.pagescroll(contener, param);
        */

        /*
        mousepos.x = window.scrollX;
        mousepos.y = window.scrollY;
        mousecurrent = contener.elementFromPoint(50, mousepos.y);
        contener.getElementById(CLISIDE_IBLOGTECH.IDDETAILSNAME()).innerHTML = "CURRENT " + mousecurrent;
        */

    }

    /// @brief print function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pageprint(contener, param) {
//        alert("notyetimplemented")
    }

    /*************************************************************************************
     * IMPLEMENTATION: TECH SIDE SPECIFIC ENTRYPOINTS
     *************************************************************************************/
    /// @brief scroll function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static mouseentercard(contener, param) {
        cliside_BLOGTECHmousecur = param.getAttribute("id");
//    contener.getElementById(CLISIDE_IBLOGTECH.IDDETAILSNAME()).innerHTML = "Implementation details for " + mousecurrent + ": ...";
        contener.getElementById(CLISIDE_IBLOGTECH.IDDETAILSNAME()).innerHTML = "Tech notes (coming soon)...";
    }

    /// @brief scroll function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static mouseleavecard(contener, param) {
        cliside_BLOGTECHmousecur = param.getAttribute("id");
        contener.getElementById(CLISIDE_IBLOGTECH.IDDETAILSNAME()).innerHTML = "Tech notes (coming soon)...";
    }

    /*************************************************************************************
     * IMPLEMENTATION: INTERNALE
     *************************************************************************************/
    /// ctor
    /// @param id
    constructor(param) {
        super(-1);

        this.loader = new CLISIDE_LOADER(cliside_BASEIDENT + param[CLISIDE_IBLOGTECH.LOAD()] + 9);

        this.cr = new CLISIDE_BTECHLOCAL(cliside_BASEIDENT + param[CLISIDE_IBLOGTECH.CREATE()]);
        this.ld = new CLISIDE_BLOGLOADER(cliside_BASEIDENT + param[CLISIDE_IBLOGTECH.LOAD()]);

    }

    loadtop(contener, file) {
        this.loader.localgetfile(contener, file, CLISIDE_IBLOGTECH.CONTENER(), CLISIDE_IBLOGTECH.IDHEADER(),
            () => {
                contener.getElementById(CLISIDE_IBLOGTECH.IDTITLEPART()).appendChild(contener.createTextNode("Technical Part"))
            }
        );

    }

    /// @brief filling the BLOG TECH page
    /// @param contener is the target DOM
    loadbody(contener, file) {
        const local = this;

        /*
        Comment when React
        */
        this.loader.localgetfile(document, file[0], CLISIDE_IBLOGTECH.CONTENER(), CLISIDE_IBLOGTECH.IDGRIDTECH1(),
            () => {
                data_BTECHmap1.forEach((entry, index) => {
                    local.loadmapitem(contener, entry);
                });
            }
        );
        this.loader.localgetfile(document, file[1], CLISIDE_IBLOGTECH.CONTENER(), CLISIDE_IBLOGTECH.IDGRIDTECH2(),
            () => {
                data_BTECHmap2.forEach((entry, index) => {
                    local.loadmapitem(contener, entry);
                });
            }
        );
        /*
        Uncomment when React
        data_BTECHmap1.forEach((entry, index) => {
            local.loadmapitem(contener, entry);
        });
        data_BTECHmap2.forEach((entry, index) => {
            local.loadmapitem(contener, entry);
        });
        */

    }

    loadbottom(contener, file) {
        this.loader.localgetfile(contener, file[0], CLISIDE_IBLOGTECH.CONTENER(), CLISIDE_IBLOGTECH.IDFOOTER());
        this.loader.localgetfile(contener, file[1], CLISIDE_IBLOGTECH.CONTENER(), CLISIDE_IBLOGTECH.IDABOUT());

    }

    loadmapitem(contener, entry){
        if(true === entry["loaded"]){
            return;
        }

        if(null !== entry[CLISIDE_IBLOGNEWS.DESC()]) {
            this.ld.remotegetentry(contener, this.cr, entry[CLISIDE_IBLOGTECH.DESC()], entry[CLISIDE_IBLOGTECH.CONTENT()], entry[CLISIDE_IBLOGTECH.PROGRESS()],
                (cr, desc, content) => {
                    cr.filldesc(contener, desc);
                    entry["loaded"] = true;
                }
            );

        }

        this.loader.localgetfile(contener, entry[CLISIDE_IBLOGTECH.DATA()][0], CLISIDE_IBLOGTECH.CONTENER(), entry[CLISIDE_IBLOGTECH.DATA()][1], entry[CLISIDE_IBLOGTECH.DATA()][2]);
    };

}
