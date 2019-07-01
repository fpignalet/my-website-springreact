'use strict';
import Multiple from "../lib/instances.js";
import Resources from "../lib/resources.js";
import Sprite from "./sprite.js";

class Factory extends Multiple {

    /****************************************
     * CONSTANTS
     ****************************************/
    static IDENT() { return "factory" };

    /****************************************
     * PUBLIC IMPLEMENTATION
     ****************************************/
    /// @brief
    /// @param settings
    /// @uses Factory
    constructor(settings) {
        super(Factory.IDENT());

        this.sprites = null;

        this.speedPlayer = settings["speedPlayer"];
        this.speedEnemy = settings["speedEnemy"];
        this.speedExplosion = settings["speedExplosion"];
    }

    /// @brief
    /// @param sprites
    /// @uses Resources
    setup(sprites) {
        const rsrc = Multiple.get(Resources.IDENT(), this.index);
        this.sprites = rsrc.get(sprites);
    }

    /// @brief returns one sprite for the player
    getplayersprite() {
        return new Sprite(
            // url,
            this.sprites,
            // pos, size,
            [0, 0], [39, 39],
            // speed,
            this.speedPlayer,
            // frames,
            [0, 1])
    }

    /// @brief returns 3 sprites for a new shot
    getbulletssprites() {
        return [
            new Sprite(
                // url,
                this.sprites,
                // pos, size,
                [0, 39], [18, 8]
            ),
            new Sprite(
                // url,
                this.sprites,
                // pos, size,
                [0, 50], [9, 5]
            ),
            new Sprite(
                // url,
                this.sprites,
                // pos, size,
                [0, 60], [9, 5]
            )
        ]
    }

    /// @brief returns one sprite for a new enemy
    getenemysprite() {
        return new Sprite(
            // url,
            this.sprites,
            // pos, size,
            [0, 78], [80, 39],
            // speed,
            this.speedEnemy,
            // frames,
            [0, 1, 2, 3, 2, 1])
    }

    /// @brief returns an explosion sprite
    getexplosionsprite() {
        return new Sprite(
            // url,
            this.sprites,
            // pos, size,
            [0, 117], [39, 39],
            // speed,
            this.speedExplosion,
            // frames,
            [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
            // ...dir, once
            null,
            true)
    }

}

export default Factory;