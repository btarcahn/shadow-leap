package io.github.btarcahn.shadowLeap.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Multi-dimensional n-vector object.
 * @param <T> number type
 * @author Bach Tran
 * @since 2.0
 */
class Position<T extends Number> implements Cloneable {

    private List<T> vector;

    /**
     * Creates a position object
     * @param n number of dimensions
     */
    Position(int n)
            throws IllegalArgumentException {
        vector = new ArrayList<>(n);
    }

    T get(int i) {
        return vector.get(i);
    }

    void set(int i, T element)
            throws IndexOutOfBoundsException {
        vector.set(i, element);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class FloatPos2d extends Position<Float> {

    private static final int X_DIM = 0;
    private static final int Y_DIM = 1;
    private static final int DEFAULT_DIM = 2;

    /**
     * Creates a new,
     * empty FloatPos2d objects.
     */
    FloatPos2d() {
        super(DEFAULT_DIM);
    }

    FloatPos2d(float x, float y) {
        super(DEFAULT_DIM);
        setX(x);
        setY(y);
    }

    /**
     * Creates a position object. This constructors
     * always create two dimensional objects.
     * @param n number of dimensions
     */
    FloatPos2d(int n) {
        super(DEFAULT_DIM);
    }

    void setX(float x) {
        set(X_DIM, x);
    }

    void setY(float y) {
        set(Y_DIM, y);
    }

    float x() {
        return get(X_DIM);
    }

    float y() {
        return get(Y_DIM);
    }
}
