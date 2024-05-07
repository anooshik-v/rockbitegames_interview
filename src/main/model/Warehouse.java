package main.model;

import main.notification.WarehouseObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warehouse {
    // I used HashMap to store the inventory of materials in the Warehouse class for efficient lookup operations
    private Map<Material, Integer> inventory;
    private List<WarehouseObserver> observers;

    public Warehouse() {
        inventory = new HashMap<>();
        observers = new ArrayList<>();
    }

    // Add observer to the warehouse
    public void addObserver(WarehouseObserver observer) {
        observers.add(observer);
    }

    // Remove observer from the warehouse
    public void removeObserver(WarehouseObserver observer) {
        observers.remove(observer);
    }

    // Notify observers about inventory change
    private void notifyObservers(Material material, int quantity) {
        for (WarehouseObserver observer : observers) {
            observer.onInventoryChange(material, quantity);
        }
    }

    // Add material to the warehouse
    public void addMaterial(Material material, int quantity) {
        int currentQuantity = inventory.getOrDefault(material, 0);
        int newQuantity = currentQuantity + quantity;
        validateCapacity(material, newQuantity);
        inventory.put(material, newQuantity);
        notifyObservers(material, newQuantity);
    }

    // Remove material from the warehouse
    public void removeMaterial(Material material, int quantity) {
        int currentQuantity = inventory.getOrDefault(material, 0);
        if (currentQuantity >= quantity) {
            int newQuantity = currentQuantity - quantity;
            inventory.put(material, newQuantity);
            notifyObservers(material, newQuantity);
        } else {
            throw new IllegalArgumentException("Not enough " + material.getName() + " in the warehouse.");
        }
    }

    // Move material from one warehouse to another
    public void moveMaterial(Material material, int quantity, Warehouse destination) {
        int currentQuantity = inventory.getOrDefault(material, 0);
        if (currentQuantity >= quantity) {
            // validate the capacity to the destination before moving starts
            destination.validateCapacity(material, quantity + destination.getMaterialQuantity(material));
            removeMaterial(material, quantity);
            destination.addMaterial(material, quantity);
        } else {
            throw new IllegalArgumentException("Not enough " + material.getName() + " in the warehouse.");
        }
    }

    // Method to get quantity of a material in the warehouse
    public int getMaterialQuantity(Material material) {
        return inventory.getOrDefault(material, 0);
    }

    // Method to output current warehouse data
    public void printInventory() {
        System.out.println("Warehouse Inventory:");
        for (Material material : inventory.keySet()) {
            int quantity = inventory.get(material);
            System.out.println(material.getName() + ": " + quantity);
        }
    }

    // Validate capacity of the warehouse for a material
    private void validateCapacity(Material material, int quantity) {
        if(quantity< 0)
            throw new IllegalArgumentException(quantity + " is an invalid quantity.");

        int maxCapacity = material.getMaxCapacity();

        if (quantity > maxCapacity) {
            throw new IllegalArgumentException(material.getName() + " exceeds max capacity of the warehouse.");
        }
    }
}
