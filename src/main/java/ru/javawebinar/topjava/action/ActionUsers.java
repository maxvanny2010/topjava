package ru.javawebinar.topjava.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ActionUsers.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/9/2020
 */
public class ActionUsers extends ActionAbs {
    @Override
    public final void execute(final HttpServletRequest req,
                              final HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/users.jsp")
                .forward(req, resp);

    }
}
