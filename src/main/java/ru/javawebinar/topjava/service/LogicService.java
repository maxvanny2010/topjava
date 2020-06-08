package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.action.Action;
import ru.javawebinar.topjava.action.Action404;
import ru.javawebinar.topjava.action.ActionAdd;
import ru.javawebinar.topjava.action.ActionCreate;
import ru.javawebinar.topjava.action.ActionDelete;
import ru.javawebinar.topjava.action.ActionEdit;
import ru.javawebinar.topjava.action.ActionException;
import ru.javawebinar.topjava.action.ActionIndex;
import ru.javawebinar.topjava.action.ActionUpdate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * LogicService.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public final class LogicService implements Logic {
    /**
     * field  a instance of LogicService.
     */
    private static final LogicService LOGIC_SERVICE = new LogicService();
    /**
     * field a map.
     */
    private final Map<String, Action> map = new HashMap<>();

    /**
     * Constructor.
     */
    public LogicService() {
        this.map.put("index", new ActionIndex());
        this.map.put("add", new ActionAdd());
        this.map.put("create", new ActionCreate());
        this.map.put("edit", new ActionEdit());
        this.map.put("update", new ActionUpdate());
        this.map.put("delete", new ActionDelete());
        this.map.put("exception", new ActionException());
        this.map.put("404", new Action404());
        this.map.put(null, new ActionIndex());
    }

    /**
     * Method to get.
     *
     * @return the instance of LogicService
     */
    public static LogicService getInstance() {
        return LOGIC_SERVICE;
    }

    @Override
    public void runAction(final String action,
                          final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        this.getAction(action).execute(req, resp);
    }

    /**
     * Method to get action from the Page.
     *
     * @param action a action
     * @return action
     */
    private Action getAction(final String action) {
        return this.map.getOrDefault(action, this.map.get("exception"));
    }
}
