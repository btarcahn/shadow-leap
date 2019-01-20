import java.util.ArrayList;
import java.util.Arrays;

/**RidingVehicle class. Important: this is NOT a solid class, meaning any other class can "ride" on it (crossing its borders).
 * RidingVehicle pushes other objects, if they are of class Player or ExtraLife.
 * @author Bach Tran
 * @since 12 October, 2018
 *
 */
public class RidingVehicle extends MovingSprite implements Pushable {
	public static final String LOG_ASSET = "assets/log.png";
	public static final String LONGLOG_ASSET = "assets/longlog.png";
	
	public static final float LOG_SPEED = 0.1f;
	public static final float LONGLOG_SPEED = 0.07f;
	/**Constructor for RidingVehicle
	 * @param imageSrc directory to the image file
	 * @param x x-starting position
	 * @param y y-starting position
	 * @param moveRight moving direction
	 * @param speed speed of movement
	 * @tags all tags that the Sprite can have
	 * @see MovingSprite
	 * */
	public RidingVehicle(String imageSrc, float x, float y, boolean moveRight, float speed, ArrayList<String> tags) {
		super(imageSrc, x, y, moveRight, speed, tags);
	}
	/**Lazy-method to create a short log 
	 *@param x x starting position
	 *@param y y starting position
	 *@param moveRight direction of the object
	 *@return a new log
	 */
	public static RidingVehicle createLog(float x, float y, boolean moveRight) {
		return new RidingVehicle(LOG_ASSET, x, y, moveRight, LOG_SPEED, new ArrayList<>(Arrays.asList(Sprite.RIDE)));
	}
	/**Lazy-method to create a long log 
	 *@param x x starting position
	 *@param y y starting position
	 *@param moveRight direction of the object
	 *@return a new LongLog
	 */
	public static RidingVehicle createLongLog(float x, float y, boolean moveRight) {
		return new RidingVehicle(LONGLOG_ASSET, x, y, moveRight, LONGLOG_SPEED, new ArrayList<>(Arrays.asList(Sprite.RIDE)));
	}
	
	
	@Override
	public void push(MovingSprite other) {
		// protect if this is a player
		if (other.getClass().getName().equals("Player")) {
			((Player) other).setProtection(true);
		}
		
		// simply set the speed of the MovingSprite, allowing it to move along with this object
		other.setSpeed(this.getSpeed());
		other.setMoveRight(this.isMoveRight());
		
	}
	
	@Override
	public void onCollision(Sprite other) {
		if (other.getClass().getSimpleName().equals("Player")
				|| other.getClass().getSimpleName().equals("ExtraLife")) {
			// then push
			push((MovingSprite) other);
		}
	}
	
}
