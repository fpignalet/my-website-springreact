'use strict';
import Multiple from "./instances.js";

class Resources extends Multiple {

    /****************************************
     * CONSTANTS
     ****************************************/
    static IDENT() { return "rsrc" };

    /****************************************
     * PUBLIC IMPLEMENTATION
     ****************************************/

    /// @brief
    constructor () {
        super(Resources.IDENT());

        this.resourceCache = {};
        this.readyCallbacks = [];
    }

    // --------------------------------------------------------------
    /// @brief
    /// @param url
    get(url) {
        return this.resourceCache[url];
    }

    /// @brief Load an image url or an array of image urls
    /// @param urlOrArr a path or a path array
    setup(urlOrArr) {
        const local = this;
        if (urlOrArr instanceof Array) {
            urlOrArr.forEach(function (url) {
                local.load(url);
            });
        }
        else {
            this.load(urlOrArr);
        }
    }

    /// @brief registers a function to be called when all resources are loaded
    /// @param func is a callback
    onReady(func) {
        this.readyCallbacks.push(func);
    }

    // --------------------------------------------------------------
    /// @brief load a resource
    /// @param url is the path of the resource to be loaded
    load(url) {
        if (this.resourceCache[url]) {
            return this.resourceCache[url];
        }
        else {
            const local = this;
            const img = new Image();
            img.onload = function () {
                local.resourceCache[url] = img;

                if (local.isReady()) {
                    local.readyCallbacks.forEach(function (func) {
                        func();
                    });
                }
            };

            this.resourceCache[url] = false;
            img.src = url;
        }
    }

    /// @brief
    /// @returns true when all required resources have been loader
    isReady() {
        let ready = true;
        for (let k in this.resourceCache) {
            if (this.resourceCache.hasOwnProperty(k) &&
                !this.resourceCache[k]) {
                ready = false;
            }
        }

        return ready;
    }

}

export default Resources;