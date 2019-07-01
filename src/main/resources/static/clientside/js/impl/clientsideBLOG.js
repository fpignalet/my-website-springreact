'use strict';

/*************************************************************************************
 * INCLUDES CODE
 *************************************************************************************/
import {
    CLISIDE_BASE,
    CLISIDE_DOM,
    CLISIDE_LOADER
} from "../lib/clientside.js";

//import * as angular from "https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js";

//game embedding:
import {
    app_settings,
    app_guimap,
    app_images
} from "../../game/js/embset.js";
import execute from "../../game/js/lib/execute.js";
import Multiple from "../../game/js/lib/instances.js";
import Core from "../../game/js/impl/core.js";

/*************************************************************************************
 * INCLUDES DATA
 *************************************************************************************/

/*************************************************************************************
 *************************************************************************************
 * BOTH SIDES
 *************************************************************************************
 *************************************************************************************/

/*************************************************************************************
 * IMPLEMENTATION: BLOG DOM handling
 *************************************************************************************/

/// @brief specific DOM utilities for BLOG pages
export class CLISIDE_BLOGDOM extends CLISIDE_DOM {

    /// ctor
    /// @param id contains an identifier used for construction
    constructor(id) {
        super(id);
    }

    /// @brief fills a blog entry descriptor (with title and date)
    /// @param contener is the target DOM
    /// @param data is a BLOGdata.js::data_BLOGdescXX formatted as follow
    ///     "blog_entryXXPHOTO": "...",
    ///     "blog_entryXXTITLE": "...",
    ///     "blog_entryXXDATE": [ "..." ]
    filldesc(contener, data) {
        let key;

        //-------------------
        //"blog_entryXXPHOTO"
        key = Object.keys(data)[0];
        this.updateimage(contener, key, data[key]);
        //-------------------
        //"blog_entryXXTITLE"
        key = Object.keys(data)[1];
        this.addtext(contener, key, data[key], true);
        //-------------------
        //"blog_entryXXDATE"
        key = Object.keys(data)[2];
        const date = contener.getElementById(key);
        const span = date.appendChild(contener.createElement("span"));
        span.setAttribute("class", "w3-opacity");
        data[key].forEach((item, index) => {
            span.appendChild(contener.createTextNode(item));
        });
    }

}

/*************************************************************************************
 * IMPLEMENTATION: COMMUNICATION WITH SERVER
 *************************************************************************************/

/// @brief specific loading utilities for BLOG pages
export class CLISIDE_BLOGLOADER extends CLISIDE_LOADER {

    /****************************************
     * CONSTANTS
     ****************************************/
    static CMD() { return "cliside_BLOGphpgetdata" };

    /****************************************
     * PUBLIC IMPLEMENTATION
     ****************************************/
    /// @brief ctor
    /// @param id contains an identifier used for construction
    constructor(id) {
        super(CLISIDE_BLOGLOADER.CMD(), id);
    }

}

/*************************************************************************************
 *************************************************************************************
 * NEWS SIDE
 *************************************************************************************
 *************************************************************************************/

/// @brief specific DOM utilities for BLOG NEWS pages
export class CLISIDE_BNEWSDOM extends CLISIDE_BLOGDOM {

    /// ctor
    /// @param id contains an identifier used for construction
    constructor(id) {
        super(id);
    }

    /// @brief
    /// @param contener is the target DOM
    /// @param desc contains the entry descriptor
    /// @param content contains the entry details
    fillentry(contener, desc, content) {
        const local = this;

        let loadedweight = 0;
        local.filldesc(contener, desc);
        Object.keys(content).forEach((key, _index) => {
            const loaded = local.fillcontent(contener, content[key]);
            if(true === loaded){
                loadedweight = loadedweight + 1;
            }
        });

//      console.log(lloader.getFuncName() + "OK");
        return loadedweight;
    }

