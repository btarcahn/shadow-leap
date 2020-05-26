package io.github.btarcahn.shadowleap.gamemgr;

import io.github.btarcahn.shadowleap.*;
import io.github.btarcahn.shadowleap.gelems.Collidable;
import io.github.btarcahn.shadowleap.gelems.FakeScreen;
import io.github.btarcahn.shadowleap.gelems.MovingSprite;
import io.github.btarcahn.shadowleap.gelems.Renderable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public final class Transcriber {
    private String path = "assets/levels/0.lvl";
    private SpriteFactory factory;
    private List<Renderable> renderables;
    private List<Collidable> collidables;

    public Transcriber(String path, FakeScreen screen) {
        this.path = path;
        this.factory = new SpriteFactory(screen);
        renderables = new ArrayList<>();
        collidables = new ArrayList<>();
    }

    public List<Renderable> getRenderables() {
        return renderables;
    }

    public List<Collidable> getCollidables() {
        return collidables;
    }

    void interpretFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String text;

            while ((text = br.readLine()) != null) {
                // read the level information and initialise sprites
                String[] information = text.split(",");
                int x = Integer.parseInt(information[1]);
                int y = Integer.parseInt(information[2]);
                boolean moveRight = true;
                float speedSign = 0;
                // I don't like to hard code, but...
                if (information.length == 4) {
                    moveRight = Boolean.parseBoolean(information[3]);
                    speedSign = moveRight ? 1.0f : -1.0f;
                }

                switch (information[0]) {

                    case "water":
                        Collidable water = factory.createWater(x, y);
                        collidables.add(water);
//                        renderables.add((MovingSprite) water);
                        break;

                    case "grass":
                        Renderable grass = factory.createGrass(x, y);
                        renderables.add(grass);
                        break;

                    case "tree":
                        Renderable tree = factory.createTree(x, y);
                        renderables.add(tree);
                        break;

                    case "bus":
                        Collidable bus = factory.createBus(speedSign, x, y);
                        collidables.add(bus);
//                        renderables.add((MovingSprite) bus);
                        break;

                    case "bike":
                        // note: the bike hasn't reached the border yet
                        Collidable motorbike = factory.createMotorbike(speedSign, x, y);
                        collidables.add(motorbike);
//                        renderables.add((MovingSprite) motorbike);
                        break;

                    case "racecar":
                        Collidable racecar = factory.createRacecar(speedSign, x, y);
                        collidables.add(racecar);
//                        renderables.add((MovingSprite) racecar);
                        break;

                    case "bulldozer":
                        Collidable bulldozer = factory.createBulldozer(speedSign, x, y);
                        collidables.add(bulldozer);
//                        renderables.add((MovingSprite) bulldozer);
                        break;

                    case "log":
                        Collidable log = factory.createLog(speedSign, x, y);
                        collidables.add(log);
//                        renderables.add((MovingSprite) log);
                        break;

                    case "longLog":
                        Collidable longlog = factory.createLonglog(speedSign, x, y);
                        collidables.add(longlog);
//                        renderables.add((MovingSprite) longlog);
                        break;

                    case "turtle":
                        Collidable turtle = factory.createTurtle(speedSign, x, y);
                        collidables.add(turtle);
//                        renderables.add((MovingSprite) turtle);
                        break;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
