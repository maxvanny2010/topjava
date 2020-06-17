package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

/**
 * MealService.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/14/2020
 */
@Service
public class MealService {

    private final MealRepository repository;

    public MealService(final MealRepository repository) {
        this.repository = repository;
    }

    public final Meal create(final Meal meal, final int userId) {
        return this.repository.save(meal, userId);
    }

    public final void delete(final int id, final int userId) {
        checkNotFoundWithId(this.repository.delete(id, userId), id);
    }

    public final Meal get(final int id, final int userId) {
        return checkNotFoundWithId(this.repository.get(id, userId), id);
    }

    public final List<Meal> getAll(final int userId) {
        return this.repository.getAll(userId);
    }

    public final List<Meal> getAll() {
        return this.repository.getAll();
    }

    public final Meal update(final Meal meal, final int userId, final int id) {
        return checkNotFoundWithId(
                this.repository.save(meal, userId), id);
    }
}
