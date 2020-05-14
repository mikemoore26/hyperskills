package horseRacing.util;

public class Util {

    public static int randomNumber(int max, int min){
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}
