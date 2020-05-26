package io.github.btarcahn.shadowleap.gelems;


public final class FakeScreen implements Collidable {
    private int width;
    private int height;

    private FakeScreen singleInstance;
    private boolean created = false;

    private FakeScreen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public FakeScreen getScreen(int width, int height) {
        if (!created) {
            singleInstance = new FakeScreen(width, height);
        }
        return singleInstance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean collidesWith(Collidable other) {
        if (other instanceof Sprite) {
            Sprite sprite = (Sprite) other;
            return sprite.getX() > this.width + sprite.getWidth() / 2
                    || sprite.getX() < -sprite.getWidth() / 2
                    || sprite.getY() > this.height + sprite.getHeight() / 2
                    || sprite.getY() < -sprite.getHeight() / 2;
        }

        return false;
    }

    @Override
    public void onCollision() {

    }

    @Override
    public void onCollision(Collidable other) {
        if (other instanceof MovingSprite) {
            MovingSprite sprite = (MovingSprite) other;
            //The sprite is on the left of the screen
            if (sprite.getX() < -sprite.getWidth() / 2) {
                sprite.setX(width + sprite.getWidth() / 2);
            } // The sprite is on the right of the screen
            else if (sprite.getX() > this.width + sprite.getWidth() / 2) {
                sprite.setX(-sprite.getWidth() / 2);
            }
        }
    }
}
