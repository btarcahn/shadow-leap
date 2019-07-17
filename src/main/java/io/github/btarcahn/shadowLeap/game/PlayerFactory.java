package io.github.btarcahn.shadowLeap.game;

import io.github.btarcahn.shadowLeap.utils.Player;

class PlayerFactory implements SpriteFactory<Player> {

    // TODO generalize starting pos
    private static final String src = "assets/frog.png";
    private static final int X_START = 400;
    private static final int Y_START = 576;

    private PlayerFactory() {

    }

    private static PlayerFactory instance = new PlayerFactory();

    /**
     * Return the same Player Factory object.
     * This complies with the Singleton pattern.
     * @return one PlayerFactory
     */
    static PlayerFactory createPlayerFactory() {
        return instance;
    }

    @Override
    public Player create() {
        return new Player(src, X_START, Y_START);
    }

    @Override
    public Player create(float x_start, float y_start) {
        return new Player(src, x_start, y_start);
    }
}
