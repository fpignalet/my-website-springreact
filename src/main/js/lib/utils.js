"use strict";

export class JSUtils {

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
