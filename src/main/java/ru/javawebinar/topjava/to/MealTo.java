package ru.javawebinar.topjava.to;

import java.time.LocalDateTime;
import java.util.StringJoiner;

public class MealTo {
    private final Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean excess;

    public MealTo(Integer id, LocalDateTime dateTime, String description,
                  int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public final String toString() {
        return new StringJoiner(", ",
                MealTo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("dateTime=" + dateTime)
                .add("description='" + description + "'")
                .add("calories=" + calories)
                .add("excess=" + excess)
                .toString();
    }
}
