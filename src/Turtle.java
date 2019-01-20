import java.util.ArrayList;
import java.util.Arrays;


import org.newdawn.slick.Input;


/**Turtle class. A special thing about the Turtle is that it disappears/reappears every 20 seconds.
 * Other than that, a Turtle is a RidingVehicle
 * @author Bach Tran
 * @since 12 October, 2018
 * @see RidingVehicle
 */
public class Turtle extends RidingVehicle implements Disappearable{
	
	/**Directory to the image file of a turtle*/
	public static final String TURTLE_ASSET = "assets/turtles.png";
	/**Default spee of a turtle*/
	public static final float TURTLE_SPEED = 0.085f;
	/**Default miliseconds for an appear/disappear cycle*/
	public static final float MILISEC_DISAPPEAR_CYCLE = 20000.0f;

	private long startTime = System.currentTimeMillis();
	
	/**Constructor of a turtle
	 * @param imageSrc directory to the image
	 * @param x x-starting position
	 * @param y y-starting position
	 * @param moveRight the movement direction
	 * @param tags all tags the Tile should have
	 */
	public Turtle(String imageSrc, float x, float y, boolean moveRight, float speed, ArrayList<String> tags) {
		super(imageSrc, x, y, moveRight, speed, tags);
	}
	
	/**Lazy-method to create a Turtle object.
	 * @param x starting x-position
	 * @param y starting y-position
	 * @param moveRight direction of the Turtle
	 * @return a Turtle object
	 */
	public static Turtle createTurtle(float x, float y, boolean moveRight) {
		return new Turtle(TURTLE_ASSET, x, y, moveRight, TURTLE_SPEED, new ArrayList<>(Arrays.asList(Sprite.RIDE)));
	}
	
	/**Special: every 20 seconds a turtle will appear or disappear.*/
	@Override 
	public void update(Input input, int delta) {
		
		super.update(input, delta);
		
		long estimatedTime = System.currentTimeMillis() - startTime;
		if (estimatedTime >= MILISEC_DISAPPEAR_CYCLE) {
			this.flipState();
			super.update(input, delta);
			startTime = System.currentTimeMillis();
		}

		
	}
	
	@Override
	public void disappear() {
		this.setTags(Sprite.DISAPPEAR);
	}
	
	@Override
	public void reappear() {
		this.removeTags(Sprite.DISAPPEAR);
	}
	
	@Override
	public void flipState() {
		if (this.hasTag(Sprite.DISAPPEAR)) {
			this.reappear();
		} else {
			this.disappear();
		}
	}
	
}
