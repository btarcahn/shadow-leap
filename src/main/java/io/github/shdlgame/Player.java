package io.github.shdlgame;

import org.newdawn.slick.Input;

import java.util.ArrayList;


/**The Player class is the main character of the game. 
 * It can be controlled by human (the player) via the keyboard, and automatically moved when hopping on a RidingVehicle, or getting crushed by a PushyVehicle.
 * Generally, the Player wins when passing all available levels, or loses when running out of lives. In both cases, the game ends.
 * @author Bach Tran
 * @author Elanor McMurtry
 * @since 12 October, 2018
 *
 */
public class Player extends MovingSprite {
	
	/**Directory to the image file of the Player*/
	public static final String ASSET_PATH = "assets/frog.png";
	
	ArrayList<LifeRemaining> lifeSymbols = new ArrayList<>();
	ArrayList<Tile> achievementSymbols = new ArrayList<>();
	
	/** Player lives is dictated by the World, but default is 3 */
	private int lives = 3;
	/** Protection: if this variable is true, the Player can't be killed when contacting with a HazardVehicle.
	 * @see HazardVehicle*/
	private boolean protection = false;
	
	/**Constructor for a Player.
	 * This will create the Player and its LifeRemaining symbols.
	 * @param x x-starting position
	 * @param y y-starting position
	 * @see LifeRemaining
	 */
	public Player(float x, float y) {
		super(ASSET_PATH, x, y);
		
		// create life symbols
		for (int i = 0; i < lives; i++) {
			lifeSymbols.add(new LifeRemaining(LifeRemaining.SYMBOL_START + LifeRemaining.TILE_SIZE * i, App.SCREEN_HEIGHT - LifeRemaining.TILE_SIZE));
		}
		
	}
	
	/*
	 * Getters and setters
	 * 
	 */
	/**@return number of lives that the Player currently has*/
	public int getLives() {
		return lives;
	}
	/** Sets the desired number of lives for the pl..ayer
	 * @param lives an int contains the desired number of lives.
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
	/**Increases the number of lives by one*/
	public void increaseLives() {
		this.lives++;
	}
	/**Changes the protection status
	 * @param protection a boolean contains the desired status.
	*/
	public void setProtection(boolean protection) {
		this.protection = protection;
	}
	
	/**Special: this method makes sure a player can't go off the screen.*/
	@Override
	public void setX(float x) {
		// this is why setters are better
		if (onScreen(x, this.getY())) {
			super.setX(x);
		}
		
	}
	/**Special: this method makes sure a player can't go off the screen.*/
	@Override
	public void setY(float y) {
		if (onScreen(this.getX(), y)) {
			super.setY(y);
		}
	}
		
//	@Override
//	public void update(Input input, int delta) {
//		float dx = 0, 
//			  dy = 0;
//		
//		super.update(input, delta);
//		
//		if (input.isKeyPressed(Input.KEY_LEFT)) {
//			dx -= World.TILE_SIZE;
//		}
//		if (input.isKeyPressed(Input.KEY_RIGHT)) {
//			dx += World.TILE_SIZE;
//		}
//		if (input.isKeyPressed(Input.KEY_DOWN)) {
//			dy += World.TILE_SIZE;
//		}
//		if (input.isKeyPressed(Input.KEY_UP)) {
//			dy -= World.TILE_SIZE;
//		}
//		
////		// make sure the Sprite stays on screen
////		if (getX() + dx - World.TILE_SIZE / 2 < 0 || getX() + dx + World.TILE_SIZE / 2 	> App.SCREEN_WIDTH) {
////			dx = 0;
////		}
////		if (getY() + dy - World.TILE_SIZE / 2 < 0 || getY() + dy + World.TILE_SIZE / 2 > App.SCREEN_HEIGHT) {
////			dy = 0;
////		}
//		
//		
//		move(dx, dy);
//		// Going back to normal state
//		this.setSpeed(0);
//		protection = false;
//	}
	
