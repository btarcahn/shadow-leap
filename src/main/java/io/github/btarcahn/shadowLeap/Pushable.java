package io.github.btarcahn.shadowLeap;

/**Interface class that grants a MovingSprite the ability to push other MovingSprite.
 * @author Tran
 * @since 12 October, 2018
 *
 */
public interface Pushable {
	
	/**Pushes other Sprite
	 * @param other a MovingSprite that will be pushed by this MovingSprite.*/
	public abstract void push(MovingSprite other);
	
}
