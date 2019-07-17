package io.github.btarcahn.shadowLeap.utils;

import org.newdawn.slick.Input;

/**
 * Objects implementing this interface
 * may accept user-input (e.g. keyboard,
 * mouse) to conduct relevant reactions.
 * @author Bach Tran
 * @since 2.0
 */
public interface Controllable {
    /**
     * Accepts the input object, which contains
     * user-input information.
     * @param input user-input object.
     */
    void accept(Input input);
}
