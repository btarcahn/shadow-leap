package io.github.btarcahn.shadowleap.gelems;

public interface Renderable {
    /**
     * Checks if the given element is on the screen.
     * @param screen the screen to be checked against
     * @return true if the element is on the screen.
     */
    boolean onscreen(FakeScreen screen);

    /**
     * Renders the element on the screen
     */
    void render();
}
