/// @brief abstract class to implement all central game mechanism
import Multiple from "./instances.js";
import Resources from "./resources.js";
import Renderer from "../lib/render.js";
import Input from "../lib/input.js";

class AEngine extends Multiple {

    /****************************************
     * CONSTANTS
     ****************************************/
    static IDENT() { return "engine" };

    /****************************************
     * PUBLIC IMPLEMENTATION
     ****************************************/

    /// @brief
    /// @param settings
    /// @param canid
    /// @uses AEngine
    constructor(settings, canid) {
        // from stackoverlow:
        // https://stackoverflow.com/questions/29480569/does-ecmascript-6-have-a-convention-for-abstract-classes
        if (new.target === AEngine) {
            throw new TypeError("Cannot construct AEngine instance directly");
        }

        super(AEngine.IDENT());

        // -----------------------------------------
        this.incrPlayer = settings["incrPlayer"];
        this.incrBullet = settings["incrBullet"];
        this.incrEnemy = settings["incrEnemy"];
        this.incrScore = settings["incrScore"];

        this.threshFire = settings["threshFire"];

        // -----------------------------------------
        this.init();
        this.create(settings, canid);
    }

    /// @brief init framework
    /// @param settings
    /// @param canid
    /// @uses Resources, Input, Renderer
    create(settings, canid) {
        new Resources();

        const wdht = [ settings["width"], settings["height"] ];
        new Renderer(wdht, canid);

        new Input();
        Multiple.get(Input.IDENT(), this.index).setUp(canid);
    }

    /// @brief prepare internal handling attributes for each new game
    init() {
        // entities
        this.player = {};
        this.bullets = [];
        this.enemies = [];
        this.explosions = [];

        // time
        const now = Date.now();
        this.lastFire = now;
        this.lastTime = now;
        this.enemyTime = 0;

        // status
        this.life = 100;
        this.score = 0;
        this.isGameOver = false;
        this.isPageOver = false;
    }

    /// @brief Reset game to original state
    reset() {
        this.init();

        this.begin();
        this.addPlayer();
        this.updatelife();

    }

    /// @brief the main entry method, which leads everything
    /// @param now is the current time
    execute(now) {

        try {
            const dt = (now - this.lastTime) / 1000.0;

            this.updatelife();

            /*****************************************
             UPDATING ENTITIES
             *****************************************/
            this.updateEntities(dt);
            const addshot = this.handleInput(dt);
            if (true === this.isGameOver) {
                return;
            }

            /*****************************************
             ADDING ENTITIES
             *****************************************/
            if(true === addshot) {
                this.addShot();
                this.lastFire = now;
            }

            this.enemyTime += dt;
            this.addEnemy();

            /*****************************************
             REMOVING ENTITIES
             *****************************************/
            this.checkPlayerBounds();
            this.checkEnemyCollisions();
        }
        catch (e) {
            this.end();
        }
        finally {
            this.updatescore();
            this.lastTime = now;
        }
    }

    // -------------------------------------------------------------------------------------------------------------
    /// @brief
    /// @param dt
    /// @uses Input
    handleInput(dt) {
        let fire = false;

        const input = Multiple.get(Input.IDENT(), this.index);
        const somewhere = input.isSelected(Input.CLICK());
        if(null != somewhere){
            //Touch input
            if(this.player.pos[0] < somewhere["X"]){
                this.player.pos[0] += this.incrPlayer * dt;
            }

            if(this.player.pos[0] > somewhere["X"]){
                this.player.pos[0] -= this.incrPlayer * dt;
            }

            if(this.player.pos[1] < somewhere["Y"]){
                this.player.pos[1] += this.incrPlayer * dt;
            }

            if(this.player.pos[1] > somewhere["Y"]){
                this.player.pos[1] -= this.incrPlayer * dt;
            }

            fire = (Date.now() - this.lastFire) > this.threshFire;
        }
        else {
            //Mouse / keyboard input
            if (input.isSelected(Input.LEFT()) || input.isSelected('a')) {
                this.player.pos[0] -= this.incrPlayer * dt;
            }

            if (input.isSelected(Input.RIGHT()) || input.isSelected('d')) {
                this.player.pos[0] += this.incrPlayer * dt;
            }

            if (input.isSelected(Input.DOWN()) || input.isSelected('s')) {
                this.player.pos[1] += this.incrPlayer * dt;
            }

            if (input.isSelected(Input.UP()) || input.isSelected('w')) {
                this.player.pos[1] -= this.incrPlayer * dt;
            }

            if (input.isSelected(Input.SPACE())) {
                fire = (Date.now() - this.lastFire) > this.threshFire;
            }

        }

        return fire;
    }

