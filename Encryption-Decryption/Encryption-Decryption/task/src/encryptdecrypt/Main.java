package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static String alp = "abcdefghijklmnopqrstuvwxyz";
    private static char[] alpArr = alp.toCharArray();
    private static String mode;
    private static int key;
    private static String data;
    private static String alg;
    private static File inFile;
    private static File outFile;
    private static String newData;

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Scanner si = new Scanner(System.in);


        init();
        if(args.length>0){
            int i = 0;
            while(i<args.length){
                if(args[i].equals("-mode")){
                    mode = args[i+1];
                }
                if(args[i].equals("-data")){
                    data = args[i+1];
                }
                if(args[i].equals("-key")){
                    int k = Integer.valueOf(args[i+1]);
                    key = k;
                }
                if(args[i].equals("-in")){
                    inFile = new File(args[i+1]);
                }
                if(args[i].equals("-out")){
                    outFile =  new File(args[i+1]);
                }
                if(args[i].equals(("-alg"))){
                    alg = args[i+1];
                }
                i++;
            }
        }


        if(inFile != null){
            readFIle(inFile);
        }
        if(mode.equals("enc")){
            if(alg.equals("unicode")){
                newData = Encryption.unicode(data,key);
            }else{
                newData = Encryption.shift(data,key);
            }
        }else{
            if(alg.equals("unicode")) {
                newData = Decryption.unicode(data, key);
            }else{
                newData = Decryption.shift(data,key);
            }
        }

        if(outFile != null){
            writeToFIle(outFile);
        }else{
            System.out.println(data);
        }

    }

    public static void init(){
        mode = "enc";
        key = 0;
        alg = "shift";
        data = "";
        inFile = null;
        outFile = null;
    }

    public static void writeToFIle(File file) {
        FileWriter fileWriter = null;
        try {
            fileWriter  = new FileWriter(file);
            fileWriter.write(newData);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void readFIle(File file){
        try {
            Scanner fileScanner = new Scanner(file);
            if(fileScanner.hasNextLine()){
                data += fileScanner.nextLine();
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static String encrypt(String original, int key){
        String newSent = "";


        if(alg.equals("unicode")){

            for(char a : original.toCharArray()){
                char newC = (char) (a + key);
                newSent += newC;
            }

        }else{
            String alp = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            char[] alparr = alp.toCharArray();
            boolean found = false;

            for(char a : original.toCharArray()){
                found = false;
                for(int i = 0; i <alparr.length; i++){
                    if(alparr[i] == a){
                        newSent += (alparr[i+key]);
                        found = true;
                    }
                }
                if(!found){
                    newSent += a;
                }
            }
        }

        return newSent;

    }

    public static String dencrypt(String original, int key){
        String newSent = "";

        if(alg == "unicodw") {
            for (char a : original.toCharArray()) {
                char newC = (char) (a - key);
                newSent += newC;
            }
        }else{
            String alp = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            char[] alparr = alp.toCharArray();
            boolean found = false;

            for(char a : original.toCharArray()){
                for(int i = 0; i <alparr.length; i++){
                    if(alparr[i] == a){
                        newSent += (alparr[i-key]);
                        found = true;
                    }
                }
                if (found == false){
                    newSent += a;
                }
            }
        }
        return newSent;
    }
}