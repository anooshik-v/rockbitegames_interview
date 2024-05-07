# Warehouse Data Structures and API
### Engineer technical interview test 0001

This project was created for Rockbite Games technical interview. Based on the description of the task two models were created. `Warehouse` and `Model`.

`Warehouse` operations are as followed: 
`moveMaterial`, `removeMaterial`, `addMaterial`. To print the material list `printInventory` method is created. Other helper methods are developed for `Warehouse` class to aid with checking the quantity of materials.
Error and validation mechanism is implemented for these methods.

Notification functionality is added that detects and prints out of any change to the warehouse.

A simple successful scenario can be found in the `Main` class. Functional tests and validity of error handling are tested in test package `WarehouseTests` class.
`Junit4` library is used for writing the tests.