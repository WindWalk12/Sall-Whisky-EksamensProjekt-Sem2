package application.model;

import java.time.LocalDate;

public class Maltbatch {

    // Simple attributes

    private String cornField;

    private String grainType;

    private LocalDate harvestDate;

    private String farmer;

    private LocalDate maltDate;


    // Constructor

    public Maltbatch(String cornField, String grainType, LocalDate harvestDate, String farmer, LocalDate maltDate) {
        this.cornField = cornField;
        this.grainType = grainType;
        this.harvestDate = harvestDate;
        this.farmer = farmer;
        this.maltDate = maltDate;
    }

    public String getCornField() {
        return cornField;
    }

    public String getGrainType() {
        return grainType;
    }

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public String getFarmer() {
        return farmer;
    }

    public LocalDate getMaltDate() {
        return maltDate;
    }

    public String toString() {
        return grainType + " " + cornField;
    }


}
