"use strict"

class JSApp {

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
                        const value = local.parse(text, jsonRes[key]);
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
                    const value = local.parse(text, jsonRes[select]);
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
