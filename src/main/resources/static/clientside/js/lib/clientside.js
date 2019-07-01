'use strict';

/*************************************************************************************
 * INCLUDES CODE
 *************************************************************************************/
import Multiple from "../../game/js/lib/instances.js";

/*************************************************************************************
 * GLOBALS
 *************************************************************************************/
export const cliside_BASEIDENT = "CLISIDEBASE";

export let cliside_disctoggled = false;

/*************************************************************************************
 * IMPLEMENTATION: BASE CLASS
 *************************************************************************************/
export class CLISIDE_BASE extends Multiple {

    static FIREFOX() { return "Firefox"; }
    static REACTENTRY() { return "reactentry"; }
    static MODALDIV() { return "modaldiv"; }
    static MODALIMG() { return "modalimg"; }

    /// ctor
    /// @param id
    constructor(id) {
        // from stackoverlow:
        // https://stackoverflow.com/questions/29480569/does-ecmascript-6-have-a-convention-for-abstract-classes
        if (new.target === CLISIDE_BASE) {
            throw new TypeError("Cannot construct CLISIDE_BASE instance directly");
        }

        super(id);

        this.decotest = false;
    }

    /// @brief ...
    static getFuncName() {
        if(true === CLISIDE_BASE.checkbrowser(CLISIDE_BASE.FIREFOX())) {
            return "";
        }

        return (new Error()).stack.match(/at (\S+)/g)[1].slice(3);
    }

    /// @brief ...
    static isLinux() {
        return /Linux/.test(window.navigator.platform);
    }

    /// @brief ...
    /// @param select ...
    static checkbrowser(select) {

        if(-1 !== navigator.userAgent.indexOf(select) ) {
            return true;
        }

        //---------------
        // special case
        else if("Opera" === select) {
            if(-1 !== navigator.userAgent.indexOf('OPR') ) {
                return true;
            }
        }

        //---------------
        // special case
        else if("MSIE" === select) {
            if( false !== document.documentMode ) {
                return true;
            }
        }

        return false;
    }

    /// @brief ...
    /// @param select ...
    static inviewport(elem) {
        var bounding = elem.getBoundingClientRect();
        return (
            bounding.top >= 0 &&
            bounding.left >= 0 &&
            bounding.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&
            bounding.right <= (window.innerWidth || document.documentElement.clientWidth)
        );
    };

}

/*************************************************************************************
 * IMPLEMENTATION: BASIC PAGE CREATION UTILITIES
 *************************************************************************************/
/// @brief class to make items adding/handling easy
export class CLISIDE_DOM extends CLISIDE_BASE {

    /// ctor
    /// @param id ...
    constructor(id) {
        super(id);

        this.progressbars = {};
        this.progressvals = {};
        this.progressitvs = {};

        this.progressincr = 20;

    }

    //------------------------------------------------------------------
    // SINGLE ITEMS THROUGH ID
    //------------------------------------------------------------------
    /// @brief html single element update
    /// @param contener is the target DOM
    /// @param id is the ID attribute of the element to be updated
    /// @param name contains the value to be used
    updatefield(contener, id, str) {
        contener.getElementById(id).innerHTML = str;
    }

    /// @brief html single element update
    /// @param contener is the target DOM
    /// @param id is the ID attribute of the element to be updated
    /// @param name contains the value to be used
    updateimage(contener, id, str) {
        contener.getElementById(id).setAttribute("src", str);
    }

    /// @brief add text inside the "id" element
    /// @param contener is the target DOM
    /// @param id is the ID attribute of the element which receives text
    /// @param value contains the text to add
    addtext(contener, id, value, bold=false) {
        let it = contener.getElementById(id);
        if(true == bold) {
            it = it.appendChild(contener.createElement("b"));
        }
        it.appendChild(contener.createTextNode(value));
    }

