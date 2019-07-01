'use strict';

//-----------------------------------------------------------------------------------------------------------
/// @brief maps are there to fetch remote data. see serverside/data/CVdata.js
export const data_CVmap1 = [
    //LEFT SIDE ---------------------------------------
    {
        loaded: false,
        side: "left",
        data: "data_CVtitle",
        desc: null,
        content: null,
        boite: null,
        boulots: null,
        progress: null,
        // for left side "cbk" item must provide a CLISIDE_CVCREATE function
        cbk: (contener, cr, detail) => {
            cr.addTITLE(contener, detail);
        }
    },
    {
        loaded: false,
        side: "left",
        data: "data_CVinfo",
        desc: null,
        content: null,
        boite: null,
        boulots: null,
        progress: null,
        // for left side "cbk" item must provide a CLISIDE_CVCREATE function
        cbk: (contener, cr, detail) => {
            cr.addINFO(contener, detail);
        }
    },
    {
        loaded: false,
        side: "left",
        data: "data_CVexperience",
        desc: null,
        content: null,
        boite: null,
        boulots: null,
        progress: null,
        // for left side "cbk" item must provide a CLISIDE_CVCREATE function
        cbk: (contener, cr, detail) => {
            cr.addEXPERIENCE(contener, detail);
        }
    },
    {
        loaded: false,
        side: "left",
        data: ["data_CVskillshead", "data_CVskillsentries"],
        desc: null,
        content: null,
        boite: null,
        boulots: null,
        progress: null,
        // for left side "cbk" item must provide a CLISIDE_CVCREATE function
        cbk: (contener, cr, detail) => {
            cr.addDETAILS(contener, detail);
        }
    },
    {
        loaded: false,
        side: "left",
        data: ["data_CVlanghead", "data_CVlangentries"],
        desc: null,
        content: null,
        boite: null,
        boulots: null,
        progress: null,
        // for left side "cbk" item must provide a CLISIDE_CVCREATE function
        cbk: (contener, cr, detail) => {
            cr.addDETAILS(contener, detail);
        }
    },
    //RIGHT SIDE ---------------------------------------
    {
        loaded: false,
        side: "right",
        data: null,
        desc: null,
        content: null,
        boite: "data_CVboulot0",
        boulots: ["data_CVboulot01"],
        progress: ["boulotentry0wait", "boulotentry0progress", "boulotentry0bar"],
        // for left side callback function is implemented in CLISIDE_CVCREATE
        cbk: null
    },
    {
        loaded: false,
        side: "right",
        data: null,
        desc: null,
        content: null,
        boite: "data_CVboulot1",
        boulots: ["data_CVboulot11"],
        progress: ["boulotentry1wait", "boulotentry1progress", "boulotentry1bar"],
        // for left side callback function is implemented in CLISIDE_CVCREATE
        cbk: null
    },
    {
        loaded: false,
        side: "right",
        data: null,
        desc: null,
        content: null,
        boite: "data_CVboulot2",
        boulots: ["data_CVboulot21", "data_CVboulot22", "data_CVboulot23", "data_CVboulot24", "data_CVboulot25", "data_CVboulot26"],
        progress: ["boulotentry2wait", "boulotentry2progress", "boulotentry2bar"],
        // for left side callback function is implemented in CLISIDE_CVCREATE
        cbk: null
    },
    {
        loaded: false,
        side: "right",
        data: null,
        desc: null,
        content: null,
        boite: "data_CVboulot3",
        boulots: ["data_CVboulot31", "data_CVboulot32"],
        progress: ["boulotentry3wait", "boulotentry3progress", "boulotentry3bar"],
        // for left side callback function is implemented in CLISIDE_CVCREATE
        cbk: null
    },
    {
        loaded: false,
        side: "right",
        data: null,
        desc: null,
        content: null,
        boite: "data_CVboulot4",
        boulots: ["data_CVboulot41"],
        progress: ["boulotentry4wait", "boulotentry4progress", "boulotentry4bar"],
        // for left side callback function is implemented in CLISIDE_CVCREATE
        cbk: null
    },
    {
        loaded: false,
        side: "right",
        data: null,
        desc: null,
        content: null,
        boite: "data_CVboulot5",
        boulots: ["data_CVboulot51", "data_CVboulot52", "data_CVboulot53"],
        progress: ["boulotentry5wait", "boulotentry5progress", "boulotentry5bar"],
        // for left side callback function is implemented in CLISIDE_CVCREATE
        cbk: null
    },
    {
        loaded: false,
        side: "right",
        data: null,
        desc: null,
        content: null,
        boite: "data_CVboulot6",
        boulots: ["data_CVboulot61", "data_CVboulot62"],
        progress: ["boulotentry6wait", "boulotentry6progress", "boulotentry6bar"],
        // for left side callback function is implemented in CLISIDE_CVCREATE
        cbk: null
    },
    {
        loaded: false,
        side: "right",
        data: null,
        desc: null,
        content: null,
        boite: "data_CVboulot7",
        boulots: ["data_CVboulot71"],
        progress: ["boulotentry7wait", "boulotentry7progress", "boulotentry7bar"],
        // for left side callback function is implemented in CLISIDE_CVCREATE
        cbk: null
    }
];

/// @brief maps are there to fetch remote data. see serverside/data/CVdata.js
export const data_CVmap2 = [
    //RIGHT SIDE ---------------------------------------
    {
        loaded: false,
        side: "right",
        data: null,
        desc: null,
        content: null,
        boite: "data_CVbildung1",
        boulots: ["data_CVbildung11", "data_CVbildung12", "data_CVbildung13"],
        progress: ["edu_f1wait", "edu_f1progress", "edu_f1bar"],
        // for left side callback function is implemented in CLISIDE_CVCREATE
        cbk: null
    }
];

/// @brief maps are there to fetch remote data. see serverside/data/CVdata.js
export const data_CVmap3 = [
    //RIGHT SIDE ---------------------------------------
    {
        loaded: false,
        side: "right",
        data: null,
        desc: null,
        content: null,
        boite: "data_CVhobby1",
        boulots: ["data_CVhobby11", "data_CVhobby12", "data_CVhobby13"],
        progress: ["loisirs_f1wait", "loisirs_f1progress", "loisirs_f1bar"],
        // for left side callback function is implemented in CLISIDE_CVCREATE
        cbk: null
    }
];
