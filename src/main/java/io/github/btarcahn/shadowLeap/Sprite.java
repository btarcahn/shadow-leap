package io.github.btarcahn.shadowLeap;

import io.github.btarcahn.shadowLeap.utils.BoundingBox;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * The most basic element in the World.
 * This is generally the unit in charge
 * of movements & collisions.
 * @author Bach Tran
 * @author Elanor McMurtry
 * @deprecated extensive refactoring in progress.
 */
public abstract class Sprite {
	/** The string "hazard", to prevent typos. This signifies a hazard Sprite that kills Player immediately in-contact.*/
	public final static String HAZARD = "hazard";
	/** The string "solid", to prevent typos. This signifies a solid Sprite.*/
	public final static String SOLID = "solid";
	/** The string "ride", to prevent typos. This signifies the Sprite is Rideable.*/
	public final static String RIDE = "ride";
	/** The string "appear", to prevent typos. This signifies the Sprite is available.*/
	public final static String APPEAR = "appear"
	/** The string "disappear", to prevent typos. This signifies the Sprite is not available.*/;
	public final static String DISAPPEAR = "disappear";
	/** The string "pushy", to prevent typos. This signifies the Pushable ability.*/
	public final static String PUSHY = "pushy";
	
	
	private BoundingBox bounds;
	private Image image;
	private float x;
	private float y;
	
	private ArrayList<String> tags = new ArrayList<>();
	
	/** Constructor for a Sprite. Use this if a Sprite should be created without any tags.
	 * @param imageSrc a String contains the directory of the image to be rendered.
	 * @param x a float of x-coordinate of the Sprite.
	 * @param y a float of y-coordinate of the Sprite.
	 */
	public Sprite(String imageSrc, float x, float y) {
		setupSprite(imageSrc, x, y);
	}
	
	/** Constructor for a Sprite with a tags. Use this if a Sprite should be created with tags.
	 * @param imageSrc a String contains the directory of the image to be rendered.
	 * @param x a float of x-coordinate of the Sprite.
	 * @param y a float of y-coordinate of the Sprite.
	 * @param tags an ArrayList<String> contains all tags of the Sprite.
	 */
	public Sprite(String imageSrc, float x, float y, ArrayList<String> tags) {
		setupSprite(imageSrc, x, y);
		this.setTags(tags);
	}
	
	/** Setup the coordinates of the Sprite, and its BoundingBox.
	 * @param imageSrc a String contains the directory of the image to be rendered.
	 * @param x a float of x-coordinate of the Sprite.
	 * @param y a float of y-coordinate of the Sprite.
	 */
	private void setupSprite(String imageSrc, float x, float y) {
		try {
			image = new Image(imageSrc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;
		
		bounds = new BoundingBox(image, x, y);
	}

	/**
	 * Sets the x position of the Sprite.
	 * @param x	 the desired x position
	 */
	public void setX(float x) {
		this.x = x; 
		bounds.setX((int)x);
	}
	
	/**
	 * Sets the y position of the Sprite.
	 * @param y	the desired y position
	 */
	public void setY(float y) {
		this.y = y; 
		bounds.setY((int)y);
	}
	
	/**
	 * Accesses the x position of the sprite.
	 * @return	the x position of the sprite
	 */
	public final float getX() { return x; }
	/**
	 * Accesses the y position of the sprite.
	 * @return	the y position of the sprite
	 */
	public final float getY() { return y; }
	
	// Moves the Sprite (dx, dy) pixels
	public final void move(float dx, float dy) {
		setX(x + dx);
		setY(y + dy);
	}
	
	
	/** Calculate and return the width (based on the image) of the Sprite.
	 * @return an int as a width of the Sprite.
	 * */
	public int getWidth() {
		return this.image.getWidth();
	}
	
	/** Calculate and return the height (based on the image) of the Sprite.
	 * @return an int as a height of the Sprite.
	 * */
	public int getHeight() {
		return this.image.getHeight();
	}
	
	/*
	 * SCREEN METHODS
	 * RELATIONSHIPS OF THE SPRITE WITH THE SCREEN
	 */
	/**Checks if the Sprite (with its current x-y coordinates) is on the screen.
	 * @param x current x-position of the Sprite.
	 * @param y current y-position of the Sprite.
	 * @return true if the Sprite is on the screen. 
	 */
	public final boolean onScreen(float x, float y) {
		return !(x + getWidth() / 2 > App.SCREEN_WIDTH || x - getWidth() / 2 < 0
			 || y + getHeight() / 2 > App.SCREEN_HEIGHT || y - getHeight() / 2 < 0);
	}
	
	/**Checks if the Sprite is on the screen. This method automatically get the coordinates of the Sprite.
	 * @return true if the Sprite is on the screen. 
	 */
	public final boolean onScreen() {
		return onScreen(getX(), getY());
	}
	
	/*
	 * OTHER SPRITE METHODS
	 * RELATIONSHIPS OF THIS SPRITE WITH ANOTHER SPRITE
	 * */
	
	/** Checks if this Sprite is colliding with another Sprite
	 * @param other another Sprite that should be checked with this Sprite.
	 * @return true if the two Sprites are in collision.
	 */
	public final boolean collides(Sprite other) {
		return bounds.interacts(other.bounds) && !this.hasTag(DISAPPEAR) && !other.hasTag(DISAPPEAR);
	}
	
	/** Actions to be taken if this Sprite collides with other Sprite.
	 * Currently an abstract method, detailed will be judged by each Sprite. 
	 * @param other the other Sprite that should be checked collision with this Sprite.*/
	public abstract void onCollision(Sprite other);

	/*
	 * SLICK-RELATED METHODS
	 * */
	/** Update the Sprite's position and status.
	 * @param input Input from the keyboard (if neccessary).
	 * @param delta number of second passed since the last frame.
	  */
	public void update(Input input, int delta) {
		// do nothing...
	}
	
	
	/**Render the image of the Sprite onto the screen.
	 * Simply this is a drawCentered method.
	 * @see Image
	 */
	public void render() {
		image.drawCentered(getX(), getY());
	}
	
	/* 
	 * TAGS METHODS 
	 * */
	
	/**Checks if the Sprite has any tag given.
	 * @param tag a String contains the tag needed to be searched.
	 * @return true if the given tag is present in this Sprite.
	 */
	public boolean hasTag(String tag) {
		for (String test : getTags()) {
			if (tag.equals(test)) {
				return true;
			}
		}
		return false;
	}
	
	/**Returns all tags that this Sprite has in the form of an ArrayList<String>
	 * @return an ArrayList<String> of all tags present in this Sprite.
	 */
	public ArrayList<String> getTags() {
		return tags;
	}
	
	/**Sets the tags of the Sprite.
	 * This is useful if user needs to initialise the Sprite's tags for the first time.
	 * @param tags an ArrayList<String> of desired tags
	*/
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	
	/**Adds an extra tag to the currently-existed ArrayList<String> of tags in the Sprite.
	 * This is useful if the Sprite has already has its tags, and user want to add an extra tag.
	 * @param tag a String contain a single desired tag.*/
	public void setTags(String tag) {
		this.tags.add(tag);
	}
	
	/**Remove all tags that have the given value in the list of tags.
	 * @param tag a String contains the tag-value that should be removed.*/
	public void removeTags(String tag) {
		this.tags.removeAll(Arrays.asList(tag));
	}
}
