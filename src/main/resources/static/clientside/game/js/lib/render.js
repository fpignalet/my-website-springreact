'use strict';
import Multiple from "./instances.js";
import Resources from "./resources.js";
import AEngine from "./engine.js";

class Renderer extends Multiple {

    /****************************************
     * CONSTANTS
     ****************************************/
    static IDENT() { return "render" };

    /****************************************
     * PUBLIC IMPLEMENTATION
     ****************************************/

    /// @brief
    /// @param wdht
    /// @param id
    constructor(wdht, id) {
        super(Renderer.IDENT());

        if(null != id) {
            this.canvas = document.getElementById(id);
        }
        else {
            this.canvas = document.createElement("canvas");
            document.body.appendChild(this.canvas);
        }

        this.canvas.width = wdht[0];
        this.canvas.height = wdht[1];

        this.ctx = this.canvas.getContext("2d");
    }

    /// @brief
    /// @param bkgnd is the background image to be used
    setup(bkgnd) {
        this.background_prepare(bkgnd);
    }

    /// @brief redraws everything
    update() {
        this.background_draw();

        const engine = Multiple.get(AEngine.IDENT(), this.index);
        if (!engine.isGameOver) {
            this.entities_drawone(engine.player);
        }
        this.entities_drawall(engine.bullets);
        this.entities_drawall(engine.enemies);
        this.entities_drawall(engine.explosions);

    }

    /****************************************
     * INTERNAL IMPLEMENTATION
     ****************************************/
    background_prepare(bkgnd) {
        const rsrc = Multiple.get(Resources.IDENT(), this.index);
        this.image = rsrc.get(bkgnd);
        this.velocity = 100;
        this.distance = this.image.width;
        this.lastFrameRepaintTime = window.performance.now();
    }

    background_calcOffset(time){
        var frameGapTime = time - this.lastFrameRepaintTime;
        this.lastFrameRepaintTime = time;

        var translateX = this.velocity*(frameGapTime/1000);
        return translateX;
    }

    background_draw() {
        try {
            this.ctx.save();

            const time = window.performance.now();
            this.ctx.clearRect(0,0,this.canvas.width,this.canvas.height);

            this.distance -= this.background_calcOffset(time);
            if(this.distance <= 0){
                this.distance = this.image.width;
            }
            this.ctx.translate(this.distance,0);

            this.ctx.drawImage(this.image,0,0);
            this.ctx.drawImage(this.image,-this.image.width+1,0);
        }
        finally {
            this.ctx.restore();
        }
    }

    /// @brief draws entities
    /// @param list
    entities_drawall(list) {
        list.forEach((item, index) => {
            this.entities_drawone(item);
        });
    }

    /// @brief draws one entity
    /// @param entity
    entities_drawone(entity) {
        try {
            this.ctx.save();

            //-------------------------------
            //prepare sprite frame
            this.ctx.translate(entity.pos[0], entity.pos[1]);

            let sprite = entity.sprite;
            let coord = sprite.render();
            if(null == coord) {
                this.ctx.restore();
                return;
            }

            //-------------------------------
            //display sprite
            this.ctx.drawImage(sprite.image,
                coord[0], coord[1],
                sprite.size[0], sprite.size[1],
                0, 0,
                sprite.size[0], sprite.size[1]);

        }
        finally {
            this.ctx.restore();

        }
    }

}

export default Renderer;