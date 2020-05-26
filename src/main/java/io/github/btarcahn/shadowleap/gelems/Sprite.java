package io.github.btarcahn.shadowleap.gelems;

import io.github.btarcahn.shadowleap.utilities.BoundingBox;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashSet;
import java.util.Set;

public abstract class Sprite {
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

    public final void render() {
        this.image.drawCentered(this.x, this.y);
    }

    public boolean collidesWith(Sprite other) {
        return this.boundingBox.intersects(other.boundingBox);
    }

    public abstract void onCollision();
    public abstract void onCollision(Sprite other);

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
