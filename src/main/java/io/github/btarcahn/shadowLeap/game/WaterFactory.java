package io.github.btarcahn.shadowLeap.game;

import io.github.btarcahn.shadowLeap.utils.Interactable;
import io.github.btarcahn.shadowLeap.utils.Sprite;

import java.util.ArrayList;
import java.util.List;

public class WaterFactory implements SpriteFactory<Sprite> {

    private static final String src = "assets/water.png";

    private WaterFactory() {

    }

    private static final WaterFactory instance = new WaterFactory();

    public static WaterFactory getInstance() {
        return instance;
    }

    @Override
    public Sprite create(float x_start, float y_start) {
        return new Sprite(src, x_start, y_start) {

            @Override
            public void reaction(Interactable obj) {
                // DO NOTHING
            }
        };
    }

    public List<Sprite> create(float... coords)
            throws IllegalArgumentException {

        if (coords.length % 2 != 0) {
            throw new IllegalArgumentException("The number of coordinates must be even.");
        }

        List<Sprite> waters = new ArrayList<>();



        for (int i = 0; i < coords.length; i++) {
            if (i % 2 == 0) {
                waters.add(new Sprite(src, coords[i], coords[i+1]) {

                    @Override
                    public void reaction(Interactable obj) {
                        // TODO kill sprite
                    }
                });
            }
        }

        return waters;
    }
}
