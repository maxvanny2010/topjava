package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.StringJoiner;

/**
 * Meal.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/14/2020
 */
public class Meal extends AbstractBaseEntity {
    private LocalDateTime dateTime;
    private String description;
    private int calories;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String aDescription) {
        this.description = aDescription;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(final Integer aCalories) {
        this.calories = aCalories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    @Override
    public final String toString() {
        return new StringJoiner(", ",
                Meal.class.getSimpleName() + "[", "]")
                .add("\n")
                .add("id=" + this.id)
                .add("dateTime=" + this.dateTime)
                .add("description='" + this.description + "'")
                .add("calories=" + this.calories)
                .toString();
    }
}
