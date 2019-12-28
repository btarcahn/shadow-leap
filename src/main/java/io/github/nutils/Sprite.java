package io.github.nutils;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.OperationNotSupportedException;

import java.util.ArrayList;
import java.util.List;

class Sprite extends Shape {
    protected Image image;
    protected List<String> tags = new ArrayList<>();

    public Sprite(Image image, float x, float y) {
        super();
        this.image = image;
        this.center = new float[2];
        this.center[0] = x;
        this.center[1] = y;
    }

    public void render() {
        this.image.drawCentered(this.center[0],
                this.center[1]);
    }

    @Override
    public Shape transform(Transform transform) {
        return this;
    }

    @Override
    protected void createPoints() {
        throw new OperationNotSupportedException(
                "Not supported in this game instance."
        );
    }
}

class MovingSprite extends Sprite {

    public MovingSprite(Image image, float x, float y) {
        super(image, x, y);
    }

    @Override
    public Shape transform(Transform transform) {
        float[] direction = transform.getMatrixPosition();
        assert direction.length == 2;
        this.center[0] += direction[0];
        this.center[1] += direction[1];
        return this;
    }
}

class ControllableSprite extends MovingSprite {

    public ControllableSprite(Image image, float x, float y) {
        super(image, x, y);
    }

    public void acceptInput(Input input) {
        Transform transform = new Transform();

    }
}