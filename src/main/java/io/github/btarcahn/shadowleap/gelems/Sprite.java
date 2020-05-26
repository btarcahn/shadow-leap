package io.github.btarcahn.shadowleap.gelems;

import io.github.btarcahn.shadowleap.App;
import io.github.btarcahn.shadowleap.utilities.BoundingBox;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashSet;
import java.util.Set;

public abstract class Sprite implements Collidable, Renderable {
    private BoundingBox boundingBox;
    private Image image;
    private float x;
    private float y;
    private Set<String> tags = new HashSet<>();

    public Sprite(String pathToImg, float x, float y) {

        // initialize the image from source
        try {
            this.image = new Image(pathToImg);
        } catch (SlickException e) {
            System.out.println(pathToImg + " is invalid,");
            e.printStackTrace();
        }
        // sets coordinates & bounding box
        this.x = x;
        this.y = y;
        boundingBox = new BoundingBox(this.image, this.x, this.y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        boundingBox.setX(x);
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        boundingBox.setY(y);
    }

    public int getHeight() {
        return this.image.getHeight();
    }

    public int getWidth() {
        return this.image.getWidth();
    }

    @Override
    public boolean onscreen(FakeScreen screen) {
        return !(x + getWidth() / 2 > screen.getWidth()
                || x - getWidth() / 2 < 0
                || y + getHeight() / 2 > screen.getHeight()
                || y - getHeight() / 2 < 0);
    }

    @Override
    public final void render() {
        this.image.drawCentered(this.x, this.y);
    }

    @Override
    public boolean collidesWith(Collidable other) {
        if (other instanceof Sprite) {
            Sprite sprite = (Sprite)other;
            return this.boundingBox.intersects(((Sprite) other).boundingBox);
        }
        return false;
    }

    public boolean addTag(String tag) {
        return tags.add(tag);
    }

    public boolean removeTag(String tag) {
        return tags.remove(tag);
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }
}
