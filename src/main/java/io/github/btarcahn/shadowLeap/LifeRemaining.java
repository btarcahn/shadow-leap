package io.github.btarcahn.shadowLeap;

import java.util.ArrayList;

/** Signifies how many lives player have left
 * @author Bach Tran
 * @since 12 October, 2018
 */

public class LifeRemaining extends Sprite {
	/** Directory to the image, which is a fancy Iron Man Arc Reactor! */
	private static final String ASSET_PATH = "assets/iron_man_arc.jpg";
	/**Default tile size of the LifeRemaining */
	public static final int TILE_SIZE = 32;
	/**Position where the LifeRemaining tile starts */
	public static final int SYMBOL_START = 24;
	/** Constructor for a LifeRemaining object
	 * @param x x-starting position
	 * @param y y-starting position
	 */
	public LifeRemaining(float x, float y) {
		super(ASSET_PATH, x, y);
	}
	/**Constructor for a LifeRemaining object
	 * @param x x-starting position
	 * @param y y-starting position
	 */
	public LifeRemaining(float x, float y, ArrayList<String> tags) {		
		super(ASSET_PATH, x, y, tags);
	}
	
	/**Lazy-method to create a LifeRemaining object
	 * @param x x-starting position
	 * @param y y-starting position
	 * @return a new LifeRemaining object */
	public static LifeRemaining addLifeRemaining(float x, float y) {
		ArrayList<String> tags = new ArrayList<String>();
		tags.add(Sprite.APPEAR);
		return new LifeRemaining(x, y, tags);
	}
	
	@Override
	public void onCollision(Sprite other) {
		
	}
}
