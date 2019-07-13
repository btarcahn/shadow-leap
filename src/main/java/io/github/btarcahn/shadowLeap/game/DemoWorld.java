package io.github.btarcahn.shadowLeap.game;

import io.github.btarcahn.shadowLeap.utils.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

public final class DemoWorld {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    List<Renderable> renderables = new ArrayList<>();

    List<Controllable> controllables = new ArrayList<>();

    public DemoWorld() {
        Player player = new Player("assets/frog.png", 400, 576);
        controllables.add(player);
        renderables.add(player);
    }

    public void update(GameContainer gc, int delta) {
        // TODO update logic

        controllables.forEach(player -> player.accept(gc.getInput()));

    }

    public void render(Graphics g) {
        renderables.forEach(Renderable::render);
    }

}
