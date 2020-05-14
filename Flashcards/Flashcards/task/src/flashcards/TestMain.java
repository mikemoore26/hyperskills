package flashcards;

import java.io.*;
import java.util.*;

public class TestMain {
    static String def = "";
    static String term = "";
    static String ans = "";
    static String inFile = "";
    static Scanner scanner = new Scanner(System.in);
    static Scanner scanner1 = new Scanner(System.in);
    static Map<String, String> cards = new LinkedHashMap<>();

    public static void TestMain(String[] args) throws IOException {


        while(true) {
            displayMenu();
        }

    }

    public static void displayMenu() throws IOException {
        /*add a card: add,
        remove a card: remove,
        load cards from file: import,
        save cards to file: export,
        ask for a definition of some random cards: ask,
        exit the program: exit.

         */

        System.out.println("Input the action (add, remove, import, export, ask, exit):");
        String selection = scanner1.nextLine();

        switch (selection){
            case "add":
                makeCards();
                break;
            case "remove":
                remove();
                break;
            case "import":
                importFile();
                break;
            case "export":
                exportFile();
                break;
            case "ask":
                ask();
                break;
            case "exit":
                System.out.println("Bye Bye");
                System.exit(0);
            default:
                break;
        }
    }


    public static void importFile() {
        File file = getFile();
        int count = 0;

        Scanner fileScanner = null;
        if(file != null){
            try {
                fileScanner = new Scanner(file);
                while(fileScanner.hasNext()){
                    term = fileScanner.nextLine();
                    def = fileScanner.nextLine();
                    cards.put(term,def);
                    count++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File not found.");
                return;
            }

            System.out.printf("%d cards have been loaded.\n", count);
        }

    }

    public static void exportFile()  {
        File file = getFile();
        int count = 0;

        PrintWriter fileWriter  = null;
        if(file != null) {
            try {
                fileWriter = new PrintWriter(file);
                for (Map.Entry<String, String> temp: cards.entrySet()) {
                    fileWriter.printf("%s\n%s\n", temp.getKey(),temp.getValue() );
                    count++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            System.out.printf("%d cards have been saved\n", count);
            fileWriter.close();
        }



    }

    public static File getFile(){
        System.out.println("File name:");
        String fileName = scanner1.nextLine();
        return new File(fileName);
    }

    public static void ask(){
        System.out.println("How many times to ask?");
        int amt = scanner.nextInt();
        int i = 0;
        while(i < amt) {
            term = getCard();
            def = cards.get(term);
            quiz(term, def);
            i++;
        }
    }

    private static String getCard() {
        Random rand = new Random();
        int r = rand.nextInt(cards.size());

        Set<String> cardsKey = cards.keySet();
        String[] cardsArr = cardsKey.toArray(new String[cards.size()]);

        return cardsArr[r];
    }


    public static void quiz(String term , String def){
        String cor ="";
        System.out.printf("Print the definition of \"%s\":\n", term);
        ans = scanner1.nextLine();

        if(ans.equals(def)){
            System.out.println("Correct answer.");
        }else{

            System.out.printf("Wrong answer, the correct one is \"%s\"", def);

            for(Map.Entry<String, String> temp2 : cards.entrySet()) {

                if (ans.equals(temp2.getValue())) {
                    cor = temp2.getKey();
                    System.out.printf(" you've just written the definition of \"%s\"\n", cor);
                }
            }
            System.out.println();
        }
    }

    public static void remove(){
        System.out.println("The card");
        term = scanner1.nextLine();

        if(cards.containsKey(term)){
            System.out.printf("\"%s\" has been removed\n", term);
            cards.remove(term);
        }else{
            System.out.printf("Can't remove \"%s\": there's no such card\n", term);
        }
    }


    public static void add(String term, String def){
        cards.put(term, def);
        System.out.printf("(\"%s\": \"%s\") has been added\n", term, def);
    }

    public static void  makeCards(){

        System.out.println("The card :");
        term = scanner1.nextLine();

        if(cards.containsKey(term)) {
            System.out.printf("The card \"%s\" already exists.\n", term);
            return;
        }

        System.out.println("The Definition of the card");
        def = scanner1.nextLine();
        if (cards.containsValue(def)) {
            System.out.printf("The definition \"%s\" already exists.\n", def);
            return;
        }

        add(term, def);
    }



    public static void quiz(){
        String cor ="";
        for(Map.Entry<String,String> temp: cards.entrySet()){
            term = temp.getKey();
            def = temp.getValue();

            System.out.printf("Print the definition of \"%s\":\n", term);
            ans = scanner1.nextLine();

            if (ans.equals(def)) {
                System.out.println("correct answer");
            } else {
                System.out.printf("Wrong answer, the correct one is \"%s\"", def);

                for(Map.Entry<String, String> temp2 : cards.entrySet()){

                    if(ans.equals(temp2.getValue())){
                        cor = temp2.getKey();
                        System.out.printf(" you've just written the definition of \"%s\"\n", cor);
                    }

                }
                System.out.println();
            }
        }
    }
}
