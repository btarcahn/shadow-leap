package io.github.btarcahn.shadowleap;

import java.util.ArrayList;
import java.util.Arrays;

/** HazardVehicle is a MovingSprite that immediately kills the Player in contact.
 * When touches border of the screen, HazardVehicle disappear and reappear on the other side of the screen.
 * @author Bach Tran
 * @since 12 October, 2018
 */
public class HazardVehicle extends MovingSprite {
	
	/** Directory to the bus image file */
	public static final String BUS_ASSET = "assets/bus.png";
	/** Directory to the racecar image file */
	public static final String RACECAR_ASSET = "assets/racecar.png";
	
	/** Speed of the bus */
	public static final float BUS_SPEED = 0.15f;
	/** Speed of the racecar */
	public static final float RACECAR_SPEED = 0.5f;
	
	/** Default tag of the HazardVehicle.
	 * HazardVehicle always has the "hazard" tag on it, this allows it to kill the Player when in contact.*/
	public static final ArrayList<String> DEFAULT_TAG = new ArrayList<String>(Arrays.asList(Sprite.HAZARD));
	
	/**Constructor for the HazardVehicle.
	 * By default, this constructor automatically add the "hazard" tag to the HazardVehicle.
	 * @param imageSrc a String contains the directory to the image file.
	 * @param x starting x-position
	 * @param y starting y-position
	 * @param speed moving speed of the HazardVehicle
	 * @see MovingSprite
	 * */
	public HazardVehicle(String imageSrc, float x, float y, boolean moveRight, float speed) {
		
		// automatically assign the "hazard" tag to HazardVehicle
		super(imageSrc, x, y, moveRight, speed, DEFAULT_TAG);

	}
	
	/**Lazy-method to create a bus, one of the HazardVehicle.
	 * @param x starting x-position
	 * @param y starting y-position
	 * @param moveRight set this to true if the bus moves right, false otherwise
	 */
	public static HazardVehicle createBus(float x, float y, boolean moveRight) {
		
		return new HazardVehicle(BUS_ASSET, x, y, moveRight, BUS_SPEED); 
	
	}
	
	/**Lazy-method to create a racecar, one of the HazardVehicle.
	 * @param x starting x-position
	 * @param y starting y-position
	 * @param moveRight set this to true if the racecar moves right, false otherwise
	 */
	public static HazardVehicle createRacecar(float x, float y, boolean moveRight) {
		
		return new HazardVehicle(RACECAR_ASSET, x, y, moveRight, RACECAR_SPEED);
	
	}
	
	@Override
	public void onCollision(Sprite other) {
		// do nothing
	}
	
}