package horseRacing.main;

import horseRacing.Controller;
import horseRacing.entity.Horse;
import horseRacing.event.Race;
import horseRacing.field.Track;
import horseRacing.window.Window;

import javax.swing.*;

public class TestMain {

    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window w = new Window();
                Controller controller = new Controller(w);
                w.setController(controller);
            }
        });

    }


    public static void mains(String[] args) {


        Track track = new Track(5,5, 1000);
        Race race = new Race(track);

        Horse a = new Horse("Mike");
        Horse b = new Horse("Naimah");
        Horse c = new Horse("Terrence");
        Horse d = new Horse("Tiawanna");
        Horse e = new Horse("Akeem");
        Horse f = new Horse("red", 14, 1);

        race.addHorse(a);
        race.addHorse(b);
        race.addHorse(c);
        race.addHorse(d);
        race.addHorse(e);
        race.addHorse(f);

        race.startRace();
        race.displayPostion();
    }
}
