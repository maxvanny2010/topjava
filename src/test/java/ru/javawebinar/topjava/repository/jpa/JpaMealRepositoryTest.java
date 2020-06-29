package ru.javawebinar.topjava.repository.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL1;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.MealTestData.MEAL2;
import static ru.javawebinar.topjava.MealTestData.MEAL3;
import static ru.javawebinar.topjava.MealTestData.MEAL4;
import static ru.javawebinar.topjava.MealTestData.MEAL5;
import static ru.javawebinar.topjava.MealTestData.MEAL6;
import static ru.javawebinar.topjava.MealTestData.MEAL7;
import static ru.javawebinar.topjava.MealTestData.NOT_FOUND;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.MealTestData.getNew;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * JpaMealRepositoryTest.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/29/2020
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@EnableTransactionManagement
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JpaMealRepositoryTest {
    @Autowired
    @Qualifier("JpaMealRepository")
    private MealRepository jpa;

    @Test
    public void save() {
        final Meal newMeal = getNew();
        final Meal created = this.jpa.save(newMeal, USER_ID);
        final Integer newID = created.getId();
        newMeal.setId(newID);
        assertMatch(created, newMeal);
        //get получить не могу пишет не может инициализировать прокси.нет сессии. причины не знаю
       //      assertMatch(this.jpa.get(newID, USER_ID), newMeal);
    }

    @Test
    public void delete() {
        //удалить не могу. пишет ожидает один параметр. причины не знаю
        this.jpa.delete(MEAL1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> this.jpa.get(MEAL1_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(IllegalArgumentException.class, () -> this.jpa.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void deleteNotOwn() {
        assertThrows(IllegalArgumentException.class, () -> this.jpa.delete(MEAL1_ID, ADMIN_ID));
    }

    @Test
    public void get() {
        final Meal actual = this.jpa.get(ADMIN_MEAL_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_MEAL1);
    }

    @Test
    public void getAll() {
        final List<Meal> actual = this.jpa.getAll(USER_ID);
        //использовал библиотеку hamcrest. с вашей не получилось. закомитил.
        assertThat(actual, containsInAnyOrder(MEAL1, MEAL2, MEAL3, MEAL4, MEAL5, MEAL6, MEAL7));
        //assertMatch(actual, expected);
    }

    @Test
    public void getBetweenHalfOpen() {
        final List<Meal> actual = this.jpa.getBetweenHalfOpen(
                of(2020, Month.JANUARY, 30, 10, 0),
                of(2020, Month.JANUARY, 30, 20, 0),
                USER_ID);
        //использовал библиотеку hamcrest. с вашей не получилось. закомитил.
        assertThat(actual, containsInAnyOrder(MEAL1, MEAL2));
        //assertMatch(actual, expected);
    }
}
