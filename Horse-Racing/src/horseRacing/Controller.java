package horseRacing;

import horseRacing.entity.Horse;
import horseRacing.event.Race;
import horseRacing.field.Track;
import horseRacing.window.Window;

public class Controller {

    private Track track;
    private  Race race;
    private Window window;

    public Controller(Window window) {
        this.window = window;
    }

    public void startGame(){
         track = new Track(100,5, 1000);
         race = new Race(track, this);

//        Horse a = new Horse("Mike");
//        Horse b = new Horse("Naimah");
//        Horse c = new Horse("Terrence");
//        Horse d = new Horse("Tiawanna");
//        Horse e = new Horse("Akeem");
//        Horse f = new Horse("red", 14, 1);
//
//        race.addHorse(a);
//        race.addHorse(b);
//        race.addHorse(c);
//        race.addHorse(d);
//        race.addHorse(e);
//        race.addHorse(f);

    }

    public void addHorsesToRace(int amtHorse){
        for(int i = 0; i<amtHorse; i++){
            race.addHorse(new Horse());
        }

    }



    public void runRace(){
        race.idk();
    }

    public void displayWinner(String winner){
        window.displayWinner(winner);
    }

    public void updateRace(){
       // System.out.println("Controller: UpdateRace");
        window.updateRace();
    }

    public Race getRace(){
        return race;
    }
}
