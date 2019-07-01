'use strict';
import AEngine from "../lib/engine.js";
import Renderer from "../lib/render.js";
import Factory from "./factory.js";
import Multiple from "../lib/instances.js";

class Core extends AEngine{

    static PLAYERXSTART() { return 50 };

    /// @brief ctor
    /// @param settings
    /// @param canid
    constructor(settings, guimap, canid) {
        super(settings, canid);

        this.elemscoreid = guimap["elemscoreid"];
        this.elemplayagainid = guimap["elemplayagainid"];
        this.elemgameoverid = guimap["elemgameoverid"];
        this.elemgameoverlayid = guimap["elemgameoverlayid"];
        this.elemlifebarid = guimap["elemlifebarid"];

        // -----------------------------------------
        new Factory(settings);
    }

    // -------------------------------------------------------------------------------------------------------------
    /// GUI layer, with DOM access

    /// @brief start everything
    /// @param something
    /// @uses Factory
    setup(something) {
        const factory = Multiple.get(Factory.IDENT(), this.index);
        factory.setup(something);

        const local = this;
        let it = document.getElementById(this.elemplayagainid+this.index);
        it.addEventListener('click', () => {
            local.reset();
        });

    }

    /// @brief hide gameover panel
    begin() {
        let it = null;

        it = document.getElementById(this.elemgameoverid+this.index);
        it.style.display = 'none';
        it = document.getElementById(this.elemgameoverlayid+this.index);
        it.style.display = 'none';
    }

    /// @brief show gameover panel. says game is over.
    end() {
        let it = null;

        it = document.getElementById(this.elemgameoverid+this.index);
        it .style.display = 'block';
        it = document.getElementById(this.elemgameoverlayid+this.index);
        it.style.display = 'block';

        this.life = 0;
        this.isGameOver = true;
    }

    /// @brief start everything
    /// @param something
    updatescore() {
        let it = document.getElementById(this.elemscoreid+this.index);
        it.innerHTML = this.score;

    }

    /// @brief
    updatelife() {
        let it = null;

        it = document.getElementById(this.elemlifebarid+this.index);
        it.style.width = this.life + '%';
        if(50 >= this.life) {
            it = document.getElementById(this.elemlifebarid+this.index);
            it.style.backgroundColor = "red";
        }
        else {
            document.getElementById(this.elemlifebarid+this.index);
            it.style.backgroundColor = "#68B4FF";
        }

    }

    /**********************************************************************
     * PLAYER HANDLING
     **********************************************************************/
    /// @brief add player sprite
    /// @uses Factory, Renderer
    addPlayer() {
        const render = Multiple.get(Renderer.IDENT(), this.index);
        const x = Core.PLAYERXSTART();
        const y = render.canvas.height / 2;

        const factory = Multiple.get(Factory.IDENT(), this.index);
        const sprite = factory.getplayersprite();
        this.player = {
            pos: [ x, y ],
            sprite: sprite
        };
    }

    /// @brief remove player sprite
    endPlayer() {
        // Add an explosion
        this.endShot(this.player.pos);
    }

    /**********************************************************************
     * ENEMY HANDLING
     **********************************************************************/
    /// @brief add enemy sprite
    /// @uses Factory, Renderer
    addEnemy() {
        // It gets harder over time by adding enemies using this equation:
        // 1-.993^enemyTime
        if (Math.random() >= (1 - Math.pow(.993, this.enemyTime))) {
            return;
        }

        const render = Multiple.get(Renderer.IDENT(), this.index);
        const x = render.canvas.width;
        const y = Math.random() * (render.canvas.height - 39);

        const factory = Multiple.get(Factory.IDENT(), this.index);
        const sprite = factory.getenemysprite();
        this.enemies.push({
            pos: [ x, y ],
            sprite: sprite
        });
    }

    /// @brief remove enemy sprite
    /// @param i is the index of the enemy
    /// @param j is the index of the bullet which has downed the enemy or -1 when collides with player
    /// @param pos is the
    endEnemy(i, j, pos) {

        // Remove the enemy
        this.enemies.splice(i, 1);

        if(-1 !== j) {
            // Remove the bullet
            this.bullets.splice(j, 1);

            // Add an explosion
            this.endShot(pos);

        }
        else {
            // Remove the player
            this.endPlayer();

        }
    }

    /**********************************************************************
     * FIRE HANDLING
     **********************************************************************/
    /// @brief
    /// @uses Factory
    addShot() {
        const x = this.player.pos[0] + this.player.sprite.size[0] / 2;
        const y = this.player.pos[1] + this.player.sprite.size[1] / 2;

        const factory = Multiple.get(Factory.IDENT(), this.index);
        const sprites = factory.getbulletssprites();
        this.bullets.push({
            pos: [ x, y ],
            dir: 'forward',
            sprite: sprites[0]
        });
        this.bullets.push({
            pos: [ x, y ],
            dir: 'up',
            sprite: sprites[1]
        });
        this.bullets.push({
            pos: [ x, y ],
            dir: 'down',
            sprite: sprites[2]
        });
    }

    /// @brief
    /// @param pos
    /// @uses Factory
    endShot(pos) {
        const factory = Multiple.get(Factory.IDENT(), this.index);
        const sprite = factory.getexplosionsprite();
        this.explosions.push({
            pos: pos,
            sprite: sprite
        });
    }

}

export default Core;