export const app_images = [
    './img/sprites.png',
    './img/terrain.png'
];

export const app_settings = {
    // for Renderer
    width: 800,
    height: 400,

    // for Factory
    // Speed are in pixels per second
    speedPlayer: 16,
    speedEnemy: 6,
    speedExplosion: 6,

    // for Engine
    incrPlayer: 200,
    incrBullet: 500,
    incrEnemy: 150,
    incrScore: 100,

    threshFire: 100

};

export const app_guimap = {
    // for Engine
    elemscoreid: 'score',
    elemplayagainid: 'play-again',
    elemgameoverid: 'game-over',
    elemgameoverlayid: 'game-over-overlay',
    elemlifebarid: 'lifebar'

};
