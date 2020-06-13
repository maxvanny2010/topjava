package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

/**
 * MealRestController.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/14/2020
 */
@Controller
public class MealRestController {
    private final MealService service;

    public MealRestController(final MealService service) {
        this.service = service;
    }

    public final Meal create(final Meal meal) {
        return this.service.create(meal, authUserId());
    }

    public final Meal get(final int id) {
        return this.service.get(id, authUserId());
    }

    public final void delete(final int id) {
        this.service.delete(id, authUserId());
    }

    public final Meal update(final Meal meal) {
        return this.service.update(meal, authUserId());
    }

    public final List<MealTo> getAll() {
        return MealsUtil.getTos(this.service.getAll(authUserId()),
                MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public final List<MealTo> getByStartTime(final LocalTime startTime) {
        return MealsUtil.filterByPredicate(this.service.getAll(authUserId()),
                MealsUtil.DEFAULT_CALORIES_PER_DAY,
                (meal) -> DateTimeUtil.isBetweenHalfOpen(meal.getTime(),
                        startTime, LocalTime.MAX));
    }

    public final List<MealTo> getByEndTime(final LocalTime endTime) {
        return MealsUtil.filterByPredicate(this.service.getAll(authUserId()),
                MealsUtil.DEFAULT_CALORIES_PER_DAY,
                (meal) -> DateTimeUtil.isBetweenHalfOpen(meal.getTime(),
                        LocalTime.MIN, endTime));
    }

    public final List<MealTo> getByStartDay(final LocalDate startDate) {
        return MealsUtil.filterByPredicate(this.service.getAll(authUserId()),
                MealsUtil.DEFAULT_CALORIES_PER_DAY,
                (meal) -> DateTimeUtil.isBetweenHalfOpenDay(meal.getDate(),
                        startDate, LocalDate.MAX));
    }

    public final List<MealTo> getByEndDay(final LocalDate endDate) {
        return MealsUtil.filterByPredicate(this.service.getAll(authUserId()),
                MealsUtil.DEFAULT_CALORIES_PER_DAY,
                (meal) -> DateTimeUtil.isBetweenHalfOpenDay(meal.getDate(),
                        LocalDate.MIN, endDate));
    }
}
