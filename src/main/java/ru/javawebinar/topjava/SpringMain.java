package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;

/**
 * SpringMain
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/14/2020
 */
public final class SpringMain {

    private SpringMain() {
    }

    public static void main(final String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController admin = appCtx.getBean(AdminRestController.class);
            final MealRestController mealController = appCtx.getBean(MealRestController.class);
            admin.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
            admin.create(new User(null, "вввв", "вввв2@mail.ru", "password", Role.USER));
            admin.create(new User(null, "ааа", "ааа1@mail.ru", "password", Role.USER));
            admin.create(new User(null, "бб", "бб1@mail.ru", "password", Role.USER));
            admin.create(new User(null, "ааа", "ааа2@mail.ru", "password", Role.USER));
            admin.create(new User(null, "вввв", "вввв1@mail.ru", "password", Role.USER));
            admin.create(new User(null, "ббб", "ббб2@mail.ru", "password", Role.USER));
            System.out.println("********************************");
            System.out.println("users");
            admin.getAll();
            System.out.println("********************************");
            final Meal meal = mealController.create(
                    new Meal(LocalDateTime.of(
                            2020, Month.JANUARY, 1, 11, 0), "Завтрак", 800));
            mealController.get(meal.getId());
            meal.setDescription("Обед");
            meal.setCalories(600);
            mealController.update(meal, meal.getId());
            mealController.getAll();
            System.out.println("days");
            System.out.println("********************************");
            System.out.println(mealController.getByStartDay(LocalDate.of(2020, Month.JANUARY, 31)));
            System.out.println("********************************");
            System.out.println(mealController.getByEndDay(LocalDate.of(2020, Month.JANUARY, 30)));
            System.out.println("times");
            System.out.println("********************************");
            System.out.println(mealController.getByStartTime(LocalTime.of(20, 0)));
            System.out.println("********************************");
            System.out.println(mealController.getByEndTime(LocalTime.of(10, 0)));
        }
    }
}
