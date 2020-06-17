package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * InMemoryMealRepository.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/14/2020
 */
@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> this.save(m, SecurityUtil.authUserId()));
    }

    @Override
    public final Meal save(final Meal meal, final int userId) {
        Map<Integer, Meal> meals = this.repository.get(userId);
        final boolean isMeals = Objects.isNull(meals);
        if (meal.isNew()) {
            meal.setId(this.counter.incrementAndGet());
            if (isMeals) {
                meals = new HashMap<>();
                meals.put(meal.getId(), meal);
                this.repository.put(userId, meals);
            } else {
                meals.put(meal.getId(), meal);
            }
            LOG.info("meal save: {}", meal);
            return meal;
        }
        // handle case: update, but not present in storage
        LOG.info("meal update: {}", meal);
        if (isMeals) {
            return null;
        }
        return meals.computeIfPresent(meal.getId(), (a, b) -> meal);
    }

    @Override
    public final boolean delete(int id, final int userId) {
        LOG.info("meal delete: {}", id);
        final Meal meal = this.get(id, userId);
        if (Objects.isNull(meal)) {
            return false;
        }
        final Meal remove = this.repository.get(userId).remove(id);
        if (this.repository.get(userId).size() == 0) {
            this.repository.remove(userId);
        }
        return Objects.nonNull(remove);
    }

    @Override
    public final Meal get(final int id, final int userId) {
        final Map<Integer, Meal> meals = this.repository.get(userId);
        if (Objects.nonNull(meals)) {
            final Meal meal = meals.get(id);
            LOG.info("meal get: {}", meal);
            return meal;
        }
        return null;
    }

    @Override
    public final List<Meal> getAll(final int userId) {
        final Map<Integer, Meal> meals = this.repository.get(userId);
        if (Objects.isNull(meals)) {
            return new ArrayList<>();
        }
        return meals
                .values()
                .stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public final List<Meal> getAll() {
        if (this.repository.size() == 0) {
            return new ArrayList<>();
        }
        return this.repository
                .values()
                .stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

