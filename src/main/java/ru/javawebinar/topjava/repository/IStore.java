package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.MealTo;

import java.util.List;
import java.util.Optional;

/**
 * IRepository.
 *
 * @param <T> a type of repository
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/7/2020
 */
public interface IStore<T> {
    /**
     * Method to create.
     *
     * @param object a object
     */
    void create(T object);

    /**
     * Method to update.
     *
     * @param object a object.
     */
    void update(T object);

    /**
     * Method to delete.
     *
     * @param object a object
     */
    void delete(T object);

    /**
     * Method to find all object from storage.
     *
     * @return objects by list
     */
    List<MealTo> findAll();

    /**
     * Method to find the object by id.
     *
     * @param id a key of object
     * @return a object or null
     */
    Optional<T> findById(int id);

    /**
     * Method to get.
     *
     * @return a size of storage
     */
    int getSize();
}
