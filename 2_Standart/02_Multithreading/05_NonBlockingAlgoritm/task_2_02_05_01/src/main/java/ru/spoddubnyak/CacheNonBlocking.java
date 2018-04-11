package ru.spoddubnyak;

import ru.spoddubnyak.Exceptions.OplimisticException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Class CacheNonBlocking implement non blocking cache.
 *
 * @param <K> This describes my type parameter
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 10.04.2018
 */
public class CacheNonBlocking<K> {
    /**
     * property cacheMap - Map for storage Model's.
    */
    private final Map<K, Model> cacheMap;

    /**
     * property testState - for on test state for testing.
     */
    private boolean testState;

    /**
     * property delay - delay for testing.
     */
    private int delay;
    /**
     * property result - result update.
     */
    private boolean result;

    /**
     * Constructor of new object CacheNonBlocking.
     *
     */
    public CacheNonBlocking() {
        this.testState = false;
        this.cacheMap = new ConcurrentHashMap<>();
        this.delay = 0;
        this.result = false;
    }

    /**
     * Method adding model in cacheMap.
     *
     * @param key - key for add
     * @param value - model for add
     */
    public void add(K key, Model value) {
        this.cacheMap.putIfAbsent(key, value);
    }

    /**
     * Method update element in cacheMap.
     *
     * @param key - key for add
     * @param newFieldForModel - field for update
     *
     * @throws OplimisticException - while trying to make changes Model in class CacheNonBlock
     * @return result update, tru if result is done, false is not done
     */
    public boolean update(K key, String newFieldForModel) throws OplimisticException {
        if (this.cacheMap.containsKey(key)) {
            Model currentModel = this.cacheMap.get(key);
            this.cacheMap.computeIfPresent(key, new BiFunction<K, Model, Model>() {
                @Override
                public Model apply(K k, Model oldModel) {
                    oldModel.incrementVersion();
                    if (testState) {
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (currentModel.getVersion() == oldModel.getVersion()) {
                        currentModel.setField(newFieldForModel);
                        result = true;
                    } else {
                          throw new OplimisticException();
                    }
                    return currentModel;
                }
            });
        }
        return result;
    }

    /**
     * Method remove object out cacheMap by key.
     *
     * @param key - key for delete
     *
     */
    public void delete(K key) {
        this.cacheMap.remove(key);
    }

    /**
     * Method return object cacheMap by key.
     *
     * @param key - key for add
     *
     * @return  object Model, where was removed or null if object don't search by key.
     */
    public Model getModel(K key) {
        return this.cacheMap.get(key);
    }

    /**
     * Method return count eleements in cacheMap.
     *
     * @return  count eleements in cacheMap.
     */
    public int getSize() {
     return this.cacheMap.size();
    }

    /**
     * Method on test state and create delay for testing.
     *
     * @param onOff on or off test state
     * @param delay timer for testing
     */
    public void testStateOnOff(boolean onOff, int delay) {
        this.testState = onOff;
        this.delay = delay;
    }
}