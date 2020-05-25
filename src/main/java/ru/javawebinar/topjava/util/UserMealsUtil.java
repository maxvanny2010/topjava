package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;
import ru.javawebinar.topjava.model.UserMeals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * UserMealsUtil.
 *
 * @author Maxim Vanny
 * @version 1.0
 * @since 5/24/2020
 */

public final class UserMealsUtil {
    /**
     * field a user.
     */
    private static final User USER = new User();

    /**
     * Constructor.
     */
    private UserMealsUtil() {
    }

    /**
     * Method a enter to program.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        final UserMealsUtil util = new UserMealsUtil();
        util.before();
        final User user = util.getUser();
        final List<UserMealWithExcess> mealsTo = TimeUtil.filteredByCycles(
                user.getMealsPerDay(),
                LocalDateTime.of(2020, 1, 29,
                        19, 0),
                LocalDateTime.of(2020, 1, 31,
                        10, 0), 1500);
        final List<UserMealWithExcess> mealsTot = TimeUtil.filteredByStreams(
                user.getMealsPerDay(),
                LocalDateTime.of(2020, 1, 29,
                        19, 0),
                LocalDateTime.of(2020, 1, 31,
                        10, 0), 1500);
        System.out.println(mealsTo.equals(mealsTot));
    }

    /**
     * Method to get.
     *
     * @return the user
     */
    public User getUser() {
        return USER;
    }

    /**
     * Method to before.
     */
    public void before() {
        final LocalDate d2901 = LocalDate.of(2020, 1, 29);
        final LocalDate d3001 = LocalDate.of(2020, 1, 30);
        final LocalDate d3101 = LocalDate.of(2020, 1, 31);

        final LocalDateTime t2909 = LocalDateTime.of(
                2020, 1, 29, 9, 0);
        final LocalDateTime t2919 = LocalDateTime.of(
                2020, 1, 29, 19, 0);
        final LocalDateTime t2923 = LocalDateTime.of(
                2020, 1, 29, 23, 0);
        final LocalDateTime t3010 = LocalDateTime.of(
                2020, 1, 30, 10, 0);
        final LocalDateTime t3013 = LocalDateTime.of(
                2020, 1, 30, 13, 0);
        final LocalDateTime t3020 = LocalDateTime.of(
                2020, 1, 30, 20, 0);
        final LocalDateTime t3100 = LocalDateTime.of(
                2020, 1, 31, 0, 0);
        final LocalDateTime t3110 = LocalDateTime.of(
                2020, 1, 31, 10, 0);
        final LocalDateTime t3113 = LocalDateTime.of(
                2020, 1, 31, 13, 0);
        final LocalDateTime t3120 = LocalDateTime.of(
                2020, 1, 31, 20, 0);
        final UserMeals d29 = new UserMeals();
        final UserMeals d30 = new UserMeals();
        final UserMeals d31 = new UserMeals();
        final int c5 = 5;
        final int c100 = 100;
        final int c400 = 400;
        final int c500 = 500;
        final int c1000 = 1000;
        final String any = "Еда";
        final String lunch = "Ужин";
        final String dinner = "Обед";
        final String breakfast = "Завтрак";
        d29.putMeal(t2909, new UserMeal(t2909.toLocalTime(), breakfast, c100));
        d29.putMeal(t2919, new UserMeal(t2919.toLocalTime(), lunch, c1000));
        d29.putMeal(t2923, new UserMeal(t2923.toLocalTime(), dinner, c1000));
        d30.putMeal(t3010, new UserMeal(t3010.toLocalTime(), any, c100));
        d30.putMeal(t3013, new UserMeal(t3013.toLocalTime(), breakfast, c5));
        d30.putMeal(t3020, new UserMeal(t3020.toLocalTime(), dinner, c1000));
        d31.putMeal(t3100, new UserMeal(t3100.toLocalTime(), any, c400));
        d31.putMeal(t3110, new UserMeal(t3110.toLocalTime(), breakfast, c5));
        d31.putMeal(t3113, new UserMeal(t3113.toLocalTime(), dinner, c1000));
        d31.putMeal(t3120, new UserMeal(t3120.toLocalTime(), lunch, c500));
        USER.putMap(d2901, d29);
        USER.putMap(d3001, d30);
        USER.putMap(d3101, d31);
    }
}
