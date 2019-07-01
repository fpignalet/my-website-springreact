/// @brief main entry function
/// @uses AEngine, Input, Resources
import Multiple from "./instances.js";
import Resources from "./resources.js";
import Renderer from "./render.js";

function execute(images, engine) {

    let firsttime = true;

    // -----------------------------------------------------------------------------------------------------------------
    // ENTRY STUFF

    /// @brief start everything, immediately after initialisation
    function exec_init() {
        engine.setup(images[0]);
        engine.reset();

        const render = Multiple.get(Renderer.IDENT(), engine.index);
        render.setup(images[1]);

        exec_loop();
    }

    /// @brief The app loop, scheduled by requestAnimFrame below
    function exec_loop() {
        if(true === engine.isPageOver) {
            return;
        }

        if(true === firsttime) {
            engine.end();
            firsttime = false;
        }
        else {
            engine.execute(Date.now());
        }

        const render = Multiple.get(Renderer.IDENT(), engine.index);
        render.update();

        exec_requestAnimFrame(exec_loop);
    }

    /// @brief A cross-browser requestAnimationFrame.
    /// See https://hacks.mozilla.org/2011/08/animating-with-javascript-from-setinterval-to-requestanimationframe/
    let exec_requestAnimFrame = (function () {
//        console.log(arguments.callee.name);

        return window.requestAnimationFrame ||
            window.webkitRequestAnimationFrame ||
            window.mozRequestAnimationFrame ||
            window.oRequestAnimationFrame ||
            window.msRequestAnimationFrame ||
            function (callback) {
//                console.log(arguments.callee.name);

                window.setTimeout(callback, 1000 / 60);
            };
    })();

    const rsrc = Multiple.get(Resources.IDENT(), engine.index);
    rsrc.setup(images);
    rsrc.onReady(exec_init);
}

export default execute;