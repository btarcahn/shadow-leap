package io.github.shdlgame;

import org.newdawn.slick.Input;

import java.util.ArrayList;
import java.util.Random;


/** ExtraLife object
 * An ExtraLife gives the Player one more life in the World, whenever it makes contact with the Player.
 * Generally, ExtraLife moves on a random log, and tries not to get out of the log.
 * @author Bach Tran
 * @see MovingSprite
 * @see Dissapearable
 */
public class ExtraLife  extends MovingSprite implements Disappearable {
	
	/** Directory to the ExtraLife image */
	public static final String EXTRA_LIFE_ASSET = "assets/extralife.png";
	/** Lowerbound for randomization */
	public static final int START_SEC = 25;
	/** Upperbound for randomization */
	public static final int END_SEC = 35;
	
	private static final float MILISEC_PER_MOVE_ON_LOG = 2000.0f;
	
	
	private boolean movingRightOnLog = true;
	private static MovingSprite onLog;
	
	private long startTime = System.currentTimeMillis();
	private long startReset = System.currentTimeMillis();
	private long startDisappear = System.currentTimeMillis();
	
//	public static final ArrayList<String> DEFAULT_TAG = new ArrayList<String>();
	
	/** Returns an ExtraLife object
	 * @param x x-starting coordinate
	 * @param y y-starting coordinate
	 * @param tags list of tags needed
	 * @see MovingObject
	*/
	public ExtraLife(String imageSrc, float x, float y, ArrayList<String> tags) {
		
		super(imageSrc, x, y, tags);
	
	}
	/** Returns an ExtraLife object
	 * @param x x-starting coordinate
	 * @param y y-starting coordinate
	 * @see MovingObject
	*/
	public ExtraLife(float x, float y) {
		super(EXTRA_LIFE_ASSET, x, y);
	}
	
	/** Automatically generate an ExtraLife by randomizing a position of a Log object.
	 * The coordinates of that ExtraLife will depend on the Log.
	 * @param logs ArrayList of all Logs in the World
	 * @see RidingVehicle
	 * */
	public static ExtraLife createExtraLife(ArrayList<Sprite> logs) {
		// choose a random log
		Random random = new Random();
		MovingSprite appearOn = (MovingSprite) logs.get(random.nextInt(logs.size()));
		
		onLog = appearOn;
		
		ArrayList<String> tags = new ArrayList<>();
		tags.add(Sprite.DISAPPEAR);
		
		float x = appearOn.getX();
		float y = appearOn.getY();
		
//		x = App.SCREEN_WIDTH / 2;
//		y = App.SCREEN_HEIGHT - 96;
		
		return new ExtraLife(EXTRA_LIFE_ASSET, x, y, tags);
	}
	
	/**Special update method for the ExtraLife
	 * @param input userinput from keyboard
	 * @param delta number of seconds passed since last frame.
	 * @param logs ArrayList<Sprite> of all logs found in the World. This is needed so the ExtraLife can randomize and choose to be appear on one of these logs.*/
	public void update(Input input, int delta, ArrayList<Sprite> logs) {
		float dx = 0,
			  dy = 0;
		
		super.update(input, delta);
		
		Random random = new Random();
		int randSec = random.nextInt((END_SEC - START_SEC) + 1) + START_SEC;
		
		long estimatedTime = System.currentTimeMillis() - startTime;
		long estimatedReset = System.currentTimeMillis() - startReset;
		long estimatedDisappear = System.currentTimeMillis() - startDisappear;
		

		if (estimatedDisappear >= (randSec + 14000.0)) {
			this.disappear();
			startDisappear = System.currentTimeMillis();
		}
		
		if (estimatedReset >= randSec * 1000.0) {
			
			
			onLog = (MovingSprite) logs.get(random.nextInt(logs.size()));
			this.setX(onLog.getX());
			this.setY(onLog.getY());
			
			
			if (this.hasTag(Sprite.DISAPPEAR)) {
				this.reappear();
			}
			
			startReset = System.currentTimeMillis();
			
		}
		
		if (estimatedTime >= MILISEC_PER_MOVE_ON_LOG) {
			
			dx = (movingRightOnLog ? 1 : -1) * this.getWidth();
			
			// create an imaginary object and see if the movement will cause it to move out of the log
			ExtraLife imaginaryLife = new ExtraLife(getX() + dx, getY());
			
			
			if (!imaginaryLife.collides(onLog)) {
				flipDirectionOnLog();
				dx = -dx;
			}
			
			this.move(dx, dy);
			super.update(input, delta);
			startTime = System.currentTimeMillis();
		}
		
		// back to normal
		this.setSpeed(0);
		
	}
	
	
	@Override
	public void onCollision(Sprite other) {
		// do nothing, nothing to be done, really.
	}
	
	@Override
	public void disappear() {
		this.setTags(Sprite.DISAPPEAR);
	}
	
	@Override
	public void reappear() {
		this.removeTags(Sprite.DISAPPEAR);
		startDisappear = System.currentTimeMillis();
	}
	
	@Override
	public void flipState() {
		if (this.hasTag(Sprite.DISAPPEAR)) {
			this.reappear();
		} else {
			this.disappear();
		}
	}
	
	/** Flips the direction of the ExtraLife while moving on the log.
	 * This is used to avoid the ExtraLife to move out of the log.*/
	public void flipDirectionOnLog() {
		movingRightOnLog = (movingRightOnLog ? false : true);
	}
	

}
