package ru.javawebinar.topjava.action;

import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static ru.javawebinar.topjava.util.MealUtil.setCounter;

/**
 * ActionController.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public class ActionDelete extends ActionAbs {
    @Override
    public final void execute(final HttpServletRequest req,
                              final HttpServletResponse resp)
            throws IOException {
        final String id = req.getParameter("id");
        final int idx = Integer.parseInt(id);
        final Optional<Meal> meal = this.getStore().findById(idx);
        if (!meal.isPresent()) {
            resp.sendRedirect("/topjava/404");
            return;
        }
        this.getStore().delete(meal.get());
        if (this.getStore().getSize() == 0) {
            setCounter(0);
        }
        resp.sendRedirect("/topjava/index");
    }
}
