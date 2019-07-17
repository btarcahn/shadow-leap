package io.github.btarcahn.shadowLeap.game;

import io.github.btarcahn.shadowLeap.utils.Interactable;
import io.github.btarcahn.shadowLeap.utils.Player;
import io.github.btarcahn.shadowLeap.utils.Sprite;

public class Factory {

    private static final String PLAYER = "assets/frog.png";
    private static final String WATER = "assets/water.png";

    public static Player createPlayer(float init_x, float init_y) {
        return new Player(PLAYER, init_x, init_y);
    }

    public static Sprite createWater(float init_x, float init_y) {
        return new Sprite(WATER, init_x, init_y) {

            @Override
            public void reaction(Interactable obj) {
                // kills player
            }
        };
    }
}