    /// @brief creates a button described as [ id, name ]
    /// @param contener is the target DOM
    /// @param presbutton describes the button with [ id, name ]
    /// @param file tells which page should be opened
    addbutton(contener, presbutton, file) {
        let it;

        const id = presbutton[0];

        it = contener.getElementById(id);
        it.setAttribute("formaction", file);

        const name = presbutton[1];
        it = contener.getElementById(id);
        it.appendChild(contener.createTextNode(name));
    }

    /// @brief add photo inside the "id" element
    /// @param contener is the target DOM
    /// @param id is the ID attribute of the element which receives text
    /// @param value contains the path to the photo to add
    addimage(contener, id, value) {
        const it = contener.getElementById(id);
        it.setAttribute("src", value);
    }

    //------------------------------------------------------------------
    // SINGLE ITEMS THROUGH INSTANCE
    //------------------------------------------------------------------
    /// @brief add href element inside the it element
    /// @param contener is the target DOM
    /// @param it is the element which receives the "a" contener
    /// @param attr contains the value of the href attribute
    /// @param val contains the text of the "a" contener
    addhref(contener, it, attr, val) {
        const a = it.appendChild(contener.createElement("a"));
        a.setAttribute("href", attr);
        if(null == val) {
            return a;
        }

        a.innerHTML = val + "<br>";
        return a;
    }

    /// @brief add href element inside the it element
    /// @param contener is the target DOM
    /// @param it is the element which receives the list item
    /// @param text contains the text of the list item
    addlistitem(contener, it, text) {
        const li = it.appendChild(contener.createElement("li"));
        li.appendChild(contener.createTextNode(text));
    }

    //------------------------------------------------------------------
    // TABLE ITEMS THROUGH INSTANCE
    //------------------------------------------------------------------
    /// @brief add sub elements inside a tr element
    /// @param contener is the target DOM
    /// @param tr is the current contener element
    /// @param data contains sub-item data. It can be an array.
    /// @param bold can be true when it is a left column "title"
    /// @param bold can be false when it is a right column "content"
    filltr(contener, tr, data, bold) {
        if(true === Array.isArray(data)) {
            this.addtrsubs(contener, tr, data);
        }
        //-----------------------------------
        else {
            let it = null;
            if(true === bold) {
                it = tr.appendChild(contener.createElement("b"));
            }
            else {
                it = tr;
            }

            it.appendChild(contener.createTextNode(data));
        }

    }

    /// @brief filltr related sub_function which allows recursion
    /// @param contener is the target DOM
    /// @param tr is the current contener element
    /// @param data contains sub-item data. It can be an array.
    addtrsubs(contener, tr, data) {
        const ul = tr.appendChild(contener.createElement("ul"));

        const inst = this;
        data.forEach((item, index) => {
            if(true === Array.isArray(item)) {
                if(true === item[0].startsWith("http")) {
                    const li = ul.appendChild(contener.createElement("li"));
                    inst.addhref(contener, li, item[0], item[1]);
                }
                else {
                    inst.addtrsubs(contener, tr, item);
                }
            }
            //-----------------------------------
            else {
                const li = ul.appendChild(contener.createElement("li"));
                li.appendChild(contener.createTextNode(item));

            }

        });

        return ul;
    }

    //------------------------------------------------------------------
    // TREE ITEMS(THROUGH INSTANCE)
    //------------------------------------------------------------------
    /// @brief html single element update. start hierarchical tree
    /// @param contener is the target DOM
    /// @param id is the ID attribute of the element to be updated
    /// @param name contains the value to be used
    /// @param islinux ...
    filltree(contener, id, data, islinux) {
        const root = contener.getElementById(id);
        root.setAttribute("style", "list-style-type: none; margin: 0; padding: 0;");

        let last = root;
        let id_ = "htid";

        Object.keys(data).forEach((key, index) => {
            data[key].forEach((item, index) => {
                const hnames = item.split(islinux? "/": "\\");
                hnames.forEach((item, index) => {
                    const li = last.appendChild(contener.createElement("li"));

                    if((hnames.length - 1) === index){
                        li.appendChild(contener.createTextNode(item));
                    }
                    else {
                        id_ += item;
                        last = contener.getElementById(id_);
                        if(null == last){
                            last = this.addtreenested(contener, li, item, id_);
                        }

                    }

                });

                last = root;
                id_ = "htid";
            });
        });

    }

