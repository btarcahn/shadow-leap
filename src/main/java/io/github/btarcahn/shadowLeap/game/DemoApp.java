package io.github.btarcahn.shadowLeap.game;

import io.github.btarcahn.shadowLeap.App;
import org.newdawn.slick.*;

public class DemoApp extends BasicGame {

    private static final int SCREEN_WIDTH = 1024;
    private static final int SCREEN_HEIGHT = 768;

    private DemoWorld world = new DemoWorld();

    /**
     * Create a new basic game
     *
     * @param title The title for the game
     */
    public DemoApp(String title) {
        super("Demo game");
    }

    @Override
    public void init(GameContainer container) throws SlickException {

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        world.update(container, delta);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        world.render(g);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }
}
