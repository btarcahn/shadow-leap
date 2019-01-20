
/**Interface that given a Sprite an ability to disappear.
 * @author Bach Tran
 * @since 12 October, 2018
 */
public interface Disappearable {
	/**Default disappear tag of the Sprite. */
	public static final String DISAPPEAR = "disappear";
	
	/**Action to get disappeared from the screen. */
	public abstract void disappear();
	/**Action to get appeared on the screen.*/
	public abstract void reappear();
	/**Action to change from appear to disappear, and vice versa.*/
	public abstract void flipState();
	
}
