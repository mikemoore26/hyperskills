package horseRacing.event;

import horseRacing.Controller;
import horseRacing.entity.Horse;
import horseRacing.field.Track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Race implements Runnable{

    private static int num = 100;
    private int raceId = num;
    private Track track;
    private Horse[] positons;
    private List<Horse> horses = new ArrayList<Horse>();
    private int maxHorses;
    private Controller controller;

    private Thread raceThread ;
    private boolean isStarted= false;
    private boolean isFinished = false;

    public Race(Track track, Controller controller){
        this.controller = controller;
        this.track = track;
        this.raceThread = new Thread(this,"race");
        maxHorses = track.getMaxHorses();
        positons = new Horse[maxHorses];
        num++;

    }

    public Race(Track track){
        this.track = track;
        maxHorses = track.getMaxHorses();
        num++;

    }

    public int getRaceId() {
        return raceId;
    }

    public Track getTrack() {
        return track;
    }

    public List<Horse> getHorses(){
        return Collections.unmodifiableList(horses);
    }

    public boolean addHorse(Horse horse){
        if(horse == null){
            System.out.println("Horse can not be null");
            return false;
        }else if( horses.contains(horse)){
            System.out.println("horse is already in the race.");
            return false;
        }else if(track.getMaxHorses() == horses.size()){
            System.out.println("Theres no more space to add a horse: " + horse.getName());
            return false;
        }

        System.out.printf("'%s' has been added to the race. id= %d \n", horse.getName(), horse.getHorseId());
        return horses.add(horse);
    }

    public boolean removeHorse(Horse horse){
        if(horses.contains(horse)){
            Iterator<Horse> iter = horses.iterator();
            while(iter.hasNext()){
                if(iter.next() == horse){
                    iter.remove();
                    return true;

                }

            }

        }

        return false;
    }

    public void startRace(){

        int seconds = 1000;
        int place = 0;
        if(!verifyRace()){
            return;
        }

        isStarted = true;

        int i = 1;
        System.out.println("Race has started");

        while(!isFinished){
            System.out.println(isFinished);

            System.out.println(i + " second");
            displaycCurrentPostion();
            try{
               raceThread.sleep(seconds);
                controller.updateRace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(Horse horse: horses){
                if(horse.isRacing()){
                    horse.move((int) horse.accelerate()*(seconds/1000));
                    //System.out.println(horse.getName() +" ACC" + horse.accelerate() + " SEC" +  (seconds/1000) + " dist " + horse.accelerate() * (seconds/1000));
                    if(horse.getDistance() >= track.getDistance()){
                        System.out.println(horse.getName() + " finished the race in position #" + (place+1));
                        positons[place] = horse;
                        place++;
                        horse.setRacing(false);
                    }
                }
            }
            raceOver();
        }
    }

    public void displaycCurrentPostion(){
        System.out.println("**********");
        System.out.println("CURRENT STANDINGS");
        System.out.println("Target Distance: " + track.getDistance() );
        for(int i = 0; i < horses.size(); i++){
            System.out.println(horses.get(i).getName() + ". " + horses.get(i).getDistance());
        }
        System.out.println("**********");
    }

    public void displayPostion(){
        System.out.println();
        for(int i = 0; i < positons.length; i++){
            System.out.println(i+1 + ". " +  positons[i].getName());
            if(i==0){
                controller.displayWinner("" + positons[0].getName() + " id:" + positons[0].getHorseId());
            }
        }
    }
    // private methods

    private boolean raceOver(){
        for(Horse horse: horses){
            if(horse.isRacing()){
                return  false;
            }
        }
        isFinished = true;
        displayPostion();
        return true;
    }

    private boolean verifyRace(){
        if(horses.size()> track.getMaxHorses()){
            System.out.println(raceId + ": Too many horses in the race");
            return false;
        }

        if(horses.size() < track.getMinHorses()){
            System.out.println(raceId + ": Need More Horses");
            return false;
        }

        for(Horse horse: horses){
            horse.newRace();
        }

        System.out.println("Race ID: " + raceId + " is good to start!");
        return true;
    }
    public void idk(){
        raceThread.start();
    }

    @Override
    public void run() {
        System.out.println("Rsce run()");
        positons = new Horse[horses.size()];
        startRace();

    }
}
