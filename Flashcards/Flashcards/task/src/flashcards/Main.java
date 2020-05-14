package flashcards;

import java.io.*;
import java.util.*;

public class Main {
    private static List<String> log = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Scanner scanner1 = new Scanner(System.in);
    private static List<FlashCard> cards = new ArrayList<FlashCard>();
    private static File exportFile = null;
    private static File importFile = null;


    public static void main(String[] args) throws IOException {

        if(args.length > 0){
            int i = 0;
            while(i < args.length){
                if(args[i].equals("-import")){
                    importFile = new File(args[i+1]);
                }

                if(args[i].equals(("-export"))){
                    exportFile = new File(args[i+1]);
                }
                i++;
            }
        }

        if(importFile != null) {
            startup();
        }

        while(true){
            displayMenu();
        }


    }

    public static void startup(){
        importFile(importFile);
    }

    public static void displayMenu() throws IOException {
        /*add a card: add,
        remove a card: remove,
        load cards from file: import,
        save cards to file: export,
        ask for a definition of some random cards: ask,
        exit the program: exit.

         */

        print("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");

        String selection = scanner1.nextLine();
        log.add(selection);

        switch (selection){
            case "add":
                makeCards();
                break;
            case "remove":
                remove();
                break;
            case "import":
                importFile(getFile());
                break;
            case "export":
                exportFile(getFile());
                break;
            case "ask":
                ask();
                break;
            case "log":
                log();
                break;
            case "hardest card":
                hardestCard();
                break;
            case "reset stats":
                reset();
                break;
            case "exit":
                print("Bye Bye");
                if(exportFile != null) {
                    exportFile(exportFile);
                }

                System.exit(0);
            default:
                break;
        }
    }

    public static void reset(){
        for(FlashCard card: cards){
            card.setMistake(0);
        }
        print("Card statistics has been reset.");
    }

    public static void hardestCard(){
        int hard = 0;
        ArrayList<FlashCard> hardestCard = new ArrayList<FlashCard>();

        for(FlashCard card : cards){
            if(card.getMistake() > hard){
                hard = card.getMistake();
            }
        }

        for(FlashCard card : cards){
            if(card.getMistake() == hard && card.getMistake()>0){
                hardestCard.add(card);
            }
        }

        if(hardestCard.size() == 0){
            print("There are no cards with errors.");

        }else if(hardestCard.size() > 1){
            StringBuilder s = new StringBuilder();
            for(FlashCard card : hardestCard){
                s.append(String.format("\"%s\",", card.getTerm()));
            }
            print(String.format("The Hardest card are %s, You have %d errors answering them", s.toString(), hard));
        }else{
            print(String.format("The Hardest card is \"%s\". %d ", hardestCard.get(0).getTerm(),hard));
        }
    }