    /// @brief fills a blog entry content (with entry data)
    /// @param contener is the target DOM
    /// @param data is a BLOGdata.js::data_BLOGcontentXX formatted as follow
    ///     "blog_entryXXCONTENT": [ "fieldtype", [ "..." ] ]
    fillcontent(contener, data) {
        //---------------------------------
        const keys = Object.keys(data);

        //blog_entryXXCONTENT
        const id = keys[0];
        const target = contener.getElementById(id);
        /*
        if (contener.body.scrollTop + window.innerHeight < target.offset().top) {
            return false;
        }
        */
        if(false === CLISIDE_BASE.inviewport(target)) {
            console.log(id + " NOT VISIBLE\n")
//            return false;
        }
        else {
            console.log(id + "  -- VISIBLE --\n")
        }

        let lastLI = null;
        let lastA = null;
        data[id].forEach((item, index) => {
            //parse each
            // item[0],     item[1]
            // "fieldtype", [ "..." ]

            const fieldtype = item[0];
            const it = target.appendChild(contener.createElement(fieldtype));

            item[1].forEach((d, i) => {
                // triggin' on fieldtype: let's create a list
                if("ul" === fieldtype) {
                    if(true === Array.isArray(d)) {
                        this.addhref(contener, it, d[0], d[1])
                    }
                    else {
                        const li = it.appendChild(contener.createElement("li"));
                        li.appendChild(contener.createTextNode(d));
                        lastLI = li;
                    }
                }
                // triggin' on fieldtype: let's add a link in the list (lastLI)
                else if("a" === fieldtype) {
                    if(null == lastA) {
//                      var br = lastLI.appendChild(contener.createElement("br"));
                        const a = this.addhref(contener, lastLI, d, null);
                        lastA = a;
                    }
                    else {
                        lastA.innerHTML = d + "<br>";
                        lastA = null;
                    }
                }
                // triggin' on fieldtype: simple cases
                else {
                    if(true === Array.isArray(d)) {
                        this.addhref(contener, it, d[0], d[1])
                    }
                    else {
                        it.appendChild(contener.createTextNode(d));
                    }
                }
            })

        });

        return true;
    }

}

/*************************************************************************************
 *************************************************************************************
 * TECH SIDE
 *************************************************************************************
 *************************************************************************************/

/*************************************************************************************
 * TECH IMPLEMENTATION: LOCAL TESTS
 *************************************************************************************/

/// @brief specific DOM utilities for BLOG TECH page
export class CLISIDE_BTECHLOCAL extends CLISIDE_BLOGDOM {

    /// @brief ctor
    /// @param id ...
    constructor(id) {
        super(id);
        this.count = 0;
    }

    /// @brief SayHello() prints a message in the document's "hello" area
    /// @param contener is the target DOM
    /// @param fieldid is the field which receives the result
    /// @returns 1 desc...
    testHELLO(contener, fieldid) {
        const message = "Hello, World";
        this.updatefield(contener, fieldid, message);
//        console.log(this.getFuncName() + "OK");
    }

    /// @brief Put the current count on the page
    /// @param contener is the target DOM
    /// @param fieldid is the field which receives the result
    /// @returns 1 desc...
    testCOUNT(contener, fieldid) {
        // Put current time in the "count" area of the web page
        const message = "The count is: " + this.count++;
        this.updatefield(contener, fieldid, message);

        // Schedule next call to DisplayCount
        const local = this;
        setTimeout(function() {
            local.testCOUNT(contener, fieldid);
        }, 500);

//        console.log(this.getFuncName() + "OK");
    }

    /// @brief creates a blog entry only with code
    /// @param entryname desc...
    /// @param photo desc...
    /// @param title desc...
    /// @param desc desc...
    /// @param date desc...
    /// @param content desc...
    /// @returns 1 desc...
    testDYN(contener, entryname, photo, title, desc, date, content){
        return [

            '    <img id=\"' + entryname + 'PHOTO' + '\" src=\"' + photo + '\" alt=\"img\" style=\"width:80%\">\n' +
            '    <div class=\"w3-container\">\n' +
            '        <h3 id=\"' + entryname + 'TITLE' + '\">\n' +
            '           <b>' + title + '</b>\n' +
            '        </h3>\n' +
            '        <h5 id=\"' + entryname + 'DATE' + '\">\n' +
            '           <span class=\"w3-opacity\">' + date + '</span>\n' +
            desc +
            '        </h5>\n' +
            '    </div>\n' +

            '    <div id=\"' + entryname + 'CONTENT' + '\" class=\"w3-container\">\n' +
            '        <p>' + content + '</p>\n' +
            '    </div>\n'

        ].join('\n');

//        console.log(this.getFuncName() + "OK");
    }

