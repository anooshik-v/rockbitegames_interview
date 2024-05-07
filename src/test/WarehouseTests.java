package test;

import jdk.jfr.Name;
import main.model.Material;
import main.model.Warehouse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class WarehouseTests {
    Material iron, gold;
    Warehouse warehouse1;

    @Before
    public void setup() {
        // Create material
        iron = new Material("Iron", "this is description for iron", "iron_icon", 1000);
        gold = new Material("Gold", "this is description for gold", "gold_icon", 300);
        // Create warehouse
        warehouse1 = new Warehouse();

    }

    @Test
    // Adds Materials To Warehouse Successfully
    public void successfulAddMaterialToWarehouseTest() {

        assertEquals(warehouse1.getMaterialQuantity(iron), 0);
        warehouse1.addMaterial(iron, 200);
        warehouse1.addMaterial(gold, 150);

        assertEquals(warehouse1.getMaterialQuantity(iron), 200);
        assertEquals(warehouse1.getMaterialQuantity(gold), 150);
    }

    @Test
    // Fails to Add Material To Warehouse Due to being over maximum capacity
    public void failedAddMaterialToWarehouseOverMaxCapacityTest() {

        assertThrows(IllegalArgumentException.class, () -> warehouse1.addMaterial(iron, 1200));
        assertEquals(warehouse1.getMaterialQuantity(iron), 0);
    }


    @Test
    // Removes Material From Warehouse Successfully and checks the amount is decremented
    public void successfulRemoveMaterialFromWarehouseTest() {
        warehouse1.addMaterial(iron, 200);
        warehouse1.removeMaterial(iron, 100);
        assertEquals(warehouse1.getMaterialQuantity(iron), 100);

    }

    @Test
    // Fails to Remove Material from Warehouse Due to the material amount not being enough
    public void failedRemoveMaterialFromWarehouseTest() {
        warehouse1.addMaterial(iron, 200);

        assertThrows(IllegalArgumentException.class, () -> warehouse1.removeMaterial(iron, 300));
        assertEquals(warehouse1.getMaterialQuantity(iron), 200);
    }

    @Test
    // this test checks for successfully moving material from one warehouse to another (warehouse2)
    public void successfulMoveFromWarehouseTest() {
        Warehouse warehouse2 = new Warehouse();
        warehouse1.addMaterial(iron, 200);
        warehouse1.moveMaterial(iron, 150, warehouse2);
        assertEquals(warehouse1.getMaterialQuantity(iron), 50);
        assertEquals(warehouse2.getMaterialQuantity(iron), 150);

    }


    @Test
    // this test check for failed moving of material from one warehouse to another (warehouse2) due to it being over capacity
    public void failedMoveFromWarehouseTestBecauseOfDestinationMaxCapacity() {
        Warehouse warehouse2 = new Warehouse();
        warehouse1.addMaterial(iron, 700);
        warehouse2.addMaterial(iron, 800);
        assertThrows(IllegalArgumentException.class, () -> warehouse1.moveMaterial(iron, 300, warehouse2));
        assertEquals(700, warehouse1.getMaterialQuantity(iron));
        assertEquals(800, warehouse2.getMaterialQuantity(iron));

    }
}
