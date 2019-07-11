package io.github.btarcahn.shadowLeap;

import java.util.ArrayList;


/**WinTile is an object that's always been sitting in the frog "holes" waiting for the Player to make contact.
 * If Player hasn't made contact, the WinTile is hidden, and vice versa.
 * When the WinTile is unhidden, it means the Player has achieved that "hole" and should find another "hole" to
 * achieve. The WinTile now becomes a hazardous Tile, that it kills the Player if the Player returns to that "hole".
 * I mean come on, go find somewhere else.
 * When 5 WinTiles get unhidden, the Player is considered win the level, and the App should move to the next one,
 * or exit the game.
 * @author Bach Tran
 * @since 12 October, 2018
 *
 */
public class WinTile extends Tile implements Disappearable {
	/**Directory for the WinTile image,
	 * right now it is equal to the Player's image, this create an illuminated feeling that
	 * the Player has reached the frog "hole"
	 */
	public static final String WIN_PATH = Player.ASSET_PATH;
	
	private boolean hidden;
	
	/**Constructor for the WinTile
	 * @param x starting x-position
	 * @param y starting y-position
	 * @see Tile
	 */
	public WinTile(float x, float y) {
		super(WIN_PATH, x, y, new ArrayList<String>());
		this.hidden = true;
	} 
	
	@Override
	public void onCollision(Sprite other) {
		if (other.getClass().getSimpleName().equals("Player")) {
			if (hidden) {
				// achievement unlocked!
				this.reappear();
				this.setTags(Sprite.HAZARD);
				((Player) other).startingPosition();
			} else {
				// press F to pay respect :( 
				((Player) other).die();
			}
		}
		
	}
	
	@Override
	public void disappear() {
		this.hidden = true;
	}
	
	@Override
	public void reappear() {
		this.hidden = false;
	}
	
	@Override
	public void flipState() {
		hidden = hidden ? false : true;
	}
	
	@Override
	public void render() {
		if (!this.hidden) {
			super.render();
		}
	}
	/**Checks if this WinTile is hidden or not
	 * @return true if the WinTile is hidden*/
	public boolean isHidden() {
		// TODO Auto-generated method stub
		return this.hidden;
	}
}
