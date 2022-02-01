import java.util.Scanner;

public class Test {
    public static double computePower(int x, int y){
        return Math.pow(x,y);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input an integer:");
        int x = scanner.nextInt();
        System.out.println("To the power of:");
        int y = scanner.nextInt();   
    
        System.out.println("Equals: " + computePower(x, y));
    }
}

