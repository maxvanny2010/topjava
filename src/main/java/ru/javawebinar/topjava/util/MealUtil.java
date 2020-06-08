package ru.javawebinar.topjava.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * MealUtil.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public final class MealUtil {
    /**
     * field a atomic integer.
     */
    private static final AtomicInteger ATOM = new AtomicInteger();

    /**
     * Constructor.
     */
    private MealUtil() {
    }

    /**
     * Method to get.
     *
     * @return int by atomic
     */
    public static int getCounter() {
        return ATOM.getAndIncrement();
    }

    /**
     * Method to get.
     *
     * @param start start init
     */
    public static void setCounter(final int start) {
        ATOM.set(start);
    }
}
