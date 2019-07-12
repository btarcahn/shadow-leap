package io.github.btarcahn.shadowLeap.utils;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Represents a render-able image.
 * @author Bach Tran
 * @since 2.0
 */
public class Sprite
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

    public void setX(float x) {
        this.x = x;
        box.setX(x);
    }

    public void setY(float y) {
        this.y = y;
        box.setY(y);
    }

    public float width() {
        return image.getWidth();
    }

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