    /// @brief fills hierarchical tree with sub elements
    /// @param li ...
    /// @param item ...
    /// @param id ...
    addtreenested(contener, li, item, id) {
        const span = li.appendChild(contener.createElement("span"));
        span.setAttribute("class", "caret");
        span.addEventListener("click", function() {
            this.parentElement.querySelector(".nested").classList.toggle("active");
            this.classList.toggle("caret-down");
        });

        if("" !== item) {
            span.appendChild(contener.createTextNode(item));
        }
        else {
            //qnd fix to avoid empty 1st item on Linux
            span.appendChild(contener.createTextNode("root"));
        }

        const ul = li.appendChild(contener.createElement("ul"));
        ul.setAttribute("class", "nested");
        ul.setAttribute("id", id);
        ul.setAttribute("style", "list-style-type: none;");

        return ul;
    }

    //------------------------------------------------------------------
    // DISCLOSURE / ACCORDION ITEMS (THROUGH INSTANCE)
    //------------------------------------------------------------------
    /// @brief create the divs contener tree inside the right column
    /// @param contener is the target DOM
    /// @param td is the current element which receives the button
    adddisclosdivs(contener, td) {
        const div = td.appendChild(contener.createElement("div"));
        div.setAttribute("class", "w3-card w3-round");

        const _div = div.appendChild(contener.createElement("div"));
        _div.setAttribute("class", "w3-white");
        return _div;
    }

    /// @brief create the disclosure button which allows to show/hide something
    /// @param contener is the target DOM
    /// @param div is the current element which receives the button
    /// @param data contains button data:
    //      data[0] -> id,
    //      data[1] -> title,
    //      data[2] -> onclick callback
    adddisclosbutton(contener, div, data) {
        const btid = data[0];
        const btcbk = data[2];
        const button = div.appendChild(contener.createElement("button"));
        button.setAttribute("id", btid);
        button.setAttribute("class", "w3-button w3-block w3-theme-l1 w3-left-align");
        button.addEventListener("click", (e) => {
            CLISIDE_DOM.cbkdisclose(contener, btcbk);
        });

        const bttxt = data[1];
        const i = button.appendChild(contener.createElement("i"));
        i.setAttribute("class", "fa fa-angle-double-down fa-fw w3-margin-right");
        button.appendChild(contener.createTextNode(bttxt));

        return button;
    }

    /// @brief creates the data which will be showed / hidden by the disclosure button
    /// @param contener is the target DOM
    /// @param div is the current element which receives the content
    /// @param data contains the data to be displayed
    adddiscloscontent(contener, div, data) {
        const _div = div.appendChild(contener.createElement("div"));

        const idcontent = data[2];
        _div.setAttribute("id", idcontent);
        _div.setAttribute("class", "w3-hide w3-container");

        const table = _div.appendChild(contener.createElement("table"));
        table.setAttribute("class", "w3-table");
        return table;
    }

    /// @brief function to disclose an html item
    /// @param id index of the disclosable item
    static cbkdisclose(contener, id) {
        const x = contener.getElementById(id);
        if (-1 === x.className.indexOf("w3-show")) {
            x.className += " w3-show";
            x.previousElementSibling.className += " w3-theme-d1";
        }
        else {
            x.className = x.className.replace("w3-show", "");
            x.previousElementSibling.className =
                x.previousElementSibling.className.replace(" w3-theme-d1", "");
        }
    }

