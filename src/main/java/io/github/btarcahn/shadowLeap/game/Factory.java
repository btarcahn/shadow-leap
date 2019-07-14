package io.github.btarcahn.shadowLeap.game;

/**
 * Functional interface of the
 * Factory pattern
 * @author Bach Tran
 * @since 2.0
 * @param <T>
 */
interface Factory<T> {
    T create();
}