	/**Special update method for the Player class.
	 * This method allows user to take control the Player through the keyboard,
	 * but also allows other classes (RidingVehicle, PushyVehicle) to control the movements as well.
	 * @param input userinput from keyboard to control the movements of the Player
	 * @param delta number of seconds passed from previous frame
	 * @param obstacles an ArrayList<Sprite> of all obstacles that the Player should avoid.
	 */
	public void update(Input input, int delta, ArrayList<Sprite> obstacles) {
		float dx = 0, 
			  dy = 0;
			
		super.update(input, delta);
		
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dx -= World.TILE_SIZE;
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dx += World.TILE_SIZE;
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			dy += World.TILE_SIZE;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			dy -= World.TILE_SIZE;
		}
		// Get an imaginary sprite and restrict the movement of the player if the imaginary sprite collides with an obstacle
		Sprite imaginarySprite = new Player(getX() + dx, getY() + dy);
		
		for (Sprite obstacle : obstacles) {
			if (imaginarySprite.collides(obstacle)) {
				// this has to be on the same rows
				if (this.getY() == obstacle.getY()) {
//					if (dx > 0) {
//						float destination = obstacle.getX() - (((MovingSprite)obstacle).isMoveRight() ? 1 : -1) * (obstacle.getWidth() + this.getWidth()) / 2;
//						dx = destination - getX();
//					} else {
//						float destination = obstacle.getX() + ((obstacle.getWidth() + this.getWidth())) / 2;
//						dx = destination - getX() - 3.0f;
//					}
					// float destination = obstacle.getX() + (dx > 0 ? -1 : 1) * (((MovingSprite)obstacle).isMoveRight() ? 1 : -1) * (obstacle.getWidth() + this.getWidth()) / 2;
					
					// this combined expression is equivalent to the commented-out block above
					dx = obstacle.getX() + (dx > 0 ? -1 : 1) * (((MovingSprite)obstacle).isMoveRight() ? 1 : -1) * (obstacle.getWidth() + this.getWidth()) / 2 - getX() - 3.0f;
			}
				dy = 0;
			}
		}
		// next position is verified, let's make a move!
		move(dx, dy);
		// Set the player back to normal state
		this.setSpeed(0);
		protection = false;
	}
	
	
	@Override
	public void render() {
		super.render();
		
		for (LifeRemaining lifeSymbol : lifeSymbols) {
			lifeSymbol.render();
		}
		
		for (Tile achievementSymbol : achievementSymbols) {
			achievementSymbol.render();
		}
	}
	
	/**The player loses a life when it hits a HazardVehicle. <br/>
	 * The player gains a life when it hits an ExtraLife.*/
	@Override
	public void onCollision(Sprite other) {
		
		// I will be dead when I got hit by a hazard stuff and I'm not riding anything
		if ((other.hasTag(HAZARD) && !protection) ||
				(other.hasTag(PUSHY) && !this.onScreen(getX() + (((MovingSprite) other).isMoveRight() ? 1 : -1), getY()))){
			this.die();
		}
		
		// Gain an ExtraLife when in contact with ExtraLife object
		if (other.getClass().getSimpleName().equals("ExtraLife")) {
			lifeSymbols.add(new LifeRemaining(LifeRemaining.SYMBOL_START + LifeRemaining.TILE_SIZE * (++this.lives - 1), App.SCREEN_HEIGHT - LifeRemaining.TILE_SIZE));
			((ExtraLife) other).disappear();
		}
	
	}
	
	/**Puts the player back to its starting position.*/
	public void startingPosition() {
		
		this.setX(App.SCREEN_WIDTH / 2);
		this.setY(App.SCREEN_HEIGHT - this.getHeight());
		
	}
	
	// haha ya dumb
	public void die() {
		if (--lives < 0) {
			// Game over
			System.exit(0);
		} else {
			// Phewww, still have more lives to spare
			lifeSymbols.remove(lives);
			this.startingPosition();
		}
	}
}
