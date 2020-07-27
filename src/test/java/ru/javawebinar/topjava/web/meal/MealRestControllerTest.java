package ru.javawebinar.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.TestUtil.readFromJson;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * MealRestControllerTest.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/26/2020
 */
class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_MEALS = MealRestController.REST_MEALS + '/';

    @Autowired
    private MealService mealsService;

    @Test
    void whenGetAllIsOk() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_MEALS))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_MATCHER.contentJson(MEALS));
    }

    @Test
    void whenGetByIdIsOk() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_MEALS + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_MATCHER.contentJson(MEAL1));
    }

    @Test
    void whenDeleteIsOk() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_MEALS + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> this.mealsService.get(MEAL1_ID, USER_ID));
    }

    @Test
    void whenUpdateIsOk() throws Exception {
        final Meal updated = MealTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_MEALS + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        MEAL_MATCHER.assertMatch(this.mealsService.get(MEAL1_ID, USER_ID), updated);
    }

    @Test
    void whenCreateWithLocationIsOk() throws Exception {
        final Meal newMeal = MealTestData.getNew();
        final ResultActions action = perform(MockMvcRequestBuilders.post(REST_MEALS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMeal)))
                .andExpect(status().isCreated());

        final Meal created = readFromJson(action, Meal.class);
        final int newId = created.id();
        newMeal.setId(newId);
        newMeal.setDateTime(created.getDateTime());
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(this.mealsService.get(newId, USER_ID), newMeal);
    }

    @Test
    void whenGetBetweenIsFalls() {
    }
}
