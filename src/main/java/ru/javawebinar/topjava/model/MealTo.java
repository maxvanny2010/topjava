package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo {
    private final int id;
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean excess;

    public MealTo(int id, LocalDateTime dateTime, String description,
                  int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    @SuppressWarnings("unused")
    public final int getId() {
        return this.id;
    }

    @SuppressWarnings("unused")
    public final LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @SuppressWarnings("unused")
    public final String getDescription() {
        return this.description;
    }

    @SuppressWarnings("unused")
    public final int getCalories() {
        return this.calories;
    }

    @SuppressWarnings("unused")
    public final boolean getExcess() {
        return this.excess;
    }

    @Override
    public final String toString() {
        return "MealTo{"
                + "id=" + id
                + "dateTime=" + dateTime
                + ", description='" + description + '\''
                + ", calories=" + calories
                + ", excess=" + excess
                + '}';
    }
}
