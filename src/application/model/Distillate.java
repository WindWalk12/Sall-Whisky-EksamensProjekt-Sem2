package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Distillate {
    private final LocalDate fillingDate;
    private final Distillation distillation;
    private ArrayList<Cask> casks = new ArrayList<>();
    private double volume;

    public Distillate(LocalDate fillingDate, Distillation distillation, Cask cask, double volume) {
        this.fillingDate = fillingDate;
        this.distillation = distillation;
        this.volume = volume;
        addCask(cask);
        cask.addDistilate(this);
    }

    public void addCask(Cask cask) {
        if (!casks.contains(cask)) {
            casks.add(cask);
        }
    }

    public double removeFromVolume(double amount) {
        double currentVolume = this.volume;
        if (amount > volume) {
            this.volume = 0.0;
            return currentVolume;
        } else {
            this.volume -= amount;
            return amount;
        }
    }

    public double getVolume() {
        return volume;
    }

    public Distillation getDistillation() {
        return distillation;
    }

    public ArrayList<Cask> getCasks() {
        return new ArrayList<>(casks);
    }

    public LocalDate getFillingDate() {
        return fillingDate;
    }
}
