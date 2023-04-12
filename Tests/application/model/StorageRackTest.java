package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StorageRackTest {

    Warehouse wh;

    @BeforeEach
    void opsaetning() {
        wh = new Warehouse("Stalden", "Morbærhaven 30");
    }

    @Test
    void TC1_constructor() {
        //Arrange
        int row = 10;
        int col = 10;
        StorageRack storageRack = wh.createStorageRack(row, col);

        //Act
        String actualId = storageRack.getId();
        Warehouse actualWh = storageRack.getWarehouse();

        //Assert
        String expectedId = "1";
        assertEquals(expectedId, actualId);
        assertEquals(wh, actualWh);

    }

    @Test
    void TC2_getShelfs() {
        //Arrange
        int row = 3;
        int col = 3;
        StorageRack storageRack = wh.createStorageRack(row, col);

        //Act
        Map<String, Cask> actualMap = storageRack.getShelfs();

        //Assert
        Map<String, Cask> expectedMap = new LinkedHashMap<>();
        expectedMap.put("1.1", null);
        expectedMap.put("1.2", null);
        expectedMap.put("1.3", null);
        expectedMap.put("2.1", null);
        expectedMap.put("2.2", null);
        expectedMap.put("2.3", null);
        expectedMap.put("3.1", null);
        expectedMap.put("3.2", null);
        expectedMap.put("3.3", null);
        assertEquals(expectedMap, actualMap);
    }

    @Test
    void TC3_putCask_grænseVærdiMinCask() {
        //Arrange
        int row = 3;
        int col = 3;
        StorageRack storageRack = wh.createStorageRack(row, col);
        Cask cask = new Cask("Rusland","Destilleri",70,CaskType.BOURBON);
        storageRack.putCask(cask, 1, 1);

        //Act
        String actualKey = storageRack.getKeyFromValue(cask);

        //Assert
        String expectedKey = "1.1";
        assertEquals(expectedKey, actualKey);
    }

    @Test
    void TC4_putCask_grænseVærdiMaxCask() {
        //Arrange
        int row = 3;
        int col = 3;
        StorageRack storageRack = wh.createStorageRack(row, col);
        Cask cask = new Cask("Rusland","Destilleri",70,CaskType.BOURBON);
        storageRack.putCask(cask, 3, 3);

        //Act
        String actualKey = storageRack.getKeyFromValue(cask);

        //Assert
        String expectedKey = "3.3";
        assertEquals(expectedKey, actualKey);
    }

    @Test
    void TC5_putCask_grænseVærdiMinSpace() {
        //Arrange
        int row = 3;
        int col = 3;
        StorageRack storageRack = wh.createStorageRack(row, col);
        Cask cask = new Cask("Rusland","Destilleri",70,CaskType.BOURBON);
        storageRack.putCask(cask, 1, 1);

        //Act
        int actualSpaceLeft = wh.getAvailableSpaces();

        //Assert
        int expectedSpaceLeft = 8;
        assertEquals(expectedSpaceLeft, actualSpaceLeft);
    }

    @Test
    void TC6_putCask_grænseVærdiMaxSpace() {
        //Arrange
        int row = 3;
        int col = 3;
        StorageRack storageRack = wh.createStorageRack(row, col);
        Cask cask = new Cask("Rusland","Destilleri",70,CaskType.BOURBON);
        storageRack.putCask(cask, 3, 3);

        //Act
        int actualSpaceLeft = wh.getAvailableSpaces();

        //Assert
        int expectedSpaceLeft = 8;
        assertEquals(expectedSpaceLeft, actualSpaceLeft);
    }

    @Test
    void TC7_removeCask_grænseVærdiMinCask() {
        //Arrange
        int row = 3;
        int col = 3;
        StorageRack storageRack = wh.createStorageRack(row, col);
        Cask cask = new Cask("Rusland","Destilleri",70,CaskType.BOURBON);
        storageRack.putCask(cask, 1, 1);
        storageRack.removeCask(cask, 1, 1);

        //Act
        String actualKey = storageRack.getKeyFromValue(cask);

        //Assert
        String expectedKey = "";
        assertEquals(expectedKey, actualKey);
    }

    @Test
    void TC8_removeCask_grænseVærdiMaxCask() {
        //Arrange
        int row = 3;
        int col = 3;
        StorageRack storageRack = wh.createStorageRack(row, col);
        Cask cask = new Cask("Rusland","Destilleri",70,CaskType.BOURBON);
        storageRack.putCask(cask, 3, 3);
        storageRack.removeCask(cask, 3, 3);

        //Act
        String actualKey = storageRack.getKeyFromValue(cask);

        //Assert
        String expectedKey = "";
        assertEquals(expectedKey, actualKey);
    }

    @Test
    void TC9_removeCask_grænseVærdiMinSpace() {
        //Arrange
        int row = 3;
        int col = 3;
        StorageRack storageRack = wh.createStorageRack(row, col);
        Cask cask = new Cask("Rusland","Destilleri",70,CaskType.BOURBON);
        storageRack.putCask(cask, 1, 1);
        storageRack.removeCask(cask, 1, 1);

        //Act
        int actualSpaceLeft = wh.getAvailableSpaces();

        //Assert
        int expectedSpaceLeft = 9;
        assertEquals(expectedSpaceLeft, actualSpaceLeft);
    }

    @Test
    void TC10_removeCask_grænseVærdiMaxSpace() {
        //Arrange
        int row = 3;
        int col = 3;
        StorageRack storageRack = wh.createStorageRack(row, col);
        Cask cask = new Cask("Rusland","Destilleri",70,CaskType.BOURBON);
        storageRack.putCask(cask, 3, 3);
        storageRack.removeCask(cask, 3, 3);

        //Act
        int actualSpaceLeft = wh.getAvailableSpaces();

        //Assert
        int expectedSpaceLeft = 9;
        assertEquals(expectedSpaceLeft, actualSpaceLeft);
    }

    @Test
    void TC11_getKeyFromValue_Pos11() {
        //Arrange
        int row = 3;
        int col = 3;
        StorageRack storageRack = wh.createStorageRack(row, col);
        Cask cask = new Cask("Rusland","Destilleri",70,CaskType.BOURBON);
        storageRack.putCask(cask, 1, 1);

        //Act
        String actualKey = storageRack.getKeyFromValue(cask);

        //Assert
        String expectedKey = "1.1";
        assertEquals(expectedKey, actualKey);
    }

    @Test
    void TC12_getKeyFromValue_Pos33() {
        //Arrange
        int row = 3;
        int col = 3;
        StorageRack storageRack = wh.createStorageRack(row, col);
        Cask cask = new Cask("Rusland","Destilleri",70,CaskType.BOURBON);
        storageRack.putCask(cask, 3, 3);

        //Act
        String actualKey = storageRack.getKeyFromValue(cask);

        //Assert
        String expectedKey = "3.3";
        assertEquals(expectedKey, actualKey);
    }

    @Test
    void TC13_getId() {
        //Arrange
        int row = 10;
        int col = 10;
        StorageRack storageRack = wh.createStorageRack(row, col);

        //Act
        String actualId = storageRack.getId();

        //Assert
        String expectedId = "1";
        assertEquals(expectedId, actualId);
    }

    @Test
    void TC14_getWarehouse() {
        //Arrange
        int row = 10;
        int col = 10;
        StorageRack storageRack = wh.createStorageRack(row, col);

        //Act
        Warehouse actualWh = storageRack.getWarehouse();

        //Assert
        assertEquals(wh, actualWh);
    }

    @Test
    void TC15_createShelfs_Lav9hylder() {
        //Arrange
        int row = 3;
        int col = 3;
        StorageRack storageRack = wh.createStorageRack(row, col);

        //Act
        int actualSpace = storageRack.getShelfs().size();
        Map<String, Cask> actualMap = storageRack.getShelfs();

        //Assert
        int expectedSpace = 9;
        assertEquals(expectedSpace, actualSpace);
        Map<String, Cask> expectedMap = new LinkedHashMap<>();
        expectedMap.put("1.1", null);
        expectedMap.put("1.2", null);
        expectedMap.put("1.3", null);
        expectedMap.put("2.1", null);
        expectedMap.put("2.2", null);
        expectedMap.put("2.3", null);
        expectedMap.put("3.1", null);
        expectedMap.put("3.2", null);
        expectedMap.put("3.3", null);
        assertEquals(expectedMap, actualMap);
    }

    @Test
    void TC16_createShelfs_Lav1hylde() {
        //Arrange
        int row = 1;
        int col = 1;
        StorageRack storageRack = wh.createStorageRack(row, col);

        //Act
        int actualSpace = storageRack.getShelfs().size();
        Map<String, Cask> actualMap = storageRack.getShelfs();

        //Assert
        int expectedSpace = 1;
        assertEquals(expectedSpace, actualSpace);
        Map<String, Cask> expectedMap = new LinkedHashMap<>();
        expectedMap.put("1.1", null);
        assertEquals(expectedMap, actualMap);
    }
}