    //-----------------------------------------------
    // PROGRESS HANDLING
    //-----------------------------------------------
    /// @brief uses "CV.html::boulotentryXXwait" to display a progrss bar during wait
    /// @param contener is the target DOM
    /// @param progress is the bar to be updated
    progressshow(contener, progress) {
        const barid = progress[2];
        if(null != this.progressbars[barid]) {
            //there is already a progress bar?
            return;
        }

        //------------------
        this.progressbars[barid] = contener.getElementById(barid);
        this.progressvals[barid] = 0;
        this.progressitvs[barid] = setInterval((inst) => {
            inst.progressvals[barid] += 1;
            if(false == inst.progressbars[barid].className.includes(CLISIDE_DOM.REACTENTRY())) {
                inst.progressbars[barid].style.width = inst.progressvals[barid] + '%';
            }
            else {
                inst.progressbars[barid].style.width = inst.progressvals[barid] + '%';
            }
        }, this.progressincr, this);

    }

    /// @brief uses "CV.html::boulotentryXXwait" to display a progrss bar during wait
    /// @param contener is the target DOM
    /// @param progress is the bar to be updated
    progresshide(contener, progress) {
        const barid = progress[2];
        if(null == this.progressbars[barid]) {
            //there is no progress bar?
            return;
        }

        //------------------
        if(null != this.progressitvs[barid]) {
            clearInterval(this.progressitvs[barid]);
        }

        const waitid = progress[1];
        const progid = progress[0];
        const it = contener.getElementById(waitid);
        contener.getElementById(progid).removeChild(it);

        this.progressbars[barid] = null;
    }

    //-----------------------------------------------
    // MODAL HANDLING
    //-----------------------------------------------
    /// @brief
    /// @param contener is the destination DOM
    /// @param dom is the source DOM
    /// @param data says which source data shall be fetched
    /// @param idsrc desc
    /// @param iddst desc
    displaymodal (domsrc, datasrc, contndst, idsrc, iddst){
        contndst.getElementById(CLISIDE_BASE.MODALDIV()).style.display = "block";
        contndst.getElementById(CLISIDE_BASE.MODALIMG()).src = idsrc.src;

        //SRC side --------------------
        const idtitle = Object.keys(datasrc)[1];
        const datatitle = datasrc[idtitle];

        const iddate = Object.keys(datasrc)[2];
        const datadate1 = datasrc[iddate][0];
        const datadate2 = datasrc[iddate][1];

        const itsrc = domsrc.getElementById(idtitle);
        itsrc.innerHTML = datatitle + ": " +
            datadate1 + " / " + datadate2;

        //DST side --------------------
        const itdst = contndst.getElementById(iddst);
        for(let content = itdst.firstChild; null != content; content = itdst.firstChild) {
            //target cleanup
            contndst.getElementById(iddst).removeChild(content);
        }

        //target update
        contndst.getElementById(iddst).appendChild(itsrc);
    }

}

/*************************************************************************************
 * IMPLEMENTATION: COMMUNICATION WITH SERVER
 *************************************************************************************/
/// @brief local or remote data/file access
export class CLISIDE_LOADER extends CLISIDE_DOM {

    /// @brief ctor
    /// @param cmdselect ...
    /// @param id ...
    constructor(cmdselect, id) {
        super(id);

        this.target = "serverside";
        this.cmdselect = cmdselect;

        this.parser = new DOMParser();

//        console.log(this.getFuncName() + "OK");
    }

    //-----------------------------------------------------
    // ACCESS
    //-----------------------------------------------------
    /// @brief load local html file
    /// @param contener is the destination DOM
    /// @param file ...
    /// @param srcid ...
    /// @param dstid ...
    /// @param cbk ...
    localgetfile(contener, file, srcid, dstid, cbk=null) {
        const inst = this;

        this.target = file;
        this.getdataraw(null, (result) => {
            if(null != dstid){
                //access loaded content
                const dom = inst.parser.parseFromString(result, "text/html");
                const itsrc = dom.getElementById(srcid);
                itsrc.removeAttribute("id");
                //then push it in destination DOM
                const itdst = contener.getElementById(dstid);
                itdst.appendChild(itsrc);
            }

            if(null != cbk){
                cbk();
            }
        });
    }

