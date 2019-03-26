"use strict"

class JSApp {

    /**
     *
     */
    execute() {
        console.log("PUTAIN CA MARCHE")
    }

    /**
     *
     * @param text
     * @param result
     */
    cbk(text, result) {
        const jsonRes = JSON.parse(text);
        const data = jsonRes[result];
        if(Object.prototype.toString.call(data) === '[object Map]') {
            alert(data[Object.keys(data)[0]]);
        }
        else if(Object.prototype.toString.call(data) === '[object Array]') {
            alert(data[0]);
        }
        else if(Object.prototype.toString.call(data) === '[object Object]') {
            let text = "";
            Object.keys(data).forEach((itm, idx) => {
                text = text + itm + ": " + data[itm] + " / ";

            });
            alert(text);
        }
        else {
            alert(data);
        }
    }

    /**
     *
     * @param req
     * @param param
     * @param result
     */
    httpGet(req, param, result) {
        const local = this;

        const xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                local.cbk(this.responseText, result);
            }
        };
        xmlhttp.open("GET", req + "?param=" + param, true);
        xmlhttp.send();
    }

}

/**
 *
 * @type {JSApp}
 */
const app = new JSApp();
app.execute();