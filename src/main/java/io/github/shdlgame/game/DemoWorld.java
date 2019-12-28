package io.github.shdlgame.game;

import io.github.shdlgame.utils.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

public final class DemoWorld {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    List<Renderable> renderables = new ArrayList<>();
    List<MovingSprite> movingSprites = new ArrayList<>();
    List<Controllable> controllables = new ArrayList<>();

    public DemoWorld() {
        Player player = Factory.createPlayer(400, 576);
        MovingSprite bike = Factory.createBike(480, 480);
        renderables.add(player);
        controllables.add(player);
        renderables.add(Factory.createWater(480, 480));
        movingSprites.add(bike);
        renderables.add(bike);

    }

    private void read(String name) {

    }

    public void update(GameContainer gc, int delta) {
        // TODO update logic

        controllables.forEach(player -> player.accept(gc.getInput()));
        movingSprites.forEach(sprite -> {

            if (sprite.x() > SCREEN_WIDTH || sprite.y() > SCREEN_HEIGHT) {
                sprite.onBorder();
            }
            sprite.update(delta);
        });
    }

    public void render(Graphics g) {
        renderables.forEach(Renderable::render);
    }

}
