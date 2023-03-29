package application.model;

public class WhiskyBatch {
    private String id;
    private double waterVolume;
    private double totalVolume;
    private boolean caskStrength;
    private double alcPrercntage;
    private String whiskyType;
    private static int batchCounter = 0;

    public WhiskyBatch(double waterVolume, double totalVolume, boolean caskStrength, double alcPrercntage, String whiskyType) {
        batchCounter++;
        this.id = String.valueOf(batchCounter);
        this.waterVolume = waterVolume;
        this.totalVolume = totalVolume;
        this.caskStrength = caskStrength;
        this.alcPrercntage = alcPrercntage;
        this.whiskyType = whiskyType;
    }
}
