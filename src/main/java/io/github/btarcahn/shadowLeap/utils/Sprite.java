package io.github.btarcahn.shadowLeap.utils;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Abstract class on top of the hierarchy. All other Sprites
 * shall extend this class.
 * The abstract Sprite has a core image, which
 * is used for rendering and calculating sizes.
 * The (x, y) position of the Sprite lies in the
 * center of its core image. A BoundingBox is used
 * by this class to check for interactions.
 * @author Bach Tran
 * @since 2.0
 * @see BoundingBox
 */
public abstract class Sprite
        implements Renderable, Interactable {
    private BoundingBox box;
    private Image image;
    private float x, y;

    /**
     * Creates a new Sprite object.
     * @param ref the image reference of the Sprite.
     * @param x the starting x-pos.
     * @param y the starting y-pos.
     */
    public Sprite(String ref, float x, float y) {
        try {
            image = new Image(ref);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
        assert image != null;
        box = new BoundingBox(image, x, y);
    }

    /**
     * The current horizontal position of the Sprite.
     * @since 2.0
     * @return the x-position of the Sprite.
     */
    public float x() {
        return this.x;
    }

    /**
     * The current vertical position of the Sprite.
     * @since 2.0
     * @return the y-position of the Sprite.
     */
    public float y() {
        return this.y;
    }

    /**
     * Modifies the horizontal position of the Sprite.
     * @since 1.0
     * @param x the new x-position of the Sprite.
     */
    public void setX(float x) {
        this.x = x;
        box.setX(x);
    }

    /**
     * Modifies the vertical position of the Sprite.
     * @since 1.0
     * @param y the new y-position of the Sprite.
     */
    public void setY(float y) {
        this.y = y;
        box.setY(y);
    }

    /**
     * Gets the width of the Sprite, which is the
     * width of its core image.
     * @return the width of the Sprite.
     */
    public float width() {
        return image.getWidth();
    }

    /**
     * Gets the height of the Sprite, which is the
     * height of its core image.
     * @return the height of the Sprite.
     */
    public float height() {
        return image.getHeight();
    }

    /**
     * Draws the Sprites to the game screen.
     * Uses its (x, y) coordinates as the central
     * of the image.
     * @since 2.0
     */
    @Override
    public void render() {
        image.drawCentered(x, y);
    }


    @Override
    public boolean interacts(Interactable o) {

        // an object must not interact with itself
        if (o == this) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        Sprite other = (Sprite) o;
        return box.interacts(other.box);
    }

    /**
     * Calculates the distance between
     * this Sprite object and the given
     * object.
     * @param o the other object
     * @return the distance between this object
     * and the other object.
     */
    public double dist(Sprite o) {
        return Math.sqrt(Math.pow(x,2) + Math.pow(y, 2));
    }
}