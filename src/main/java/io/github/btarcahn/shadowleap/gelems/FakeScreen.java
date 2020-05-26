package io.github.btarcahn.shadowleap.gelems;


public final class FakeScreen implements Collidable {
    private final int width;
    private final int height;

    private int lifeCount = 3;

    private static FakeScreen singleInstance;
    private static boolean created = false;

    private FakeScreen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static FakeScreen getScreen(int width, int height) {
        if (!created) {
            singleInstance = new FakeScreen(width, height);
            created = true;
        }
        return singleInstance;
    }

    public void increaseLife() {
        lifeCount++;
    }
    public void decreaseLife() {
        lifeCount--;
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
        // screen does nothing
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
