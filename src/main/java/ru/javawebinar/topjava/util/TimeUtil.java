package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;
import ru.javawebinar.topjava.model.UserMeals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TimeUtil.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 5/24/2020
 */
public final class TimeUtil {
    /**
     * Constructor.
     */
    private TimeUtil() {
    }

    /**
     * Method to filter data.
     *
     * @param meals          meals
     * @param start          start time
     * @param end            end time
     * @param caloriesPerDay a calories per day
     * @return result
     */
    public static List<UserMealWithExcess> filteredByCycles(
            final SortedMap<LocalDate, UserMeals> meals,
            final LocalDateTime start,
            final LocalDateTime end,
            final int caloriesPerDay) {
        final LocalDate startDay = start.toLocalDate();
        final LocalDate endDay = end.toLocalDate().plusDays(1);
        SortedMap<LocalDate, UserMeals> days = meals.subMap(startDay, endDay);
        final SortedMap<?, ?>[] byTime = new SortedMap[days.size()];
        final List<UserMealWithExcess> list = new ArrayList<>();
        int i = 0;
        for (final UserMeals m : days.values()) {
            int calories = 0;
            byTime[i++] = m.getMeals().subMap(start, end.plusHours(1));
            list.add(new UserMealWithExcess(false, byTime[i - 1]));
            for (final UserMeal u : m.getMeals().values()) {
                calories += u.getCalories();
                if (calories > caloriesPerDay) {
                    list.get(i - 1).setExcess(true);
                    break;
                }
            }
        }
        return list;
    }

    /**
     * Method to filter data.
     *
     * @param meals          meals
     * @param start          start time
     * @param end            end time
     * @param caloriesPerDay a calories per day
     * @return result
     */
    public static List<UserMealWithExcess> filteredByStreams(
            final SortedMap<LocalDate, UserMeals> meals,
            final LocalDateTime start,
            final LocalDateTime end,
            final int caloriesPerDay) {
        final LocalDate startDay = start.toLocalDate();
        final LocalDate endDay = end.toLocalDate().plusDays(1);
        SortedMap<LocalDate, UserMeals> days = meals.subMap(startDay, endDay);
        final AtomicInteger calories = new AtomicInteger();
        final List<UserMealWithExcess> list = new ArrayList<>();
        final SortedMap<?, ?>[] byTime = new SortedMap[days.size()];
        final AtomicInteger i = new AtomicInteger();
        days.values().stream()
                .peek(m -> {
                    calories.set(0);
                    byTime[i.getAndIncrement()] = m.getMeals()
                            .subMap(start, end.plusHours(1));
                    list.add(new UserMealWithExcess(
                            false, byTime[i.get() - 1]));
                })
                .flatMap(m -> m.getMeals().values().stream())
                .forEach(m -> {
                    calories.addAndGet(m.getCalories());
                    if (calories.get() > caloriesPerDay) {
                        list.get(i.get() - 1).setExcess(true);
                    }
                });
        return list;
    }

    /**
     * Method to get interval.
     *
     * @param lt        init time
     * @param startTime a start time
     * @param endTime   a end time
     * @return a result
     */
    @SuppressWarnings("unused")
    public static boolean isBetweenHalfOpen(final LocalTime lt,
                                            final LocalTime startTime,
                                            final LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }
}
