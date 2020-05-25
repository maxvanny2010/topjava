package ru.javawebinar.topjava.model;

import java.time.LocalTime;
import java.util.StringJoiner;

/**
 * UserMeal.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 5/24/2020
 */
public class UserMeal {
    /**
     * field a date of time.
     */
    private final LocalTime dateTime;
    /**
     * field a description.
     */
    private final String description;
    /**
     * field a calories.
     */
    private final int calories;

    /**
     * Constructor.
     *
     * @param aTime        a date of time
     * @param sDescription a description
     * @param aCalories    a calories
     */
    public UserMeal(final LocalTime aTime, final String sDescription,
                    final int aCalories) {
        this.dateTime = aTime;
        this.description = sDescription;
        this.calories = aCalories;
    }

    /**
     * Method to get.
     *
     * @return a date of time
     */
    @SuppressWarnings("unused")
    public final LocalTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Method to get.
     *
     * @return a description
     */
    @SuppressWarnings("unused")
    public final String getDescription() {
        return this.description;
    }

    /**
     * Method to get.
     *
     * @return calories
     */
    public final int getCalories() {
        return this.calories;
    }

    @Override
    public final String toString() {
        return new StringJoiner(", ",
                UserMeal.class.getSimpleName()
                        + "[", "]\n")
                .add("dateTime=" + this.dateTime)
                .add("description='" + this.description + "'")
                .add("calories=" + this.calories)
                .toString();
    }
}
