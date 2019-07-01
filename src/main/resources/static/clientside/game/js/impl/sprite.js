'use strict';

class Sprite {

    /****************************************
     * PUBLIC IMPLEMENTATION
     ****************************************/
    /// @brief ctor
    /// @param image
    /// @param pos
    /// @param size
    /// @param speed
    /// @param frames
    /// @param dir
    /// @param once
    constructor(image, pos, size, speed, frames, dir, once) {
        this.image = image;

        this.pos = pos;
        this.size = size;
        this.speed = typeof speed === 'number' ? speed : 0;
        this.frames = frames;
        this.dir = dir || 'horizontal';
        this.once = once;

        this.index = 0;
        this.done = false;
    };

    /// @brief updates frame selector which will be used in render
    /// @param dt
    update(dt) {
        this.index += this.speed * dt;
    }

    /// @brief
    /// @returns updated [ x, y ] coordinates
    render() {

        //-------------------------------
        //find frame
        let nthframe = 0;
        if (this.speed > 0) {
            const idx = Math.floor(this.index);
            const max = this.frames.length;
            nthframe = this.frames[idx % max];

            if (this.once && idx >= max) {
                this.done = true;
                return null;
            }
        }

        //-------------------------------
        //find coordinates
        let x = this.pos[0];
        let y = this.pos[1];
        if (this.dir === 'vertical') {
            y += nthframe * this.size[1];
        }
        else {
            x += nthframe * this.size[0];
        }

        return [ x, y ];
    }

}

export default Sprite;