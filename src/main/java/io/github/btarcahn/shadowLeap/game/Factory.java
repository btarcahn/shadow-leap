package io.github.btarcahn.shadowLeap.game;

import io.github.btarcahn.shadowLeap.utils.Interactable;
import io.github.btarcahn.shadowLeap.utils.MovingSprite;
import io.github.btarcahn.shadowLeap.utils.Player;
import io.github.btarcahn.shadowLeap.utils.Sprite;

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

    static Player createPlayer(float init_x, float init_y) {
        return new Player(PLAYER, init_x, init_y);
    }

    static Sprite createWater(float init_x, float init_y) {
        return new Sprite(WATER, init_x, init_y) {

            @Override
            public void reaction(Interactable obj) {
                // kills player
            }
        };
    }

    static MovingSprite createBike(float init_x, float init_y) {

        MovingSprite bike = new MovingSprite(BIKE, init_x, init_y) {

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