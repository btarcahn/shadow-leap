package io.github.btarcahn.shadowleap.gamemgr;

import io.github.btarcahn.shadowleap.gelems.*;
import org.newdawn.slick.Input;

import java.io.File;
import java.util.List;

public class WorldInitializer {
    private String[] paths = new File("assets/levels/").list();

    private final FakeScreen screen = FakeScreen.getScreen(1024, 768);

    private Transcriber transcriber = new Transcriber(paths[0], screen);

    private List<Renderable> renderables;
    private List<Collidable> collidables;

    public WorldInitializer() {
        renderables = this.transcriber.getRenderables();
        collidables = this.transcriber.getCollidables();
    }

    public void update(Input input, int delta) {
        // TODO check collisions


        // Update positions
        collidables.stream()
                .filter(element -> element instanceof MovingSprite)
                .forEach(sprite -> ((MovingSprite) sprite).update(input, delta));
    }

    public void render() {
        renderables.stream()
                .filter(Renderable::onscreen)
                .filter(Renderable::canRender)
                .forEach(Renderable::render);
    }

}
