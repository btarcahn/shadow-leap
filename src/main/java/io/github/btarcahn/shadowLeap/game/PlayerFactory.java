package io.github.btarcahn.shadowLeap.game;

import io.github.btarcahn.shadowLeap.utils.Player;

class PlayerFactory implements Factory<Player> {

    // TODO generalize starting pos
    private static final String src = "assets/frog.png";
    private static final int X_START = 400;
    private static final int Y_START = 576;

    private PlayerFactory() {

    }

    private static PlayerFactory instance = new PlayerFactory();

    /**
     *
     * @return one PlayerFactory
     */
    public static PlayerFactory createPlayerFactory() {
        return instance;
    }

    @Override
    public Player create() {
        return new Player(src, X_START, Y_START);
    }
}
