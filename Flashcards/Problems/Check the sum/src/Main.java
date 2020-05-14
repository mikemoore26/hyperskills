import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        Scanner s = new Scanner(System.in);
        int a,b,c;

        a = s.nextInt();
        b = s.nextInt();
        c = s.nextInt();

        System.out.println(a + b == 20 || a + c == 20 || b + c == 20);
    }

    public static boolean equal20(int a, int b, int c){
        if(a + b == 20 || a + c == 20 || b + c == 20){
            return true;
        }
            return false;
    }
}