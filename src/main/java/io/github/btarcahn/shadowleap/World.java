package io.github.btarcahn.shadowleap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


/** The World contains all Sprites and their movements in the Game.
 * The World can takes a *.lvl file as direction and construct all Sprites based on that direction.
 * @author Bach Tran
 * @author Elenor McMurtry
 */
public class World {
	/** Number of "frog holes" needed to win the level.*/
	public static final int MAX_ACHIEVEMENTS = 5;
	/** Default size of a normal Sprite (Tile), which is generally 48 pixels. */
	public static final int TILE_SIZE = 48;
	/** The x-coorinates where the LifeRemaining object starts to be rendered */
	public static final int LIVES_START = 24;
	/** All x coordinates that the WinTitle should be rendered */
	public static final int[] WIN_TILE_XPOS = {120, 312, 504, 696, 888};
	/** The y coordinates of the WinTitle */
	public static final int WIN_TILE_YPOS = 48;
	

	private ArrayList<Sprite> sprites = new ArrayList<>();
	private ArrayList<Sprite> solids = new ArrayList<>();
	private ArrayList<Sprite> logs = new ArrayList<>();
	private ArrayList<WinTile> achievements = new ArrayList<>();
	
	/** Check if the nextWorld should be called. Default value is false*/
	private boolean nextWorld = false;
	
	/**
	 * Returns a World object that initialise all positions of the Sprite
	 * @param levelSrc a String contains the directory of the *.lvl file
	*/
	public World(String levelSrc) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(levelSrc))) {

			String text;

			while ((text = br.readLine()) != null) {
				// read the level information and initialise sprites
				String[] information = text.split(",");
				int x = Integer.parseInt(information[1]);
				int y = Integer.parseInt(information[2]);
				boolean moveRight = true;
				// I don't like to hard code, but...
				if (information.length == 4) {
					moveRight = Boolean.parseBoolean(information[3]);
				}
				
				switch (information[0]) {
				
				case "water":
					sprites.add(Tile.createWaterTile(x, y));
					break;
				
				case "grass":
					sprites.add(Tile.createGrassTile(x, y));
					break;
				
				case "tree":
					sprites.add(Tile.createTreeTile(x, y));
					break;
				
				case "bus":
					sprites.add(HazardVehicle.createBus(x, y, moveRight));
					break;
					
				case "bike":
					// note: the bike hasn't reached the border yet
					sprites.add(BouncyVehicle.createBike(x, y, moveRight));
					break;
				
				case "racecar":
					sprites.add(HazardVehicle.createRacecar(x, y, moveRight));
					break;
				
				case "bulldozer":
					sprites.add(PushyVehicle.createBulldozer(x, y, moveRight));
					break;
					
				case "log":
					sprites.add(RidingVehicle.createLog(x, y, moveRight));
					break;
				
				case "longLog":
					sprites.add(RidingVehicle.createLongLog(x, y, moveRight));
					break;
					
				case "turtle":
					sprites.add(Turtle.createTurtle(x, y, moveRight));
					break;
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// create achievements (WinTile) in the World
		for (int i = 0; i < MAX_ACHIEVEMENTS; i++) {
			WinTile achievement = new WinTile(WIN_TILE_XPOS[i], WIN_TILE_YPOS);
			sprites.add(achievement);
			achievements.add(achievement);
			
		}
		
		for (Sprite sprite : sprites) {
			// Keep track of all solid sprites
			if (sprite.hasTag(Sprite.SOLID)) {
				solids.add(sprite);
			}
			// Keep track of all logs
			if (sprite.getClass().getSimpleName().equals("RidingVehicle")) {
				logs.add(sprite);
			}
		}
		
		// finally, add ExtraLife
		sprites.add(ExtraLife.createExtraLife(logs));
		
		
		// create player
		Player player = new Player(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT - TILE_SIZE);
		
		sprites.add(player);
		
	}
	
	/** Update positions, statusses of all Sprites in the World
	 *  @param input an Input object from keyboard
	 *  @param delta number of seconds since the previous frame
	 *  @see World
	 *  @see Input
	*/
	public void update(Input input, int delta) {
		int count = 0;
		// update all solid sprites first
		for (Sprite sprite : solids) {
			sprite.update(input, delta);
		}
		for (WinTile achievement : achievements) {
			if (!achievement.isHidden()) {
				count++;
			}
			if (count == MAX_ACHIEVEMENTS) {
				nextWorld = true;
			}
		}
		for (Sprite sprite : sprites) {
			// update the player
			if (sprite.getClass().getSimpleName().equals("Player")) {
				((Player) sprite).update(input, delta, solids);
			// update ExtraLife
			} else if (sprite.getClass().getSimpleName().equals("ExtraLife")) {
				((ExtraLife) sprite).update(input, delta, logs);
			}
			
			else if (!sprite.hasTag(Sprite.SOLID)){
			// update the rest
				sprite.update(input, delta);
			}	
		}
		
		// loop over all pairs of sprites and test for intersection
		for (Sprite sprite1: sprites) {
			for (Sprite sprite2: sprites) {
				if (sprite1 != sprite2
						&& sprite1.collides(sprite2)) {
					//System.out.println(sprite1.getTags() + "," + sprite2.getTags());
					sprite1.onCollision(sprite2);
				}
			}
		}
	}
	
	/** Render the whole world onto the screen
	 * @param g Graphics object to render
	 * @see Graphics
	*/
	public void render(Graphics g) {
		for (Sprite sprite : sprites) {
			if (!sprite.hasTag(Sprite.DISAPPEAR)) {
				sprite.render();
			}
		}
	}
	
	/** Add a Sprite to the sprites ArrayList
	 * @param sprite The sprite to be added
	 * @see Sprite
	 * @see ArrayList
	 * */
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}
	
	/** Check if the next World should be moved, this method will call the App to move to the next level
	 * 
	 * */
	public boolean isNextWorld() {
		return nextWorld;
	}
	
	/** Setter for the nextWorld variable
	 * */
	public void setNextWorld(boolean nextWorld) {
		this.nextWorld = nextWorld;
	}
	
}
