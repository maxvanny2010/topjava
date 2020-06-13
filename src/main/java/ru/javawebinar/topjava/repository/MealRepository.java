package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * MealRepository.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/14/2020
 */
public interface MealRepository {
    // null if not found, when updated
    Meal save(Meal meal, int userId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Meal get(int id, int userId);

    List<Meal> getAll(int userId);
}
