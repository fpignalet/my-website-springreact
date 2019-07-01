'use strict';

import Multiple from "./instances.js";
import AEngine from "../lib/engine.js";
import Renderer from "../lib/render.js";

class Input extends Multiple {

    /****************************************
     * CONSTANTS
     ****************************************/
    static IDENT() { return "input" };

    static SPACE() { return "SPACE" };
    static LEFT() { return "LEFT" };
    static UP() { return "UP" };
    static RIGHT() { return "RIGHT" };
    static DOWN() { return "DOWN" };
    static CLICK() { return "CLICK" };

    /****************************************
     * PUBLIC IMPLEMENTATION
     ****************************************/

    /// @brief
    /// @uses Factory, Renderer
    constructor() {
        super(Input.IDENT());

        this.pressedKeys = {};
        this.mousing = false;
    }

    /// @brief
    /// @param canid
    setUp(canid) {
        const target = document.getElementById(canid);

        const local = this;

        //--------------------------------------
        target.addEventListener('touchstart', (e) => {
            local.touched(e, true);
        });
        target.addEventListener('touchmove', (e) => {
            local.touched(e, true);
        });
        target.addEventListener('mousedown', (e) => {
            local.moused(e, true);
            local.mousing = true;
        });
        target.addEventListener('mousemove', (e) => {
            if(true === local.mousing) {
                local.moused(e, true);
            }
            else {
                local.pressedKeys = {};
            }
        });
        document.addEventListener('keydown', (e) => {
            local.keyed(e, true);
        });

        //--------------------------------------
        target.addEventListener('touchend', (e) => {
            local.touched(e, false);
        });
        target.addEventListener('mouseup', (e) => {
            local.moused(e, false);
            local.mousing = false;
        });
        document.addEventListener('keyup', (e) => {
            local.keyed(e, false);
        });

        //--------------------------------------
        window.addEventListener('blur', (e) => {
            local.keyed(e, false);
        });

    }

    /// @brief
    /// @param key
    isSelected(key) {
        return this.pressedKeys[key.toUpperCase()];
    }

    /****************************************
     * INTERNAL IMPLEMENTATION
     ****************************************/

    /// @brief
    /// @param event is the event forwarded by handler
    /// @param status is true when keydown, false when keyup
    keyed(event, status) {
        const engine = Multiple.get(AEngine.IDENT(), this.index);
        if(true === engine.isGameOver) {
            this.pressedKeys = {};
            return;
        }

        const code = event.keyCode;

        let key;
        switch(code) {
            case 32:
                key = Input.SPACE(); break;
            case 37:
                key = Input.LEFT(); break;
            case 38:
                key = Input.UP(); break;
            case 39:
                key = Input.RIGHT(); break;
            case 40:
                key = Input.DOWN(); break;
            default:
                // Convert ASCII codes to letters
                key = String.fromCharCode(code);
        }

        this.pressedKeys[key] = status;

        event.returnValue = false;
    }

    /// @brief
    /// @param event is the event forwarded by handler
    /// @param status is true when mousedown, false when mouseup
    moused(event, status) {
        const engine = Multiple.get(AEngine.IDENT(), this.index);
        if(true === engine.isGameOver) {
            this.pressedKeys = {};
            return;
        }

        let where = null;
        if(true == status){
            const render = Multiple.get(Renderer.IDENT(), this.index);
            let rect = render.canvas.getBoundingClientRect();
            let root = document.documentElement;

            where = {
                X: event.clientX - rect.left - root.scrollLeft,
//                  X: event.clientX - rect.left,
//                  Y: event.clientY - rect.top - root.scrollTop,
                Y: event.clientY - rect.top
            };
        }

        let key = Input.CLICK();
        this.pressedKeys[key] = where;

        event.returnValue = false;
    }

    /// @brief
    /// @param event is the event forwarded by handler
    /// @param status is true when touchstart, false when touchend
    touched(event, status) {
        const engine = Multiple.get(AEngine.IDENT(), this.index);
        if(true === engine.isGameOver) {
            this.pressedKeys = {};
            return;
        }

        let where = null;
        if(true == status){
            let rect = Multiple.get(Renderer.IDENT(), this.index).canvas.getBoundingClientRect();
            let root = document.documentElement;

            where = {
                X: parseInt(event.changedTouches[0].clientX) - rect.left - root.scrollLeft,
//              X: event.clientX - rect.left,
//              Y: event.clientY - rect.top - root.scrollTop,
                Y: parseInt(event.changedTouches[0].clientY)  - rect.top
            };
        }

        let key = Input.CLICK();
        this.pressedKeys[key] = where;

        event.returnValue = false;
    }

}

export default Input;