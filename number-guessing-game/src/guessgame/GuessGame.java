package guessgame;
import java.util.Scanner;
import java.util.Random;


public class GuessGame {
    public static void start() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int randomNumber = rand.nextInt(1,101);
        int guess;
        int count = 0;
        while (true){
            System.out.print("Guess a number between 1 and 100: ");
            guess = sc.nextInt();
            count++;
            if (guess > 100 || guess < 1){
                System.out.println("Invalid guess! try again");
                continue;
            }
            if (guess < randomNumber){
                System.out.println("Too Low!");
            }
            if (guess > randomNumber){
                System.out.println("Too High!");
            }
            if(guess == randomNumber) {
                System.out.println("\nCongratulations! you guessed it in "+count+" guesses");
                System.out.print("Play again(Y/N): ");
                sc.nextLine();
                if(sc.nextLine().equalsIgnoreCase("Y")){
                    count=0;
                }
                else{
                    System.out.println("Bye!");
                    System.exit(0);
                }
            }
        }
    }
}
