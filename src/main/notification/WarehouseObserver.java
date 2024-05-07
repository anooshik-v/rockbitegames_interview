package main.notification;

import main.model.Material;

public interface WarehouseObserver
{
    void onInventoryChange(Material material, int quantity);

}
