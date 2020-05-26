package io.github.btarcahn.shadowleap.gamemgr;

import io.github.btarcahn.shadowleap.*;
import io.github.btarcahn.shadowleap.gelems.Collidable;
import io.github.btarcahn.shadowleap.gelems.FakeScreen;
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
                        collidables.add(factory.createWater(x, y));
                        break;

                    case "grass":
                        renderables.add(factory.createGrass(x, y));
                        break;

                    case "tree":
                        // TODO add trees
                        break;

                    case "bus":
                        collidables.add(factory.createBus(speedSign, x, y));
                        break;

                    case "bike":
                        // note: the bike hasn't reached the border yet
                        collidables.add(factory.createMotorbike(speedSign, x, y));
                        break;

                    case "racecar":
                        collidables.add(factory.createRacecar(speedSign, x, y));
                        break;

                    case "bulldozer":
                        collidables.add(factory.createBulldozer(speedSign, x, y));
                        break;

                    case "log":
                        collidables.add(factory.createLog(speedSign, x, y));
                        break;

                    case "longLog":
                        collidables.add(factory.createLonglog(speedSign, x, y));
                        break;

                    case "turtle":
                        collidables.add(factory.createTurtle(speedSign, x, y));
                        break;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
