package ru.javawebinar.topjava.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ActionEdit.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public class ActionEdit extends ActionAbs {
    @Override
    public final void execute(final HttpServletRequest req,
                              final HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            final String id = req.getParameter("id");
            this.setMealInRequest(id, req);
        } catch (RuntimeException e) {
            resp.sendRedirect("/topjava/404");
            return;
        }
        final String path = "WEB-INF/jsp/edit.jsp";
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