    /// @brief add angularjs to tech tests
    /// @param name desc...
    /// @param ctrl desc...
    /// @param ifirstName desc...
    /// @param ilastName desc...
    /// @param products desc...
    testANGULAR1(contener, id, ifirstName, ilastName, products) {
        const appname = contener.getElementById("angutest1").getAttribute("ng-app");
        const ctrlname = contener.getElementById("angutest1").getAttribute("ng-controller");

        const testapp = angular.module(appname, []);
        testapp.controller(ctrlname, function($scope) {

            // -------------------------------------
            $scope.ifirstName= ifirstName;
            $scope.ilastName= ilastName;
            $scope.products = products;

            // -------------------------------------
            $scope.dynlistadditem = function () {
                $scope.errortext = "";

                if (null === $scope.addMe) {
                    $scope.errortext = "Please enter something";
                    return;
                }

                if ($scope.products.indexOf($scope.addMe) !== -1) {
                    $scope.errortext = "The item is already in the list";
                    return;
                }

                $scope.products.push($scope.addMe);
            };

            // -------------------------------------
            $scope.dynlistremoveitem = function (index) {
                $scope.errortext = "";
                $scope.products.splice(index, 1);
            };

            // -------------------------------------
            $scope.onmouseover = function(event) {
                $scope.x = event.clientX;
                $scope.y = event.clientY;
            };

        });

//        console.log(this.getFuncName() + "OK");
    }

    /// @brief add angularjs to tech tests
    /// @param name desc...
    /// @param ctrl desc...
    /// @param ifirstName desc...
    /// @param ilastName desc...
    /// @param products desc...
    testANGULAR2(contener, id) {
        function onmenu1() {
            return "<h1>Choosed: Menu 1</h1><p>result = 75%.</p>"
        }
        function onmenu2() {
            return "<h1>Choosed: Menu 2</h1><p>result = 95%.</p>"
        }
        function ondefault() {
            return "<h1>Choosed: Nothing</h1><p>Nothing has been selected</p>"
        }

        const appname = contener.getElementById(id).getAttribute("ng-app");
        const ctrlname = contener.getElementById(id).getAttribute("ng-controller");

        const otherapp = angular.module(appname, [ 'ngRoute' ]);
        otherapp.config(function($routeProvider) {

            $routeProvider
                .when("/Menu1", {
                    template : onmenu1(),
                    controller: ctrlname
                })
                .when("/Menu2", {
                    template : onmenu2(),
                    controller: ctrlname
                })
                .otherwise({
                    template : ondefault(),
                    controller: ctrlname
                });
        });
        otherapp.controller(ctrlname, function($scope) {
            console.log(CLISIDE_DOM.getFuncName() + "$scope = " + $scope.toString());
        });

        angular.bootstrap(contener.getElementById(id), ['otherapp']);

//        console.log(this.getFuncName() + "OK");
    }

    /// @brief
    /// @param canid
    testCANVAS(contener, canid) {
        const core = new Core(app_settings, app_guimap, canid);
        window.onunload = function() { cliside_BLOGTECHpageunload(contener, core.index) };
        execute(app_images, Multiple.get(Core.IDENT(), core.index));
    }

    /// @brief
    /// @param loader
    /// @param boxid
    testCODEBOX(contener, target, loader, boxid) {
        loader.target = target;
        loader.getdataraw(null, (result) => {
            const it = contener.getElementById(boxid);
            it.appendChild(contener.createTextNode(result));
        });
    }

    /// @brief code test:
    static testANY(contener, route) { }

}

/*************************************************************************************
 * TECH IMPLEMENTATION: TESTS WITH SERVER
 *************************************************************************************/

/// @brief specific loading utilities for BLOG TECH page
export class CLISIDE_BTECHREMOTE extends CLISIDE_LOADER {

    /****************************************
     * CONSTANTS
     ****************************************/
    static CMD() { return "cliside_BLOGphptest" };

    static WSURL() { return 'ws://localhost:1414'; }
    static WSPCOL() { return ['soap', 'xmpp']; }
    static WSSENDSTR() { return "TESTTOTOTESTTOTO"; }
    static WSRECVSTR() { return 'OKOKOKOK'; }

    /****************************************
     * PUBLIC IMPLEMENTATION
     ****************************************/
    /// @brief ctor
    /// @param id contains an identifier used for construction
    constructor(id) {
        super(CLISIDE_BTECHREMOTE.CMD(), id);

        this.islinux = false;
        this.wsocket = null;
    }

    //-----------------------------------------------
    // DIRECT FIELD UPDATE
    //-----------------------------------------------
    /// @brief calls serverside with cliside_BLOGphptest1 selector then updates txtHint html item
    /// @param contener is the target DOM
    /// @param data contains the parameter waited by cliside_BLOGphptest1 selector
    /// @param fieldid is the field which receives the result
    testPHP1(contener, data, fieldid) {
        const params = [this.cmdselect + 1, data];
        if (null == params[0]) {
            params[0] = "";
        }

        const local = this;
        this.getdataraw(params, (result) => {
            local.updatefield(contener, fieldid, result);
//            console.log(local.getFuncName() + "OK");
        });
    }

