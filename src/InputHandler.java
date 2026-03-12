import java.util.Scanner;

public class InputHandler {
    public static void start(){
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();
        while(true){
            System.out.println("Enter a two number between 1 and 100:");
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();
            if(num1<=1 || num1>=100){
                System.out.println("Please enter a number between 1 and 100:");
                System.exit(0);
            }
            System.out.println("Enter the operation(addition,subtraction,multiplication,division,exit)(1,2,3,4,5):");
            int choice = sc.nextInt();
            if(choice==1){
                System.out.println("Addition of 2 number: "+calculator.add(num1,num2));
            }
            else if(choice==2){
                System.out.println("Subtraction of 2 number: "+calculator.sub(num1,num2));
            }
            else if(choice==3){
                System.out.println("Multiplication of 2 number: "+calculator.mul(num1,num2));
            }
            else if(choice==4){
                System.out.println("Division of 2 number: "+calculator.div(num1,num2));
            }
            else if (choice==5){
                System.out.println("Exit");
                System.exit(0);
            }
            else{
                System.out.println("Invalid choice");
            }

        }
    }
}
