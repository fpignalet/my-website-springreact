"use strict"

class JSApp {

    /**
     *
     * @param req
     * @param param
     * @param select
     */
    httpGetRAW(req, param) {
        const local = this;

        const xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                alert(this.responseText);
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
    httpGetJSON(req, param, select) {
        const local = this;

        const xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                const jsonRes = JSON.parse(this.responseText);

                let text = "";
                let value = local.parse(text, jsonRes[select]);
                alert(value);
            }
        };

        xmlhttp.open("GET", req + "?param=" + param, true);
        xmlhttp.send();
    }

    /**
     *
     * @param text
     * @param select
     */
    parse(text, data) {
        const local = this;

        let value = text;

        if(Object.prototype.toString.call(data) === '[object Array]') {
            data.forEach((itm, idx) => {
                value = local.parse(value, itm);
            });
        }
        else if(Object.prototype.toString.call(data) === '[object Map]') {
            Object.keys(data).forEach((itm, idx) => {
                value = local.parse(value, itm + ": " + data[itm]);
            });
        }
        else if(Object.prototype.toString.call(data) === '[object Object]') {
            Object.keys(data).forEach((itm, idx) => {
                value = local.parse(value, itm + ": " + data[itm]);
            });
        }
        else {
            value = text + data + "\r\n";
        }

        return value;
    }

}

/**
 *
 * @type {JSApp}
 */
const app = new JSApp();
