package io.github.btarcahn.shadowleap;

import java.util.ArrayList;
import java.util.Arrays;

/**PushyVehicle is a MovingSprite that can push other MovingSprite when in contact. <br/>
 * PushyVehicle is solid, meaning other class cannot cross the border (e.g. stand on it).
 * PushyVehicle is currently the bulldozer.
 * @author Bach Tran
 * @since 12 October, 2018
 *
 */
public class PushyVehicle  extends MovingSprite implements Pushable {
	
	/**Directory to the image file of a bulldozer*/
	public static final String BULLDOZER_ASSET = "assets/bulldozer.png";
	/**Default speed of a bulldozer*/
	public static final float BULLDOZER_SPEED = 0.05f;
	
	/**Construtor for the PushyVehicle
	 * @param imageSrc the directory to the image file
	 * @param x starting x-position
	 * @param y starting y-position
	 * @param moveRight moving direction
	 * @param speed the speed of movement
	 * @param tags all tags of the Sprite
	 * @see MovingSprite
	 */
	public PushyVehicle(String imageSrc, float x, float y, boolean moveRight, float speed, ArrayList<String> tags) {
		
		super(imageSrc, x, y, moveRight, speed, tags);
			
	}
	/**Constructor for the PushyVehicle
	 * @see MovingSprite
	 */
	public static PushyVehicle createBulldozer(float x, float y, boolean moveRight) {
		
		return new PushyVehicle(BULLDOZER_ASSET, x, y, moveRight, BULLDOZER_SPEED, new ArrayList<>(Arrays.asList(Sprite.SOLID, Sprite.PUSHY)));
	
	}
	
	@Override
	public void push(MovingSprite other) {
		
		other.setSpeed(this.getSpeed());
		other.setMoveRight(this.isMoveRight());
		
	}

	@Override
	public void onCollision(Sprite other) {
		
		if (other.getClass().getSimpleName().equals("Player")) {
			//System.out.println("Bulldoz = " + this.getX() + "Frog = " + other.getX());
			push((Player) other);
		}
	}
	
	
}
