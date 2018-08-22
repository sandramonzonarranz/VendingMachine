package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Adapter products inside VendingMachine (product - quantity)
 */
public class Inventory<T> {
	
    private Map<T, Integer> inventory = new HashMap<T, Integer>();

    public void add(T product) {
        int count = inventory.get(product);
        inventory.put(product, count + 1);
    }

    public void put(T product, int quantity) {
        inventory.put(product, quantity);
    }

    public void remove(T product) {
        if (hasProduct(product)) {
            int count = inventory.get(product);
            inventory.put(product, count - 1);
        }
    }

    public int getQuantity(T product) {
        Integer value = inventory.get(product);
        return value == null ? 0 : value;
    }

    public boolean hasProduct(T product) {
        return getQuantity(product) > 0;
    }

    public void clear() {
        inventory.clear();
    }

}