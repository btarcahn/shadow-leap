package io.github.btarcahn.shadowleap;

import org.newdawn.slick.Input;


/**BouncyVehicle is a HazardVehicle that bounce back when it hits the screen, unlike a HazardVehicle that appears on the other side.
 * Note that this type of Vehicle is still hazardous, meaning it still kills the player immediately in-contact.
 * @author Bach Tran (b.tran17@student.unimelb.edu.au)
 */
public class BouncyVehicle extends HazardVehicle {
	
	/** The directory to the image file of the bike */
	public static final String BIKE_ASSET = "assets/bike.png";
	/** The default speed of the bike */
	public static final float BIKE_SPEED = 0.12f;
	
	/**Constructor for the BouncyVehicle.
	 * @param imageSrc the directory to the image file
	 * @param x starting x-position
	 * @param y starting y-position
	 * @param moveRight moving direction
	 * @param speed the speed of movement
	 * @see HazardVehicle
	 * */
	public BouncyVehicle(String imageSrc, float x, float y, boolean moveRight, float speed) {
		
		// note that the constructer has already included the "hazard" tag (inheritance from HazardVehicle)
		super(imageSrc, x, y, moveRight, speed);
	
	}
	
	/**Lazy-method to automatically create a bike
	 * @param x starting x-position of the bike.
	 * @param y starting y-position of the bike.
	 * @param moveRight a boolean to dictate the bike should move right or not.*/
	public static BouncyVehicle createBike(float x, float y, boolean moveRight) {
		
		return new BouncyVehicle(BIKE_ASSET, x, y, moveRight, BIKE_SPEED);
	}
	
	@Override
	public void update(Input input, int delta) {
		
		move(getSpeed() * delta * (isMoveRight() ? 1 : -1), 0);
		
		if (!this.onScreen()) {
			// ya gonna bounce back
			this.reverseDirection();
		}
		
	}
}
