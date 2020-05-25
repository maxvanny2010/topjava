package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TreeMap;

/**
 * User.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 5/24/2020
 */
public class User {
    /**
     * field a map by meals per day by a user.
     */
    private final SortedMap<LocalDate, UserMeals> map = new TreeMap<>();

    /**
     * Method to get.
     *
     * @return user meals per day
     */
    public final SortedMap<LocalDate, UserMeals> getMealsPerDay() {
        return this.map;
    }

    /**
     * Method to add.
     *
     * @param key   day by user
     * @param value the list of user meals by time
     */
    public final void putMap(final LocalDate key, final UserMeals value) {
        this.map.put(key, value);
    }

    @Override
    public final String toString() {
        return new StringJoiner(", ",
                User.class.getSimpleName() + "[", "]")
                .add("map=" + this.map)
                .toString();
    }
}
