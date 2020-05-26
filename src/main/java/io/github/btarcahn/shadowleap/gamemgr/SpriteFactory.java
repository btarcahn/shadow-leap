package io.github.btarcahn.shadowleap.gamemgr;

import io.github.btarcahn.shadowleap.gelems.*;

public class SpriteFactory {
    private static final String PATH_BIKE = "assets/bike.png";
    private static final String PATH_RACECAR = "assets/racecar.png";
    private static final String PATH_BUS = "assets/bulldozer.png";
    private static final String PATH_WATER = "assets/water.png";
    private static final String PATH_GRASS = "assets/grass.png";
    private static final String PATH_BULLDOZER = "assets/bulldozer.png";
    private static final String PATH_LOG = "assets/log.png";
    private static final String PATH_LONGLOG = "assets/longlog.png";

    private static final float SPEED_BIKE = 0.2f;
    private static final float SPEED_RACECAR = 0.5f;
    private static final float SPEED_BUS = 0.15f;
    private static final float SPEED_BULLDOZER = 0.05f;
    private static final float SPEED_LOG = 0.1f;
    private static final float SPEED_LONGLOG = 0.07f;

    private FakeScreen screen;

    public SpriteFactory(FakeScreen screen) {
        this.screen = screen;
    }

    Renderable createGrass(int x, int y) {
        return new Sprite(PATH_GRASS, x, y, screen);
    }

    Collidable createWater(int x, int y) {
        Sprite water = new Sprite(PATH_WATER, x, y, screen);
        return new HazardDecorator(water);
    }

    Collidable createMotorbike(float speedSign, int x, int y) {
        // create a moving vehicle
        MovingSprite motorbike = new MovingSprite(PATH_BIKE, x, y, screen);
        motorbike.setDirection(Direction.HORIZONTAL);
        motorbike.setSpeed(speedSign * SPEED_BIKE);

        // decorate it with hazardous behavior
        return new HazardDecorator(motorbike);
    }

    Collidable createRacecar(float speedSign, int x, int y) {
        MovingSprite racecar = new MovingSprite(PATH_RACECAR, x, y, screen);
        racecar.setDirection(Direction.HORIZONTAL);
        racecar.setSpeed(speedSign * SPEED_RACECAR);

        return new HazardDecorator(racecar);
    }

    Collidable createBus(float speedSign, int x, int y) {
        MovingSprite bus = new MovingSprite(PATH_BUS, x, y, screen);
        bus.setDirection(Direction.HORIZONTAL);
        bus.setSpeed(speedSign * SPEED_BUS);

        return new HazardDecorator(bus);
    }

    Collidable createBulldozer(float speedSign, int x, int y) {
        MovingSprite bulldozer = new MovingSprite(PATH_BULLDOZER, x, y, screen);
        bulldozer.setSpeed(speedSign * SPEED_BULLDOZER);

        return new PushyDecorator(bulldozer);
    }

    Collidable createLog(float speedSign, int x, int y) {
        MovingSprite log = new MovingSprite(PATH_LOG, x, y, screen);
        log.setDirection(Direction.HORIZONTAL);
        log.setSpeed(speedSign * SPEED_LOG);

        return new RideableDecorator(log);
    }

    Collidable createLonglog(float speedSign, int x, int y) {
        MovingSprite longlog = new MovingSprite(PATH_LONGLOG, x, y, screen);
        longlog.setDirection(Direction.HORIZONTAL);
        longlog.setSpeed(speedSign * SPEED_LONGLOG);

        return new RideableDecorator(longlog);
    }

}