    /// @brief calls serverside with cliside_BLOGphptest2 selector then updates bdResult html item
    /// @param contener is the target DOM
    /// @param data contains the parameter waited by cliside_BLOGphptest2 selector
    /// @param fieldid is the field which receives the result
    testPHP2(contener, data, fieldid) {
        const params = [this.cmdselect + 2, ""];

        const local = this;
        this.getdataraw(params, (result) => {
            local.updatefield(contener, fieldid, result);
//            console.log(local.getFuncName() + "OK");
        });
    }

    /// @brief calls serverside with cliside_BLOGphptest5 selector then updates params_area3 html item
    /// @param contener is the target DOM
    /// @param data contains the parameter waited by cliside_BLOGphptest5 selector
    /// @param fieldid is the field which receives the result
    testPHP5(contener, data, fieldid) {
        const params = [this.cmdselect + 5, ""];

        const local = this;
        this.getdataraw(params, (result) => {
            local.updatefield(contener, fieldid, result);
//            console.log(local.getFuncName() + "OK");
        });
    }

    /// @brief prepraring push test
    /// TODO: need a form
    testPHP8() {
        const params = [this.cmdselect + 8, ""];

        const local = this;
        this.getdataraw(params, (result) => {
            if(null === local.wsocket) {
                local.createwsocket();
            }

            local.wsocket.send(CLISIDE_BTECHREMOTE.WSSENDSTR()); //sending message to server.
        });
    }

    //-----------------------------------------------
    // JSON FIELD UPDATE
    //-----------------------------------------------
    /// @brief calls serverside with cliside_BLOGphptest3 selector then updates params_area1 html item
    /// @param contener is the target DOM
    /// @param data contains the parameter waited by cliside_BLOGphptest3 selector
    /// @param fieldid is the field which receives the result
    testPHP3(contener, data, fieldid) {
        const params = [this.cmdselect + 3, ""];

        const local = this;
        this.getdatajson(params, (result) => {
            local.updatefield(contener, fieldid, result.object_id + "," + result.object_title);
//            console.log(local.getFuncName() + "OK");
        });
    }

    /// @brief calls serverside with cliside_BLOGphptest4 selector then updates params_area2 html item
    /// @param contener is the target DOM
    /// @param data contains the parameter waited by cliside_BLOGphptest4 selector
    /// @param fieldid is the field which receives the result
    testPHP4(contener, data, fieldid) {
        const adata = [2, "JScript_TestObject2"];
        const params = [this.cmdselect + 4, JSON.stringify(adata)];

        const local = this;
        this.getdatajson(params, (result) => {
            local.updatefield(contener, fieldid, result.object_id + "," + result.object_title);
//            console.log(local.getFuncName() + "OK");
        });
    }

    /// @brief calls serverside with cliside_BLOGphptest4 selector then updates ...
    /// @param contener is the target DOM
    /// @param data contains the parameter waited by cliside_BLOGphptest4 selector
    /// @param fieldid is the field which receives the result
    testPHP67(contener, data, fieldid) {
        const params = [this.cmdselect + 7, ""];

        const local = this;
        this.getdataraw(params, (result) => {
            local.islinux = /Linux/.test(result);

            const params = [local.cmdselect + 6, ""];

            local.getdatajson(params, (result) => {
                local.filltree(contener, fieldid, result, local.islinux);
//            console.log(local.getFuncName() + "OK");
            });
        });
    }

    //-----------------------------------------------
    // NON VISIBLE IMPLEMENTATION
    //-----------------------------------------------
    /// @brief
//    @clientside_decorator()
    createwsocket() {
        const local = this;

        this.wsocket = new WebSocket(CLISIDE_BTECHREMOTE.WSURL(), CLISIDE_BTECHREMOTE.WSPCOL());
//        this.wsocket = new WebSocket('ws://localhost', ['echo-protocol']);

        // connection open event
        this.wsocket.onopen = function () {
            console.log('WebSocket Opened ');
            local.wsocket.send(CLISIDE_BTECHREMOTE.WSSENDSTR()); //sending message to server.
        };

        // update received from server event
        this.wsocket.onmessage = function (e) {
            if(CLISIDE_BTECHREMOTE.WSRECVSTR() === e.data) {
                console.log('WebSocket Server answered: ' + e.data);
            }
            else {
                console.log('WebSocket Server didn\'t answered properly...');
            }
        };

        // error event
        this.wsocket.onerror = function (error) {
            console.log('WebSocket Error ' + error);
        };

    }
}
