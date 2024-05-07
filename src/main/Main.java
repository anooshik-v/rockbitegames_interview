package main;

import main.model.Material;
import main.model.Warehouse;
import main.notification.WarehouseObserverImp;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        // Create materials
        Material iron = new Material("Iron", "this is description for iron", "iron_icon", 1000);
        Material copper = new Material("Copper", "this is description for copper", "copper_icon", 800);
        Material bolt = new Material("Bolt", "this is description for bolt", "bolt_icon", 400);
        Material gold = new Material("Gold", "this is description for gold", "gold_icon", 200);

        // Create warehouses
        Warehouse warehouse1 = new Warehouse();
        Warehouse warehouse2 = new Warehouse();
        // Add observer to warehouse1 for notifying the changes
        WarehouseObserverImp observer = new WarehouseObserverImp();
        warehouse1.addObserver(observer);
        // Add materials to warehouse1
        warehouse1.addMaterial(iron, 500);
        warehouse1.addMaterial(copper, 300);

        // Output warehouse1 inventory
        warehouse1.printInventory();

        // Move materials from warehouse1 to warehouse2
        warehouse1.moveMaterial(iron, 200, warehouse2);
        warehouse1.moveMaterial(copper, 100, warehouse2);

        // Output warehouse2 inventory
        warehouse2.printInventory();
    }


}