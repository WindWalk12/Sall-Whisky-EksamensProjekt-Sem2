package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Distilate {
    private final LocalDate fillingDate;
    private ArrayList<Cask> casks = new ArrayList<>();

    public Distilate(LocalDate fillingDate) {
        this.fillingDate = fillingDate;
    }

    public void addCask(Cask cask) {
        if (!casks.contains(cask)) {
            casks.add(cask);
        }
    }
}
