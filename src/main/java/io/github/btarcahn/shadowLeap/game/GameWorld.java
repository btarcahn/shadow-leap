package io.github.btarcahn.shadowLeap.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class GameWorld {

    private int width;
    private int height;

    public GameWorld(GameContainer gc) {
        this.width = gc.getScreenWidth();
        this.height = gc.getScreenHeight();
    }

    public void render(GameContainer gc, Graphics g) {

    }

}