    /// @brief display items from another html page
    /// @param contener is the destination DOM
    /// @param file ...
    /// @param data says which source data shall be fetched
    /// @param idsrc ...
    /// @param iddst ...
    /// @param cbk ...
    displayitemsfrom(contener, file, data, idsrc, itdst, cbk) {
        const inst = this;

        this.target = file;
        this.getdataraw(null, (result) => {
            if(true === Array.isArray(data)) {
                data.forEach((item, index) => {
                    const dom = inst.parser.parseFromString(result, "text/html");
                    cbk(dom, item, contener, idsrc, itdst[index]);
                })
            }
            else {
                const dom = inst.parser.parseFromString(result, "text/html");
                cbk(dom, data, contener, idsrc, itdst);
            }
        });
    }

    /// @brief calls serverside with cliside_BLOGphptest1 selector then updates txtHint html item
    /// @param creator is th instance of the creator
    /// @param data ...
    /// @param cbk will be executed
    remotegetbatch(contener, creator, content, progress, cbk) {
        const inst = this;

        if(null != progress) {
            inst.progressshow(contener, progress);
        }

        // with many data to be loaded
        if(true === Array.isArray(content)) {
            /**********************************
             * 1ST: PREPARE DATA FOR UPDATE
             contentmap contains result for each content item.
             it is there to be sure that they are correctly sorted:
             everything is fully asynchrone, so getdatajson may fill the table in a total random way
             **********************************/
            const contentmap = {};
            inst.mapinit(content, contentmap);

            content.forEach((item, index) => {
                /**********************************
                 * 2ND: RETRIEVE DATA
                 * the main request, to get the data item contents
                 * @type {XMLHttpRequest}
                 **********************************/
                const params = [inst.cmdselect, item];
                inst.getdatajson(params, (result) => {
                    // ---------------------------------------------------
                    // store result and survey: we need to be sure that all results are there:
                    contentmap[item] = result;
                    if(false === inst.mapisfull(contentmap)) {
                        /// not everything is there, we need to keep on waiting
                        return;
                    }

                    // ---------------------------------------------------
                    /// OK we got every result for the current data list
                    if(null != progress) {
                        inst.progresshide(contener, progress);
                    }

                    const results = inst.map2array(contentmap);
                    cbk(creator, results);
//                    console.log(this.getFuncName() + "OK");
                });
            })
        }
        // with just one item to be loaded
        else {
            const params = [ inst.cmdselect, content ];
            inst.getdatajson(params, (result) => {
                if(null != progress) {
                    inst.progresshide(contener, progress);
                }

                cbk(creator, result);
            });
        }
    }

    /// @brief calls serverside with cliside_BLOGphptest1 selector then updates ...
    /// @param creator is th instance of the CV creator
    /// @param boite is the name of the desired boite data
    /// @param boulots is the name of the desired boulots data
    /// @param progress contains the DOM items required to display progress
    /// @param cbk will be executed
    remotegetentry(contener, creator, desc, content, progress, cbk) {
        const inst = this;

        if(null != progress) {
            inst.progressshow(contener, progress);
        }

        /**********************************
         * 1ST: PREPARE DATA FOR UPDATE
         **********************************/
        /*
        desc contains result for desc item
        */
        let descdata = null;

        /**********************************
         * 2ND: RETRIEVE DATA
         * the main request, to get the desc item contents
         * @type {XMLHttpRequest}
         **********************************/
        const params = [inst.cmdselect, desc];
        inst.getdatajson(params, (result) => {
            //store result
            descdata = result;

            /**********************************
             * 3RD: PREPARE DATA FOR UPDATE
             **********************************/
            /*
            contentmap contains result for each content item.
            it is there to be sure that they are correctly sorted:
            everything is fully asynchrone, so getdatajson may fill the table in a total random way
            */
            const contentmap = {};
            inst.mapinit(content, contentmap);

            content.forEach((item, index) => {
                /**********************************
                 * 4TH: RETRIEVE DATA
                 * the sub request, to get a "content item contents" :)
                 * @type {XMLHttpRequest}
                 **********************************/
                const _params = [inst.cmdselect, item];
                inst.getdatajson(_params, (_result) => {
                    // ---------------------------------------------------
                    // store result and survey: we need to be sure that all results are there:
                    contentmap[item] = _result;
                    if(false === inst.mapisfull(contentmap)) {
                        /// not everything is there, we need to keep on waiting
                        return;
                    }

                    // ---------------------------------------------------
                    /// OK we got all content results
                    if(null != progress) {
                        inst.progresshide(contener, progress);
                    }

                    cbk(creator, descdata, contentmap);
                });
            })
        });
    }