    public static void importFile(File file) {
        String term = "";
        String def = "";
        int mistake = 0;

        int count = 0;

        Scanner fileScanner = null;
        if(file != null){
            try {
                fileScanner = new Scanner(importFile);
                while(fileScanner.hasNextLine()){
                    term = fileScanner.next();
                    def = fileScanner.next();
                    mistake = fileScanner.nextInt();
                    fileScanner.nextLine();

                    if(add(term,def,mistake)) {
                        count++;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                print("File not found.");
                return;
            }
        }
        if(count == 0){
            print(String.format("%d cards have been loaded", count));
        }else {
            print(String.format("%d cards have been loaded.", count));
        }
    }

    public static void log()  {
        File file = getFile();
        int count = 0;

        FileWriter fileWriter  = null;
        if(file != null) {
            try {
                fileWriter = new FileWriter(file);
                for (String logEntry : log) {
                    fileWriter.write(logEntry);
                    count++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            print(String.format("%d Logs have been saved", count));
        }
    }

    public static void exportFile(File file)  {
        PrintWriter fileWriter  = null;
        int count = 0;

        if(file != null) {
            try {
                fileWriter = new PrintWriter(file);
                for (FlashCard temp: cards) {

                    fileWriter.printf("%s %s %d\n",temp.getTerm() , temp.getDef(), temp.getMistake());
                    count++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            fileWriter.close();

        }

        print(String.format("%d cards have been saved", count));

    }

    public static File getFile(){
        print("File name:");
        String fileName = scanner1.nextLine();
        log.add(fileName);
        return new File(fileName);
    }

    public static void ask(){
        print("How many times to ask?");

        int amt = scanner.nextInt();
        log.add(String.valueOf(amt));
        int i = 0;
        while(i < amt) {
            quiz(getCard());
            i++;
        }
    }

    private static FlashCard getCard() {
        Random rand = new Random();
        int r = rand.nextInt(cards.size());


        return cards.get(r);
    }


    public static void quiz(FlashCard card){
        String cor ="";
        print(String.format("Print the definition of \"%s\":", card.getTerm()));

        String ans = scanner1.nextLine();

        if(ans.equals(card.getDef())){
            print("Correct answer.");

        }else{
            StringBuilder s = new StringBuilder();
            s.append(String.format("Wrong answer, the correct one is \"%s\"", card.getDef()));
            card.wrongAnswer();

            for(FlashCard temp2 : cards) {

                if (ans.equals(temp2.getDef())) {
                    cor = temp2.getTerm();
                    s.append(String.format(" you've just written the definition of \"%s\"", cor));
                }
            }
            print(s.toString());
        }
    }

    public static void remove(){
        print(String.format("The card"));

        String term = scanner1.nextLine();
        log.add(term);

        Iterator<FlashCard> iter = cards.iterator();
        while (iter.hasNext()){
            FlashCard card = iter.next();
            if(card.getTerm().equals(term)){
                print(String.format("\"%s\" has been removed", term));

                cards.remove(card);
                return;
            }
        }
//        for(FlashCard card : cards){
//            if(card.getTerm().equals(term)){
//                print(String.format("\"%s\" has been removed", term));
//
//                cards.remove(card);
//            }
//        }
        print(String.format("Can't remove \"%s\": there's no such card", term));
    }


    public static void add(String term, String def){
        cards.add(new FlashCard(term, def));
        print(String.format("(\"%s\": \"%s\") has been added", term, def));

    }

    public static boolean add(String term, String def, int mis){
        for (FlashCard card : cards){
            if(card.getTerm().equals(term) && !(card.getDef().equals(def))) {
                card.setDef(def);
                return true;
            }else if(card.getTerm().equals(term)) {
                //print("Can not add this card");
                return false;
            }
        }
        cards.add(new FlashCard(term, def, mis));
        //print(String.format("(\"%s\": \"%s\") has been added", term, def));
        return true;

    }

    public static void  makeCards(){

        print("The card :");

        String term = scanner1.nextLine();
        log.add(term);
        for(FlashCard card: cards){
            if(card.getTerm().equals(term)){
                print(String.format("The Card \"%s\" already exists.", term));
                return;
            }
        }
        print("The Definition of the card");
        String def = scanner1.nextLine();
        log.add(def);

        for(FlashCard card: cards) {
            if (card.getDef().equals(def)) {
                print(String.format("The definition \"%s\" already exists.", def));
                return;
            }
        }

        add(term, def);
    }



//    public static void quiz(){
////        String cor ="";
////        for(Map.Entry<String,String> temp: cards.entrySet()){
////             term = temp.getKey();
////             def = temp.getValue();
////
////             print(String.format("Print the definition of \"%s\":", term));
////
////
////            ans = scanner1.nextLine();
////
////            if (ans.equals(def)) {
////                print("correct answer");
////
////            } else {
////                print(String.format("Wrong answer, the correct one is \"%s\"", def));
////
////                for(Map.Entry<String, String> temp2 : cards()){
////
////                    if(ans.equals(temp2.getValue())) {
////                        cor = temp2.getKey();
////                        print(String.format(" you've just written the definition of \"%s\"", cor));
////                    }
////
////                }
////                System.out.println();
////            }
////        }
////    }

    public static void print(String s){
        System.out.println(s);
        log.add(s);
    }
}
