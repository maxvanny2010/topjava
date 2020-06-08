package ru.javawebinar.topjava.action;

import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.ActionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ActionIndex.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public class ActionIndex extends ActionAbs {
    @Override
    public final void execute(final HttpServletRequest req,
                              final HttpServletResponse resp)
            throws ServletException, IOException {
        final List<MealTo> list = this.getStore().findAll();
        req.setAttribute("list", list);
        req.setAttribute("hats", ActionUtil.firstRow());
        final String path = "WEB-INF/jsp/index.jsp";
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
