package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            final AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            final User byAdminMail = adminUserController.getByMail("email@mail.ru");
            if (Objects.isNull(byAdminMail)) {
                adminUserController.create(new User(null, "adminName", "emailAdmin@mail.ru", "password", Role.ADMIN));
            }
            System.out.println();
            final MealRestController mealController = appCtx.getBean(MealRestController.class);
            final Meal meal1 = mealController.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 7, 0), "break", 500));
            final Meal meal2 = mealController.create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 11, 0), "lunch", 1000));
            meal2.setDescription("UPDATE");
            mealController.update(meal2, meal2.getId());
            mealController.delete(meal2.getId());
            mealController.delete(meal1.getId());
            List<MealTo> filteredMealsWithExcess =
                    mealController.getBetween(
                            LocalDate.of(2020, Month.JANUARY, 30), LocalTime.of(7, 0),
                            LocalDate.of(2020, Month.JANUARY, 31), LocalTime.of(11, 0));
            filteredMealsWithExcess.forEach(System.out::println);
            System.out.println();
            System.out.println(mealController.getBetween(null, null, null, null));
        }
    }
}
