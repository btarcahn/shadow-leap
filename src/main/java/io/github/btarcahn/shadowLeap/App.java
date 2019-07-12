package io.github.btarcahn.shadowLeap;

/**
 * 
 * Code developed by Bach Tran (b.tran17@student.unimelb.edu.au)
 * The University of Melbourne, 2018
 * This project is expanded on the works of
 * Eleanor McMurtry, University of Melbourne (2018)
 */


import org.newdawn.slick.*;

import java.io.File;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame {
    /** screen width, in pixels */
    public static final int SCREEN_WIDTH = 1024;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 768;
    /** First, Player starts with 3 lives */
    public static final int PLAYER_LIVES = 3;
    
    private World world;
    String[] levels = new File("assets/levels").list();
    private int currentLevel = 0;
    
    /**Constructor for app
     * */
    public App() {
        super("Shadow Leap");
    }
    
    
    @Override
    public void init(GameContainer gc) {
        world = new World("assets/levels/" + levels[currentLevel]);
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        if (world.isNextWorld()) {
        	if (currentLevel < levels.length - 1) {
	        	world = new World("assets/levels/" + levels[++currentLevel]);
	        	world.setNextWorld(false);
        	} else {
        		// congrats, you win
        		System.exit(0);
        	}
        } 
        world.update(input, delta);
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g) {
        world.render(g);
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}