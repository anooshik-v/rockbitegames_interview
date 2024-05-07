package main.notification;

import main.model.Material;

public class WarehouseObserverImp implements WarehouseObserver {
    @Override
    public void onInventoryChange(Material material, int quantity) {
        // in this implementation only a simple informative text is printed to the console.
        System.out.println("Inventory change: " + material.getName() + " - Quantity: " + quantity);
    }
}
