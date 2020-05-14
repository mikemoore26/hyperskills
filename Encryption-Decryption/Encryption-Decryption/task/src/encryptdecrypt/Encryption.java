package encryptdecrypt;

public class Encryption  {

    public static String unicode(String original, int key){
        String newData ="";

        for(char a : original.toCharArray()){
            char newC = (char) (a + key);
            newData += newC;
        }

        return newData;

    }

    public static String shift(String original, int key){
        String newData ="";
        String alpLc = "abcdefghijklmnopqrstuvwxyz";
        String alpUc =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] alparrUc = alpUc.toCharArray();
        char[] alparrLc = alpLc.toCharArray();
        boolean found = false;

        for(char a : original.toCharArray()){
            found = false;
            for(int i = 0; i <alparrUc.length; i++){
                if(alparrUc[i] == a){
                    int ni = i + key;
                    if(ni > alparrUc.length){
                        ni -= alparrUc.length;
                    }
                    newData += (alparrUc[ni]);
                    found = true;
                }else if(alparrLc[i] == a){
                    int ni = i + key;
                    if(ni > alparrLc.length){
                        ni -= alparrLc.length;
                    }
                    newData += (alparrLc[ni]);
                    found = true;
                }
            }
            if(!found){
                System.out.println(a);
                newData += a;
            }

        }

        return newData;

    }

}
