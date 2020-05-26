package io.github.btarcahn.shadowleap.gelems;

public abstract class SpriteDecorator implements Collidable {
    protected Sprite sprite;

    public SpriteDecorator(Sprite sprite) {
        this.sprite = sprite;
    }
}
