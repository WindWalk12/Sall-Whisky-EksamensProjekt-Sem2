package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Distilate {
    private final LocalDate fillingDate;
    private final Distillation distillation;
    private ArrayList<Cask> casks = new ArrayList<>();

    public Distilate(LocalDate fillingDate, Distillation distillation, Cask cask) {
        this.fillingDate = fillingDate;
        this.distillation = distillation;
        addCask(cask);
        cask.addDistilate(this);
    }

    public void addCask(Cask cask) {
        if (!casks.contains(cask)) {
            casks.add(cask);
        }
    }
}
