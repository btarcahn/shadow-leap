package io.github.btarcahn.shadowleap.gelems;

public interface Renderable {
    /**
     * Checks if the given element is on the screen.
     * @return true if the element is on the screen.
     */
    boolean onscreen();

    boolean canRender();

    /**
     * Renders the element on the screen
     */
    void render();
}
