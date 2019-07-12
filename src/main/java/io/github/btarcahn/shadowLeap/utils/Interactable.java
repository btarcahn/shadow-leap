package io.github.btarcahn.shadowLeap.utils;

/**
 * Functional interface checks for
 * object interactions.
 * @author Bach Tran
 * @since 2.0
 */
public interface Interactable {

    /**
     * Checks for interactions between
     * two objects
     * @param o the other object to be checked for interaction.
     * @return true if two object interact.
     */
    boolean interacts(Interactable o);
}
