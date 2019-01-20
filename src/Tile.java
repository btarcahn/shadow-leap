import java.util.ArrayList;
import java.util.Arrays;

/**A tile is simply an "immobile" Sprite. It cannot move but still can make contact with other Sprites
 * and thereby affect them in many ways. <br/>
 * Currently: Water, Grass & Tree are of Tile class: <br/>
 * Water: not solid, and hazard (kills the Player immediately in contact) <br/>
 * Grass: not solid, not hazard <br/>
 * Tree: solid, not hazard (Player cannot move into it) <br/>
 * @author Bach Tran
 * @author Elanor McMurtry
 *
 */
public class Tile extends Sprite {
	
	/**Diretory to the image file of grass*/
	private static final String GRASS_PATH = "assets/grass.png";
	/**Directory to the image file of water*/
	private static final String WATER_PATH = "assets/water.png";
	/**Directory to the image file of tree*/
	private static final String TREE_PATH = "assets/tree.png";
	
	
	/**Lazy-method to create a grass Tile
	 * @param x x starting position
	 * @param y y starting position
	 * @return a grass Tile object
	 */
	public static Tile createGrassTile(float x, float y) {
		return new Tile(GRASS_PATH, x, y);
	}
	/**Lazy-method to create a water Tile
	 * @param x x starting position
	 * @param y y starting position
	 * @return a water Tile object
	 */
	public static Tile createWaterTile(float x, float y) {
		return new Tile(WATER_PATH, x, y, new ArrayList<String>(Arrays.asList(Sprite.HAZARD)));
	}
	/**Lazy-method to create a tree Tile
	 * @param x x starting position
	 * @param y y starting position
	 * @return a tree Tile object
	 */
	public static Tile createTreeTile(float x, float y) {
		return new Tile(TREE_PATH, x, y, new ArrayList<String>(Arrays.asList(Sprite.SOLID)));
	}
	
	/**Constructor for a Tile
	 * @param imageSrc the directory to the image file
	 * @param x starting x-position
	 * @param y starting y-position
	 * @see Sprite
	*/
	public Tile(String imageSrc, float x, float y) {		
		super(imageSrc, x, y);
	}
	/**Constructor for a Tile
	 * @see Sprite
	 * */
	public Tile(String imageSrc, float x, float y, ArrayList<String> tags) {		
		super(imageSrc, x, y, tags);
	}
	
	@Override
	public void onCollision(Sprite other) {
		// don't have to do anything... yay...
	}
}