package ru.javawebinar.topjava.model;

import java.util.Objects;
import java.util.SortedMap;
import java.util.StringJoiner;

/**
 * UserMealWithExcess.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 5/24/2020
 */
public class UserMealWithExcess {
    /**
     * field a meals by a day.
     */
    private final SortedMap<?, ?> meals;
    /**
     * field a excess.
     */
    private boolean excess;

    /**
     * /**
     * Constructor.
     *
     * @param aExcess a excess
     * @param aMeals  a meals
     */
    public UserMealWithExcess(final boolean aExcess,
                              final SortedMap<?, ?> aMeals) {
        this.excess = aExcess;
        this.meals = aMeals;
    }

    @Override
    public final String toString() {
        return new StringJoiner(", ",
                UserMealWithExcess.class.getSimpleName()
                        + "[\n", "]\n")
                .add("excess=" + this.excess + "\n")
                .add("meals=" + this.meals)
                .toString();
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserMealWithExcess)) {
            return false;
        }
        final UserMealWithExcess excess1 = (UserMealWithExcess) o;
        return this.excess == excess1.excess
                && this.meals.equals(excess1.meals);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.meals, this.excess);
    }

    /**
     * Method to set.
     *
     * @param aExcess a excess
     **/
    public final void setExcess(final boolean aExcess) {
        this.excess = aExcess;
    }
}
