import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        int[] arr = new int[size];
        for(int i = 0; i < arr.length; i++){
            arr[i] = s.nextInt();
        }

        int m = s.nextInt();
        int n  = s.nextInt();

        boolean flag = true;
        for(int i = 0;i <arr.length-1; i++){
            if(!(i-1 <0) || !(i+1 == arr.length)){
                if(arr[i] == m){
                    if(arr[i+1] == n || arr[i-1] == n){
                        flag = false;
                    }
                }
            }
        }

        System.out.println(flag);
    }
}