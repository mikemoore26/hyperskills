package horseRacing.entity;

import horseRacing.event.Race;
import horseRacing.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Horse {

    private static int num = 1;
    private String name;
    private String color;
    private int currentSpeed = 0;
    private int topSpeed;
    private int velocity;
    private int horseId;
    private int distance = 0;
    private int totaltDistance = 0;
    private List<Race> raceHistory = new ArrayList<Race>();

    private boolean isRacing = false;


    // condtructors

    public Horse(String name, String color, int topSpeed, int velocity) {
        this.name = name;
        this.color = color;
        this.topSpeed = topSpeed;
        this.velocity = velocity;
        horseId = num;
        num++;
    }

    public Horse(String color, int topSpeed, int velocity) {
        this.color = color;
        this.topSpeed = topSpeed;
        this.velocity = velocity;
        this.horseId = num;
        this.name = "Test Horse Number " + horseId;
        num++;
    }

    public Horse(String name) {
        this.color = "Test Color";
        setTopSpeed(0);
        setVelocity(0);
        this.horseId = num;
        this.name = name;
        num++;
    }

    public Horse() {
        this.color = "Test Color";
        setTopSpeed(0);
        setVelocity(0);
        this.horseId = num;
        this.name = "" + horseId;
        num++;
    }

    // GETTERS AND SETTERS


    public boolean isRacing() {
        return isRacing;
    }

    public void setRacing(boolean racing) {
        isRacing = racing;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTotaltDistance() {
        return totaltDistance;
    }

    public void setTotaltDistance(int totaltDistance) {
        this.totaltDistance = totaltDistance;
    }

    public List<Race> getRaceHistory() {
        return raceHistory;
    }

    public void setRaceHistory(List<Race> raceHistory) {
        this.raceHistory = raceHistory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        int min = 100;
        int max = 200;
        if(topSpeed == 0){
            topSpeed = Util.randomNumber(max,min);
        }
        this.topSpeed = topSpeed;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        int min = 5;
        int max = 20;
        if(velocity == 0){
            velocity = Util.randomNumber(max,min);
        }
        this.velocity = velocity;
    }

    public int getHorseId() {
        return horseId;
    }

    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }

    // public methids

    public void newRace(){
        currentSpeed =0;
        distance = 0;
        isRacing = true;
    }
    public int accelerate(){
        if(currentSpeed<topSpeed){
            currentSpeed += velocity;
            if(currentSpeed >= topSpeed){
                currentSpeed = topSpeed;
            }
        }
        //System.out.println(getName() + " moving at " + currentSpeed );
        return currentSpeed;
    }

    public void move(int distance){
        this.distance += distance;
       // System.out.println("Horse move(): " + name + " current dist: " + distance);
    }
}
