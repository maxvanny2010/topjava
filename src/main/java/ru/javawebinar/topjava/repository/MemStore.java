package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MemStore.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public final class MemStore implements IStore<Meal> {
    /**
     * field a instance of StoreMemory.
     */
    private static final MemStore MEM_STORE = new MemStore();
    /**
     * field a storage.
     */
    private final Map<Integer, Meal> storage;

    /**
     * Constructor.
     */
    private MemStore() {
        this.storage = new ConcurrentHashMap<>();
        final Meal one = new Meal(
                LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0),
                "Завтрак", 500);
        final Meal two = new Meal(
                LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0),
                "Обед", 1000);
        final Meal three = new Meal(
                LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0),
                "Ужин", 500);
        final Meal four = new Meal(LocalDateTime.of(
                2020, Month.JANUARY, 31, 0, 0),
                "Еда на граничное значение", 100);
        final Meal five = new Meal(
                LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0),
                "Обед", 500);
        final Meal six = new Meal(
                LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0),
                "Ужин", 410);
        this.create(one);
        this.create(two);
        this.create(three);
        this.create(four);
        this.create(five);
        this.create(six);
    }

    /**
     * Method to get.
     *
     * @return the instance a store of memory
     */
    public static MemStore getInstance() {
        return MEM_STORE;
    }

    @Override
    public final int getSize() {
        return this.storage.size();
    }

    @Override
    public final Meal create(final Meal meal) {
        Objects.requireNonNull(meal, "must not be null");
        return this.storage.putIfAbsent(meal.getId(), meal);
    }

    @Override
    public final Meal update(final Meal meal) {
        Objects.requireNonNull(meal, "must not be null");
        return this.storage.computeIfPresent(meal.getId(), (k, v) -> v = meal);
    }

    @Override
    public final void delete(final int id) {
        this.storage.remove(id);
    }

    @Override
    public final List<Meal> findAll() {
        return new ArrayList<>(this.storage.values());
    }

    @Override
    public final Optional<Meal> findById(final int id) {
        return Optional.ofNullable(
                this.storage.getOrDefault(id, null));
    }
}
