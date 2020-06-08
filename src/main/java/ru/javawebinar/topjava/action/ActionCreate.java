package ru.javawebinar.topjava.action;

import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * ActionCreate.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public class ActionCreate extends ActionAbs {
    @Override
    public final void execute(final HttpServletRequest req,
                              final HttpServletResponse resp)
            throws IOException {
        final String desc = req.getParameter("desc").trim();
        final String dates = req.getParameter("dates");
        final String calorie = req.getParameter("calories");
        try {
            final LocalDateTime dateTime = LocalDateTime.parse(dates);
            final int calories = Integer.parseInt(calorie);
            final Meal meal = new Meal(dateTime, desc, calories);
            this.getStore().create(meal);
        } catch (final Exception e) {
            resp.sendRedirect("/topjava/404");
            return;
        }
        resp.sendRedirect("/topjava/index");
    }
}
