"use strict";

import {JSUtils} from "../lib/utils.js"

class JSApp {

    constructor () {
        this.jsutils = new JSUtils();
    }

    /**
     *
     * @param req
     * @param param
     * @param select
     */
    httpGetRAW(req, param, cbk) {
        const local = this;

        const xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                if(null != cbk){
                    cbk(this.responseText);
                }
                else{
                    console.log(this.responseText);
                    alert(this.responseText);
                }
            }
        };

        xmlhttp.open("GET", req + "?param=" + param, true);
        xmlhttp.send();
    }

    /**
     *
     * @param req
     * @param param
     * @param select
     */
    httpGetJSON(req, param, select, cbk) {
        const local = this;

        const xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                const jsonRes = JSON.parse(this.responseText);

                let text = "";
                if(null == select){
                    for (var key in jsonRes) {
                        const value = local.jsutils.parse(text, jsonRes[key]);
                        if(null != cbk){
                            cbk(value);
                        }
                        else{
                            console.log(key + ': ' + jsonRes[key]);
                            alert(value);
                        }
                    }
                }
                else{
                    const value = local.jsutils.parse(text, jsonRes[select]);
                    if(null != cbk){
                        cbk(value);
                    }
                    else{
                        console.log(key + ': ' + jsonRes[key]);
                        alert(value);
                    }
                }
            }
        };

        xmlhttp.open("GET", req + "?param=" + param, true);
        xmlhttp.send();
    }

}

const restRequests = [
    "guirequestfrommain",
    "guirequestfromreact",
    "testhttpgetfromparam",
    "exthttpgetjson1",
    "testpopulatedb"
];

window.guirequestfrommain = () => {
    const jsapp = new JSApp();
    jsapp.httpGetRAW(restRequests[0], "here is CONTENT MAIN", (text) => {
        console.log(text)
    });
}

window.guirequestfromreact = () => {
    const jsapp = new JSApp();
    jsapp.httpGetRAW(restRequests[1], "here is REACT PAGE");
}

window.testhttpgetfromparam = () => {'testpopulatedb'
    const jsapp = new JSApp();
    jsapp.httpGetJSON(restRequests[2], 'MAIN ENTRY', 'item1');
}

window.exthttpgetjson1 = () => {
    const jsapp = new JSApp();
    jsapp.httpGetJSON(restRequests[3], null, "DBConteners", (text) => {
        console.log(text);

        const item = document.createElement("li");
        item.innerText = text;

        const dynlist = document.getElementById("dyntest");
        dynlist.appendChild(item);
    });
}

window.testpopulatedb = () => {
    const jsapp = new JSApp();
    jsapp.httpGetJSON(restRequests[4], null, 'DBConteners')
}
