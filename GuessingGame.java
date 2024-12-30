import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    public static boolean playRound(int roundNumber, int maxAttempts) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        //to generate random vale 
        int targetNumber = random.nextInt(100) + 1;
        System.out.println("Round " + roundNumber + ": Guess the number between 1 and 100.");
        int attempts = 0;
        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int userGuess = sc.nextInt();
            attempts++;
            if (userGuess == targetNumber) {
                System.out.println("Correct! You've guessed the number in " + attempts + " attempts.");
                // if correct answer detected then we can simply return true for further detection of rounds won
               return true;
            }
             else if (userGuess < targetNumber) {
                System.out.println("Too low! Try again.");
            }
             else {
                System.out.println("Too high! Try again.");
            }
        }
            System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The correct number was " + targetNumber + ".");
            //if answer cannot be detected then we return false
                return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------NUMBER GAME-----------------");
        System.out.println();
        int roundsWon = 0;
        int totalRounds = 0;
        int maxAttempts = 10; 
        while (true) {
            totalRounds++; 
            boolean c=(playRound(totalRounds, maxAttempts));
            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = sc.next();
            System.out.println("----------------------------------------");
            //to check whether the player win or lose
            if (c) { 
                roundsWon++;
            }
            if (playAgain.equalsIgnoreCase("no")) {
                break;
            }
        }
        System.out.println("|    Game Over!");

        System.out.println("|   You played " + totalRounds + " rounds.");
        System.out.println("|    You won " + roundsWon + " rounds.");
        System.out.println("----------------------------------------");
    }
}
