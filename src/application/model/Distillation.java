package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Distillation {
    private double volumen;
    private double alcPercentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String employeeName;
    private String smokingMaterial;
    private String comment;
    private Maltbatch maltBatch;


    private String spiritBatchNr;
    private ArrayList<Distilate> distilates;

    public Distillation(double volumen, double alcPercentage, LocalDate startDate, LocalDate endDate, String employeeName, String smokingMaterial, String comment, Maltbatch maltBatch, String spiritBatchNr) {
        this.volumen = volumen;
        this.alcPercentage = alcPercentage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeName = employeeName;
        this.smokingMaterial = smokingMaterial;
        this.comment = comment;
        this.maltBatch = maltBatch;
        this.spiritBatchNr = spiritBatchNr;
        distilates = new ArrayList<>();
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public double getAlcPercentage() {
        return alcPercentage;
    }

    public void setAlcPercentage(double alcPercentage) {
        this.alcPercentage = alcPercentage;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getSmokingMaterial() {
        return smokingMaterial;
    }

    public void setSmokingMaterial(String smokingMaterial) {
        this.smokingMaterial = smokingMaterial;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Maltbatch getMaltBatch() {
        return maltBatch;
    }

    public void setMaltBatch(Maltbatch maltBatch) {
        this.maltBatch = maltBatch;
    }

    public String getSpiritBatchNr() {
        return spiritBatchNr;
    }

    public Distilate fillCask(LocalDate fillingDate, Distillation distillation, Cask cask){
        Distilate distilate = new Distilate(fillingDate, distillation, cask);
        distilates.add(distilate);
        this.volumen -= cask.getVolume();
        return distilate;
    }

    @Override
    public String toString() {
        return spiritBatchNr + " - volume: " + volumen;
    }
}