    //-----------------------------------------------
    // CORE
    //-----------------------------------------------
    /// @brief desc...
    /// @param params ...
    /// @param cbk ...
    getdataraw(params, cbk) {
        const xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                try {
                    cbk(this.responseText);
//                  console.log(local.getFuncName() + "OK");

                }
                catch (e) {
                    console.log(e.errortext);
                }
            }
        };

        xmlhttp.open("GET", this.createGETstr(params), true);
        xmlhttp.send();
    }

    /// @brief desc...
    /// @param params ...
    /// @param cbk ...
    getdatajson(params, cbk) {
        const xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                try {
                    const jsonRes = JSON.parse(this.responseText);
                    cbk(jsonRes);
//                  console.log(local.getFuncName() + "OK");

                }
                catch (e) {
                    console.log(e.errortext);
                }
            }
        };

        xmlhttp.open("GET", this.createGETstr(params), true);
        xmlhttp.send();
    }

    //-----------------------------------------------
    // MAP HANDLING
    //-----------------------------------------------
    /// @brief a way to be sure that the datas are in a proper order:
    /// the keys will be sorted following the order of the array
    /// @param datamap ...
    mapinit(data, datamap) {
        data.forEach((key, index) => {
            datamap[key] = null;
        });
    }

    /// @brief to be called each time a data item has been received
    /// @param datamap ...
    mapisfull(datamap) {
        let isfull = true;
        Object.keys(datamap).forEach((key, _index) => {
            if(null == datamap[key]) {
                isfull = false;
            }
        });

        return isfull;
    }

    /// @brief ...
    /// @param datamap ...
    map2array(datamap) {
        const results = [];
        Object.keys(datamap).forEach((key, _index) => {
            results.push(datamap[key]);
        });

        return results;
    }

    //-----------------------------------------------
    // UTILS
    //-----------------------------------------------
    /// @brief create an http text request line to serverside.php
    /// @param name params is an array which may contain up to 2 parameters
    /// @returns the formatted GET request
    createGETstr(params) {
        let cmd = this.target;
        if(null != params){
            params.forEach((item, index) => {
                cmd +=
                    ((0 === index)? "?": "&") +
                    "p" + (index+1) + "=" + item;
            })
        }

        return cmd;
    }

}

/*************************************************************************************
 * IMPLEMENTATION: PAGE STRUCTURE HANDLER
 *************************************************************************************/
export class CLISIDE_PAGE extends CLISIDE_BASE {

    static LATLON() { return [ 41.878114, -87.629798 ] };

    /*************************************************************************************
     * IMPLEMENTATION: PAGE UTILS
     *************************************************************************************/
    /// @brief Used to toggle the menu on small screens when clicking on the menu button
    /// @param contener is the target DOM
    static navtoggle(contener, navid) {
        try {
            const x = contener.getElementById(navid);
            if (x.className.indexOf("w3-show") === -1) {
                x.className += " w3-show";
            }
            else {
                x.className = x.className.replace(
                    " w3-show", ""
                );
            }
        }
        catch (e) {
            console.log(e.name)
        }
        finally {
            //...
        }
    }

