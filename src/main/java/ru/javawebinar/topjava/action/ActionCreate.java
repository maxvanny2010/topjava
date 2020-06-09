package ru.javawebinar.topjava.action;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * ActionCreate.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public class ActionCreate extends ActionAbs {
    /**
     * field a logger.
     */
    private static final Logger LOG = getLogger(ActionCreate.class);

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
            final Meal create = this.getStore().create(meal);
            LOG.info("создан:" + create);
        } catch (final Exception e) {
            resp.sendRedirect("/404");
            return;
        }
        resp.sendRedirect("/meals");
    }
}