    /// @brief update all registered entities like player, bullets, enemies, explosions
    /// @param dt is the difference between now and lats time
    /// @uses Renderer
    updateEntities(dt) {

        //--------------------------------------------------------
        // Update the player sprite animation
        this.player.sprite.update(dt);

        //--------------------------------------------------------
        // Update all the bullets
        for (let i = 0; i < this.bullets.length; i++) {
            const bullet = this.bullets[i];

            // Move the bullet
            switch (bullet.dir) {
                case 'up':
                    bullet.pos[1] -= this.incrBullet * dt;
                    break;
                case 'down':
                    bullet.pos[1] += this.incrBullet * dt;
                    break;
                default:
                    bullet.pos[0] += this.incrBullet * dt;
            }

            bullet.sprite.update(dt);

            const render = Multiple.get(Renderer.IDENT(), this.index);
            // Remove if offscreen
            if ((bullet.pos[1] < 0) ||
                (bullet.pos[1] > render.canvas.height) ||
                (bullet.pos[0] > render.canvas.width)) {
                this.bullets.splice(i, 1);
                i--;
            }
        }

        //--------------------------------------------------------
        // Update all the enemies
        for (let i = 0; i < this.enemies.length; i++) {
            const enemy = this.enemies[i];

            // Move the enemy
            enemy.pos[0] -= this.incrEnemy * dt;
            enemy.sprite.update(dt);

            // Remove if offscreen
            if (enemy.pos[0] + enemy.sprite.size[0] < 0) {
                this.enemies.splice(i, 1);
                i--;

                this.life = this.life - 20;
                if(0 >= this.life) {
                    this.end();
                    return;
                }

            }

        }

        //--------------------------------------------------------
        // Update all the explosions
        for (let i = 0; i < this.explosions.length; i++) {
            const explosion = this.explosions[i];

            // Animate the explosion
            explosion.sprite.update(dt);

            // Remove if animation is done
            if (explosion.sprite.done) {
                this.explosions.splice(i, 1);
                i--;
            }
        }
    }

    // -------------------------------------------------------------------------------------------------------------
    /// @brief limit to the player movement
    /// @uses Renderer
    checkPlayerBounds() {
        const render = Multiple.get(Renderer.IDENT(), this.index);

        if (this.player.pos[0] < 0) {
            this.player.pos[0] = 0;
        }
        else if (this.player.pos[0] > render.canvas.width - this.player.sprite.size[0]) {
            this.player.pos[0] = render.canvas.width - this.player.sprite.size[0];
        }

        if (this.player.pos[1] < 0) {
            this.player.pos[1] = 0;
        }
        else if (this.player.pos[1] > render.canvas.height - this.player.sprite.size[1]) {
            this.player.pos[1] = render.canvas.height - this.player.sprite.size[1];
        }
    }

    /// @brief Run collision detection for each ennemy
    checkEnemyCollisions() {
        for (let i = 0; i < this.enemies.length; i++) {
            const enemy = this.enemies[i];

            const poscur = enemy.pos;
            const sizecur = enemy.sprite.size;

            //----------------------------
            //Do ennemy meets a bullet?
            for (let j = 0; j < this.bullets.length; j++) {
                const bullet = this.bullets[j];

                const posother = bullet.pos;
                const sizeother = bullet.sprite.size;
                if (this.boxCollides(poscur, sizecur, posother, sizeother)) {
                    //this enemy is out
                    i = this.endEnemy(i, j, poscur);
                    i--;

                    // update score
                    this.score += this.incrScore;
                    if(100 > this.life) {
                        this.life = this.life + 1;
                    }

                    // stop this iteration then check the next enemy
                    break;
                }
            }

            //----------------------------
            //Do ennemy meets player?
            const posplayer = this.player.pos;
            const sizeplayer = this.player.sprite.size;
            if (this.boxCollides(poscur, sizecur, posplayer, sizeplayer)) {
                i = this.endEnemy(i, -1, poscur);

                this.end();
                break;
            }
        }
    }

    /// @brief check hitboxes intersections
    /// @param pos1
    /// @param size1
    /// @param pos2
    /// @param size2
    boxCollides(pos1, size1, pos2, size2) {
        return !(
            ((pos1[0] + size1[0]) <= pos2[0]) ||
             (pos1[0] > (pos2[0] + size2[0])) ||
            ((pos1[1] + size1[1]) <= pos2[1]) ||
             (pos1[1] > (pos2[1] + size2[1]))
        );
    }

    // -------------------------------------------------------------------------------------------------------------
    /// GUI layer, with DOM access

    /// @brief start everything
    /// @param something
    setup(something) {}

    /// @brief hide gameover panel
    begin() {}
    /// @brief show gameover panel. says game is over.
    end() {}

    /// @brief start everything
    /// @param something
    updatescore() {}
    /// @brief
    updatelife() {}

    // -------------------------------------------------------------------------------------------------------------
    /// ENTITIES layer

    /// @brief
    addPlayer() {}
    /// @brief
    endPlayer() {}

    /// @brief
    addEnemy() {}
    /// @brief
    /// @param i
    /// @param j
    /// @param pos
    endEnemy(i, j, pos) {}

    /// @brief
    addShot() {}
    /// @brief
    /// @param pos
    endShot(pos) {}

}

export default AEngine;