package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CaskTest {

    Cask cask;
    Distillation distillation;

    @BeforeEach
    void opsaetning() {
        Maltbatch maltbatch = new Maltbatch("Engen", "Evergreen", LocalDate.of(1999, 12,02), "Lars",LocalDate.now());
        distillation = new Distillation(400, 78, LocalDate.of(2000, 01, 01), LocalDate.now(), "Maja", "", "", maltbatch, "DT1");
        cask = new Cask("Spanien", "Arehucas Rum Distillery", 500, CaskType.RUM);
    }

    @Test
    void TC1_getNewestDate_TomListe() {
        //Act
        LocalDate actual = cask.getNewestDate();

        //Assert
        LocalDate expected = LocalDate.MAX;
        assertEquals(expected, actual);
    }

    @Test
    void TC2_getNewestDate_EtDistillat() {
        //Arrange
        Distillate distillate1 = new Distillate(LocalDate.of(2022, 01, 01), distillation, cask, 20.0);

        //Act
        LocalDate actual = cask.getNewestDate();

        //Assert
        LocalDate expected = LocalDate.of(2022, 01, 01);
        assertEquals(expected, actual);
    }

    @Test
    void TC3_getNewestDate_TreDistillater() {
        //Arrange
        Distillate distillate1 = new Distillate(LocalDate.of(2022, 01, 01), distillation, cask, 20.0);
        Distillate distillate2 = new Distillate(LocalDate.of(2023, 01, 01), distillation, cask, 20.0);
        Distillate distillate3 = new Distillate(LocalDate.of(2020, 01, 01), distillation, cask, 20.0);

        //Act
        LocalDate actual = cask.getNewestDate();

        //Assert
        LocalDate expected = LocalDate.of(2023, 01, 01);
        assertEquals(expected, actual);
    }

    @Test
    void TC4_getContentVolume_TomListe() {
        //Act
        double actual = cask.getContentVolume();

        //Assert
        double expected = 0.0;
        assertEquals(expected, actual);
    }

    @Test
    void TC5_getContentVolume_EtDistillat() {
        //Arrange
        Distillate distillate1 = new Distillate(LocalDate.of(2022, 01, 01), distillation, cask, 20.0);

        //Act
        double actual = cask.getContentVolume();

        //Assert
        double expected = 20.0;
        assertEquals(expected, actual);
    }

    @Test
    void TC6_getContentVolume_TreDistillater() {
        //Arrange
        Distillate distillate1 = new Distillate(LocalDate.of(2022, 01, 01), distillation, cask, 20.0);
        Distillate distillate2 = new Distillate(LocalDate.of(2023, 01, 01), distillation, cask, 20.0);
        Distillate distillate3 = new Distillate(LocalDate.of(2020, 01, 01), distillation, cask, 20.0);

        //Act
        double actual = cask.getContentVolume();

        //Assert
        double expected = 60.0;
        assertEquals(expected, actual);
    }


    @Test
    void TC7_removeFromContentVolume_TomListe_amount10() {
        //Act
        cask.removeFromContentVolume(10);
        double actual = cask.getContentVolume();

        //Assert
        double expected = 0.0;
        assertEquals(expected, actual);
    }

    @Test
    void TC8_removeFromContentVolume_TomListe_amount0() {
        //Act
        cask.removeFromContentVolume(0);
        double actual = cask.getContentVolume();

        //Assert
        double expected = 0.0;
        assertEquals(expected, actual);
    }

    @Test
    void TC9_removeFromContentVolume_TomListe_amountMinus10() {
        //Act
        cask.removeFromContentVolume(-10);
        double actual = cask.getContentVolume();

        //Assert
        double expected = 0.0;
        assertEquals(expected, actual);
    }

    @Test
    void TC10_removeFromContentVolume_EtDistillat_Amount10() {
        //Assert
        Distillate distillate1 = new Distillate(LocalDate.of(2022, 01, 01), distillation, cask, 20.0);

        //Act
        cask.removeFromContentVolume(10);
        double actual = cask.getContentVolume();

        //Assert
        double expected = 10.0;
        assertEquals(expected, actual);
    }

    @Test
    void TC11_removeFromContentVolume_EtDistillat_Amount0() {
        //Assert
        Distillate distillate1 = new Distillate(LocalDate.of(2022, 01, 01), distillation, cask, 20.0);

        //Act
        cask.removeFromContentVolume(0);
        double actual = cask.getContentVolume();

        //Assert
        double expected = 20.0;
        assertEquals(expected, actual);
    }

    @Test
    void TC12_removeFromContentVolume_EtDistillat_AmountMinus10() {
        //Assert
        Distillate distillate1 = new Distillate(LocalDate.of(2022, 01, 01), distillation, cask, 20.0);

        //Act
        cask.removeFromContentVolume(-10);
        double actual = cask.getContentVolume();

        //Assert
        double expected = 20.0;
        assertEquals(expected, actual);
    }

    @Test
    void TC13_removeFromContentVolume_EtDistillat_AmountOverContent() {
        //Assert
        Distillate distillate1 = new Distillate(LocalDate.of(2022, 01, 01), distillation, cask, 20.0);

        //Act
        cask.removeFromContentVolume(30);
        double actual = cask.getContentVolume();

        //Assert
        double expected = 0.0;
        assertEquals(expected, actual);
    }

    @Test
    void TC14_removeFromContentVolume_TreDistillater_Amount30() {
        //Assert
        Distillate distillate1 = new Distillate(LocalDate.of(2022, 01, 01), distillation, cask, 20.0);
        Distillate distillate2 = new Distillate(LocalDate.of(2023, 01, 01), distillation, cask, 20.0);
        Distillate distillate3 = new Distillate(LocalDate.of(2020, 01, 01), distillation, cask, 20.0);

        //Act
        cask.removeFromContentVolume(30);
        double actual = cask.getContentVolume();

        //Assert
        double expected = 30.0;
        assertEquals(expected, actual);
    }

    @Test
    void TC15_removeFromContentVolume_TreDistillater_Amount0() {
        //Assert
        Distillate distillate1 = new Distillate(LocalDate.of(2022, 01, 01), distillation, cask, 20.0);
        Distillate distillate2 = new Distillate(LocalDate.of(2023, 01, 01), distillation, cask, 20.0);
        Distillate distillate3 = new Distillate(LocalDate.of(2020, 01, 01), distillation, cask, 20.0);

        //Act
        cask.removeFromContentVolume(0);
        double actual = cask.getContentVolume();

        //Assert
        double expected = 60.0;
        assertEquals(expected, actual);
    }

    @Test
    void TC16_removeFromContentVolume_TreDistillater_AmountMinus30() {
        //Assert
        Distillate distillate1 = new Distillate(LocalDate.of(2022, 01, 01), distillation, cask, 20.0);
        Distillate distillate2 = new Distillate(LocalDate.of(2023, 01, 01), distillation, cask, 20.0);
        Distillate distillate3 = new Distillate(LocalDate.of(2020, 01, 01), distillation, cask, 20.0);

        //Act
        cask.removeFromContentVolume(-30);
        double actual = cask.getContentVolume();

        //Assert
        double expected = 60.0;
        assertEquals(expected, actual);
    }

    @Test
    void TC17_removeFromContentVolume_TreDistillater_AmountOverContent() {
        //Assert
        Distillate distillate1 = new Distillate(LocalDate.of(2022, 01, 01), distillation, cask, 20.0);
        Distillate distillate2 = new Distillate(LocalDate.of(2023, 01, 01), distillation, cask, 20.0);
        Distillate distillate3 = new Distillate(LocalDate.of(2020, 01, 01), distillation, cask, 20.0);

        //Act
        cask.removeFromContentVolume(70);
        double actual = cask.getContentVolume();

        //Assert
        double expected = 0.0;
        assertEquals(expected, actual);
    }
}