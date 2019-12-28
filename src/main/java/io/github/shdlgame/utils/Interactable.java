package io.github.shdlgame.utils;

/**
 * Object implementing this funtional interface
 * may have the ability to interact with each
 * other.
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

    /**
     * Contains the action to be done
     * if an interaction happens.
     */
    void reaction(Interactable obj);
}
