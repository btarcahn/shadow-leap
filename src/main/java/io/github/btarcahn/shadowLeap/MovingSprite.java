package io.github.btarcahn.shadowLeap;

import org.newdawn.slick.Input;

import java.util.ArrayList;

/**A Sprite that able to move (change their x, y positions) in the World.
 * There are two approaches for movements: moving by keyboard (userinput), and moving automatically (with a given speed and direction).
 * 
 * @author Bach Tran
 * @since 12 October, 2018
 */
public abstract class MovingSprite extends Sprite {
	//private static final String ASSET_PATH = "assets/bus.png";
	//private static final float SPEED = 0.15f;
	
	/**true if this object is moving right, false otherwise*/
	private boolean moveRight;
	/**speed of the MovingSprite, useful for automatic movements*/
	private float speed;
	
	private final float getInitialX() {
		return isMoveRight() ? -super.getWidth() / 2
						 : App.SCREEN_WIDTH + super.getWidth() / 2;
	}
	
	
	/**Constructor for the MovingSprite, without tags.
	 * This is useful to create human-controlled MovingSprite (e.g. Player)
	 * @param imageSrc the directory to the image file
	 * @param x starting x-position
	 * @param y starting y-position
	 * @see Sprite
	*/
	public MovingSprite(String imageSrc, float x, float y) {
		
		super(imageSrc, x, y);
	
	}
	
	/**Constructor for the MovingSprite, with tags, and no initial speed is given.
	 * This method is particularly useful for ExtraLife.
	 * @param imageSrc the directory to the image file
	 * @param x starting x-position
	 * @param y starting y-position
	 * @param tags an ArrayList<String> of tags created along with this MovingSprite.
	 * @see Sprite*/
	public MovingSprite(String imageSrc, float x, float y, ArrayList<String> tags) {
		super(imageSrc, x, y, tags);
	}
	
	/**Constructor for the MovingSprite, with tags and initial speed.
	 * This is used to create an automatically-controlled MovingSprite (e.g buses, racecars).
	 * @param imageSrc the directory to the image file
	 * @param x starting x-position
	 * @param y starting y-position
	 * @param moveRight a boolean dictates the direction
	 * @param speed initial speed
	 * @param tags an ArrayList<String> of tags created along with this MovingSprite.
	 * @see Sprite*/
	public MovingSprite(String imageSrc, float x, float y, boolean moveRight, float speed, ArrayList<String> tags) {
		
		super(imageSrc, x, y, tags);
		this.setMoveRight(moveRight);
		this.setSpeed(speed);
		
	}
	
	/**Reverses the direction of the MovingSprite by negating its movingRight attribute.*/
	public void reverseDirection() {	
		
		setMoveRight(isMoveRight() ? false : true);
	
	}
	
	@Override
	public void update(Input input, int delta) {
		move(getSpeed() * delta * (isMoveRight() ? 1 : -1), 0);
		
		// a smooth way to move vehicle off the screen
		if (getX() > App.SCREEN_WIDTH + getWidth() / 2 || getX() < -getWidth() / 2
		 || getY() > App.SCREEN_HEIGHT + getHeight() / 2 || getY() < -getHeight() / 2) {
			setX(getInitialX());
		}
		
	}
	/** @return the speed of the MovingSprite */
	public float getSpeed() {
		return speed;
	}
	/** set the speed of the MovingSprite */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	/**Checks the current direction of the MovingSprite
	 * @return true if MovingSprite is moving right, false otherwise
	*/
	public boolean isMoveRight() {
		return moveRight;
	}
	/**Sets/resets the movement of the MovingSprite
	 * @param moveRight a boolean dictates the direction
	*/
	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	
}
