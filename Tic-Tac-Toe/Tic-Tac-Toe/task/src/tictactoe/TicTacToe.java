package tictactoe;

import java.util.Scanner;

public class TicTacToe {

    private String[][] slots = new String[3][3];
    private String[] input = new String[9];
    private boolean isGameOver = false;
    private String user = "X";


    public TicTacToe(){
        for(int i =0 ; i<3; i++){
            for(int j = 0; j<3; j++){
                slots[i][j] = " ";
            }
        }
    }

    public void runMulti(){

        displayGame();

        while(!isGameOver) {
            getUserChoice();
            displayGame();
            checkWin();
            switchUser();
        }

    }

    public void run(){

       getCells();
       displayGame();


       if(countXO(input)) {

           while(!isGameOver) {
               getUserChoice();
               checkWin();
               displayGame();
               switchUser();
           }
       }

    }

    private void switchUser(){
        if(user.equals("X")){
            user = "O";
        }else{
            user = "X";
        }
    }
    private void getUserChoice(){
        Scanner s = new Scanner(System.in);
        String x[] = new String[2];

        do {
            String promt = "Enter the coordinates:";

            System.out.println(promt);
            String in = s.nextLine();
            x = in.split(" ");
        }while(!checkUserInput(x));
    }

    private boolean checkUserInput(String[] s){
        int x,y;

        try{
            x = Integer.parseInt(s[0]);
            y = Integer.parseInt(s[1]);
        }catch(Exception e){
            System.out.println("You should enter numbers!");
            return false;
        }

        if(x > 3 || x<1 || y<1 || y>3){
            System.out.println("Coordinates should be between 1 - 3");
            return false;
        }

        return playCoord(x, y);
    }

    private  boolean playCoord(int x, int y){
        int i,j;

        i = x - 1;
        j = Math.abs(y - 3);

        if(!slots[j][i].equals("X") && !slots[j][i].equals("O")){

            slots[j][i] = user;
            return  true;
        }

        System.out.println("This cell is occupied! Choose another one!");
        return false;
    }

    public void getCells(){
        System.out.print("Enter Cells: ");
        Scanner s = new Scanner(System.in);
        System.out.println();
        String in = s.nextLine();
        input = in.split("");

        int m = 0;
        for(int i = 0; i<3;i++){
            for(int j = 0; j<3; j++){
                slots[i][j] = input[m];
                m++;
            }
        }

    }

    public void displayGame(){
        StringBuilder s = new StringBuilder();
        int i =0;
        for( i =0; i<3; i++){
            s.append("| ");
            for(int j =0;j<3; j++){
                s.append(slots[i][j] + " ");
                if(j== 2){
                    s.append("|\n");
                }
            }
        }

        System.out.println("---------");
        System.out.println(s.toString());
        System.out.println("---------");
    }


    public boolean countXO(String[] xoArr){
        int x = 0;
        int o = 0;
        for(String a : xoArr){
            if(a.equals("X")){
                x++;
            }else if(a.equals("O")){
                o++;
            }
        }
        if(Math.abs(x-o) >1){
            System.out.println("Impossible");
            return false;
        }
        return true;
    }

    public void checkWin(){

        int winCond = 0;
        String s = "";
        String xo[] = {"X","O"};
        // check vert
        for(int i =0 ; i<3; i++){
            if(slots[i][0].equals(slots[i][1]) && slots[i][1].equals(slots[i][2]) ) {
                for (String a: xo) {
                    if (slots[i][0].equals(a)){
                        System.out.println("wind 1" +a);
                        s = a;
                        winCond++;
                    }
                }
            }
        }

        //check hor
        for(int i =0 ; i<3; i++){
            if(slots[0][i].equals(slots[1][i]) && slots[1][i].equals(slots[2][i])){
                for (String a: xo) {
                    if (slots[0][i].equals(a)){
                        System.out.println("wind 2: " + a);
                        s = a;
                        winCond++;
                    }
                }
            }
        }

        // check \
       if(slots[0][0].equals(slots[1][1]) && slots[0][0].equals(slots[2][2])){
           for (String a: xo) {
               if (slots[1][1].equals(a)){
                   System.out.println("wind 3");
                   s = a;
                   winCond++;
               }
           }
        }

       // check /
        if(slots[0][2].equals(slots[1][1]) && slots[1][1].equals(slots[2][0])){
            for (String a: xo) {
                if (slots[1][1].equals(a)){
                    System.out.println("wind 4");
                    s = a;
                    winCond++;
                }
            }
        }

        if(winCond == 1){
            isGameOver = true;
            System.out.println(String.format("%s wins",user));
           // return String.format("%s wins",user);
        }else if(winCond>1){
            isGameOver = true;
            //System.out.println("Impossible");
            //return "Impossible";
        }else{
            if(isDraw()){
                isGameOver = true;
                System.out.println("Draw!");
              //  return "Draw";
            }
            else {
                //return "Game not finished";
            }
        }

    }

    public boolean isDraw(){
        for(int i = 0; i< 3; i++){
            for(int j=0; j<3;j++){
                if(!slots[i][j].equals("X") && !slots[i][j].equals("O") ){

                    return false;
                }
            }
        }
            return true;
    }
}
