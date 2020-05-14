package horseRacing.field;

import horseRacing.entity.Horse;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

public class Track {

    private int maxHorses;
    private int minHorses;

    private int distance;

    public Track(int maxHorses, int minHorses, int distance) {
        this.maxHorses = maxHorses;
        this.minHorses = minHorses;
        this.distance = distance;
    }

    public int getMaxHorses() {
        return maxHorses;
    }

    public int getMinHorses() {
        return minHorses;
    }

    public int getDistance() {
        return distance;
    }


}
