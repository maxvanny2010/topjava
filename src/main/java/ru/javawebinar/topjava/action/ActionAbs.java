package ru.javawebinar.topjava.action;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.IStore;
import ru.javawebinar.topjava.repository.MemStore;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * ActionAbd.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public abstract class ActionAbs implements Action {
    /**
     * field a repo.
     */
    private final IStore<Meal> store = MemStore.getInstance();

    /**
     * Constructor.
     */
    ActionAbs() {
    }

    /**
     * Method to get.
     *
     * @return a store of memory
     */
    public final IStore<Meal> getStore() {
        return this.store;
    }

    /**
     * Method to get user and to set it to a request.
     *
     * @param id  a id
     * @param req a request if id correct
     */
    protected final void setMealInRequest(final String id,
                                          final HttpServletRequest req) {
        if (id != null) {
            final int parseInt = Integer.parseInt(id);
            final Optional<Meal> meal = getStore().findById(parseInt);
            meal.ifPresent(value -> req.setAttribute("meal", value));
        } else {
            throw new RuntimeException("missing id");
        }
    }
}
