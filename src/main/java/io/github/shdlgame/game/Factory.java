package io.github.shdlgame.game;

import io.github.shdlgame.utils.Interactable;
import io.github.shdlgame.utils.MovingSprite;
import io.github.shdlgame.utils.Player;
import io.github.shdlgame.utils.Sprite;

/**
 * Static methods, creating Sprites for the game.
 * @author Bach Tran
 * @since 2.0
 */
class Factory {

    private static final String PLAYER = "assets/frog.png";
    private static final String WATER = "assets/water.png";
    private static final String BIKE = "assets/bike.png";
    private static final String FINISH = "assets/matt.jpg";

    static Player createPlayer() {
        return new Player(PLAYER, 400.0f, 576.0f);
    }

    static Sprite createWater() {
        return new Sprite(WATER, 480.0f, 480.0f) {

            @Override
            public void reaction(Interactable obj) {
                if (obj instanceof Player) {
                    ((Player) obj).decLife();
                }
            }
        };
    }

    static MovingSprite createBike() {

        MovingSprite bike = new MovingSprite(BIKE, 480.0f, 480.0f) {

            @Override
            public void onBorder() {
                this.negateDir();
            }
        };

        bike.setSpeed(0.15f);
        bike.setDir(MovingSprite.Directions.RIGHT);

        return bike;
    }

    static Sprite createFinishTile(float init_x, float init_y) {
        return new Sprite(FINISH, init_x, init_y) {

            @Override
            public void reaction(Interactable obj) {
                // TODO winning behavior
            }
        };
    }

}