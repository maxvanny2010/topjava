package ru.javawebinar.topjava.action;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.IStore;
import ru.javawebinar.topjava.repository.MemStore;

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

}