    /// @brief print function
    /// @param contener
    static disctoggle(contener) {
        try {
            const disclosable = contener.getElementsByClassName("w3-hide w3-container");
            var temp = [];
            [].push.apply(temp, disclosable);
//        it seems that the following notation doesn't anyway functionate in VERDAMMT MICROSOFT EDGE
//        [...disclosable].forEach((item, index) => {
            temp.forEach((item, index) => {
                CLISIDE_DOM.cbkdisclose(contener, item.getAttribute("id"));
            });

            if(false === cliside_disctoggled ) {
                cliside_disctoggled = true;
            }
            else {
                cliside_disctoggled = false;
            }

//        console.log(this.getFuncName() + "OK");
        }
        catch (e) {
            console.log(e.name)
        }
        finally {
            //...
        }
    }

    /// @brief scroll function
    /// @param contener is the target DOM
    /// @param param maybe anything
    static pagescrolllazy(contener, param) {
        window.scrollrect = null;

        function getOffset(el) {
            const rect = el.getBoundingClientRect();
            return {
//            left: (rect.right + window.scrollX ) +'px',
//            top: (rect.top + window.scrollY ) +'px'
                left: rect.right,
                top: rect.top
            }
        }

        const itemabout = contener.getElementById("aboutcard");
        const itemdetails = contener.getElementById("detailscard");
        if (contener.body.scrollTop > 50 || contener.documentElement.scrollTop > 50) {
            itemabout.style.transform = "scale(0.5, 0.5)";

            itemdetails.style.transform = "";
            itemdetails.style.position = "relative";
            itemdetails.style.top = '-230px';
            itemdetails.style.bottom = '+230px';
            /*
            if(null != window.scrollrect){
                var xPosition = window.scrollrect.left - contener.getBoundingClientRect().left - (item.clientWidth / 2);
                var yPosition = window.scrollrect.top - contener.getBoundingClientRect().top - (item.clientHeight / 2);
                // in case of a wide border, the boarder-width needs to be considered in the formula above
                item.style.left = xPosition + "px";
                item.style.top = yPosition + "px";
            }
            */
        }
        else {
            itemabout.style.transform = "";

            itemdetails.style.transform = "";
            itemdetails.style.position = "relative";
            itemdetails.style.top = '-10px';

            /*
            if(null == window.scrollrect){
                window.BLOGscrollrect = getOffset(item);
            }
            */
        }
    }

    /*************************************************************************************
     * IMPLEMENTATION: GOOGLE MAPS INTEGRATION
     *************************************************************************************/
    /// @brief ...
    /// @param contener is the target DOM
    /// @param mapid ...
    static gmapshow(contener, mapid/*"googleMap"*/) {
        try {
            // --------------------------------------
            //1.1: construct location
            const where = new google.maps.LatLng(CLISIDE_PAGE.LATLON()[0], CLISIDE_PAGE.LATLON()[1]);
            //1.2: construct parameters using location
            const options = {
                center: where,
                zoom: 12,
                scrollwheel: false,
                draggable: false,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            //1.3: construct map using parameters
            const map = new google.maps.Map(contener.getElementById(mapid), options);

            // --------------------------------------
            //2.1: construct marker using location
            const marker = new google.maps.Marker({position: where});
            //2.2: assigns marker to map
            marker.setMap(map);
        }
        catch (e) {
            console.log(e.name)
        }
        finally {
            //...
        }
    }

    /*************************************************************************************
     * IMPLEMENTATION: INTERNALE
     *************************************************************************************/
    /// ctor
    /// @param id
    constructor(id) {
        // from stackoverlow:
        // https://stackoverflow.com/questions/29480569/does-ecmascript-6-have-a-convention-for-abstract-classes
        if (new.target === CLISIDE_PAGE) {
            throw new TypeError("Cannot construct CLISIDE_PAGE instance directly");
        }

        super(id);

    }

    loadtop(contener, file) {}
    loadbody(contener, file) {}
    loadbottom(contener, file) {}
}
