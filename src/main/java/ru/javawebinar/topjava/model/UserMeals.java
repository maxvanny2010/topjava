package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TreeMap;

/**
 * UserMeals.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 5/24/2020
 */
public class UserMeals {

    /**
     * field a meals by a day.
     */
    private final SortedMap<LocalDateTime, UserMeal> meals = new TreeMap<>();

    /**
     * Method to get.
     *
     * @return a meals by a day
     */
    public final SortedMap<LocalDateTime, UserMeal> getMeals() {
        return this.meals;
    }

    /**
     * Method to add.
     *
     * @param time time by meal
     * @param meal a meal by time
     */
    public final void putMeal(final LocalDateTime time, final UserMeal meal) {
        this.meals.put(time, meal);
    }

    @Override
    public final String toString() {
        return new StringJoiner(",\n ",
                UserMeals.class.getSimpleName() + "[\n",
                "]\n")
                .add("meals=" + this.meals)
                .toString();
    }
}
