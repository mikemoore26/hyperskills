package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        CoffeMachine coffeMachine =  new CoffeMachine();

        System.out.println("Write action(buy,fil, take, remaining, exit");
        String action = scanner.nextLine();
        while(!action.equals("exit")){

            coffeMachine.options(action);
            System.out.println("Write action(buy,fil, take, remaining, exit");
            action = scanner.nextLine();
        }



    }



}

class CoffeMachine{

     int milk,beans,water,cups,cupNeeded;
     int cupsDis, money;
     String action = "";
    Scanner scanner = new Scanner(System.in);
    Scanner numScanner = new Scanner(System.in);

    public CoffeMachine(){
        init();
    }

    public  void init(){
        money = 550;
        water = 400;
        milk = 540;
        beans = 120;
        cupsDis = 9;
    }

    public void options(String action){
        if (action.equals("buy")) {
            buy();
        }
        if (action.equals("fill")) {
            fill();
        }
        if (action.equals("take")) {
            take();
        }
        if (action.equals("remaining")) {
            currentState();
        }
    }

    public void currentState(){
        System.out.printf("The coffee maker has:\n"+
                "%d of water\n" +
                "%d of milk\n" +
                "%d of coffee beans\n" +
                "%d of disposable cups\n" +
                "%d of money\n", water,milk,beans,cupsDis,money);
    }

    public void take(){
        System.out.printf("I Gave you %d", money);
        money = 0;
    }

    public void fill(){
        System.out.println("water?");
        water += numScanner.nextInt();
        System.out.println("Milk?");
        milk += numScanner.nextInt();
        System.out.println("beans");
        beans +=numScanner.nextInt();
        System.out.println("Cups?");
        cupsDis += numScanner.nextInt();
    }

    public void buy(){
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ?");
        String choice = scanner.nextLine() ;
        switch(choice) {
            case "1":
                // espresso
                if(water >= 250){
                    if(beans >= 16){
                        System.out.println("I have enough resources, making you a coffee!\n");
                        water -= 250;
                        beans -= 16;
                        cupsDis--;
                        money += 4;
                        break;
                    }
                }
                System.out.println("Sorry not enough resources!");
                break;
            case "2":
                // latte
                if(water >= 350){
                    if(beans >= 20){
                        if(milk >= 75){
                            System.out.println("I have enough resources, making you a coffee!\n");

                            water -= 350;
                            beans -= 20;
                            milk -= 75;
                            cupsDis--;
                            money += 7;
                            break;
                        }
                    }
                }
                System.out.println("Sorry not enough resources!");
                break;
            case "3":
                //capp
                if(water >= 200){
                    if(beans >= 12){
                        if(milk >= 100){
                            System.out.println("I have enough resources, making you a coffee!\n");

                            water -= 200;
                            beans -= 12;
                            milk -= 100;
                            cupsDis--;
                            money += 6;
                            break;
                        }
                    }
                }
                System.out.println("Sorry not enough resources!");
                break;
            default:
                break;
        }

    }

    public void serving(int num ){
        int water = num * 200;
        int milk = 50 * num;
        int beans = 15 * num;
        System.out.printf("for %d cups of coffee\n" +
                "%d cups of water \n %d ml of milk \n %d g of coffee beans  ",num, water,milk,beans);
    }

    public void getCurrentIng(){
        System.out.println("Write how many ml of water the coffee machine has:");
        water = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        milk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        beans = scanner.nextInt();
        System.out.println("Write how many cups of coffee you will need:");
        cupNeeded = scanner.nextInt();
    }

    public boolean makecoffer() {
        if(water >= 200){
            if(beans >= 15){
                if(milk >= 50){
                    water -= 200;
                    beans -= 15;
                    milk -= 50;
                    cups++;
                    return true;
                }
            }
        }

        return false;
    }

    public  void makeCoffee(){
        for(int i = 0; i <= cups; i++){

        }
    }
}
