package ru.javawebinar.topjava.action;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.ActionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.filteredByStreams;

/**
 * ActionIndex.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public class ActionMeals extends ActionAbs {
    @Override
    public final void execute(final HttpServletRequest req,
                              final HttpServletResponse resp)
            throws ServletException, IOException {
        final List<Meal> meals = this.getStore().findAll();
        final List<MealTo> mealTo = filteredByStreams(meals,
                LocalTime.MIN, LocalTime.MAX, 1500);
        req.setAttribute("list", mealTo);
        req.setAttribute("hats", ActionUtil.firstRow());
        final String path = "WEB-INF/jsp/meals.jsp";
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
