package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.StringJoiner;

import static ru.javawebinar.topjava.util.MealUtil.getCounter;

public class Meal {
    private final int id;
    private LocalDateTime dateTime;
    private String description;
    private int calories;

    public Meal(LocalDateTime aDateTime, String aDescription, int aCalories) {
        this.id = getCounter();
        this.dateTime = aDateTime;
        this.description = aDescription;
        this.calories = aCalories;
    }

    public final int getId() {
        return this.id;
    }

    public final LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public final void setDateTime(final LocalDateTime aDateTime) {
        this.dateTime = aDateTime;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(final String aDesc) {
        this.description = aDesc;
    }

    public final int getCalories() {
        return this.calories;
    }

    public final void setCalories(final int aCalories) {
        this.calories = aCalories;
    }

    public final LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }

    public final LocalTime getTime() {
        return this.dateTime.toLocalTime();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                Meal.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("dateTime=" + dateTime)
                .add("description='" + description + "'")
                .add("calories=" + calories)
                .toString();
    }
}
