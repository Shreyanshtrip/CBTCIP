import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int totalAttempts = 0;
        int totalScore = 0;
        int rounds = 3;  // Number of rounds

        System.out.println("Welcome to Guess the Number Game!");
        System.out.println("I have selected a random number between " + lowerBound + " and " + upperBound + ". Can you guess it?");

        for (int round = 1; round <= rounds; round++) {
            int number = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0;
            boolean hasGuessedCorrectly = false;

            while (!hasGuessedCorrectly && attempts < 5) {  // Allowing a maximum of 5 attempts per round
                System.out.print("Round " + round + " - Attempt #" + (attempts + 1) + ": Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess < lowerBound || guess > upperBound) {
                    System.out.println("Please guess a number between " + lowerBound + " and " + upperBound + ".");
                } else if (guess < number) {
                    System.out.println("Too low! Try again.");
                } else if (guess > number) {
                    System.out.println("Too high! Try again.");
                } else {
                    hasGuessedCorrectly = true;
                    int roundScore = 6 - attempts;  // Giving points based on the number of attempts
                    totalScore += roundScore;
                    System.out.println("Congratulations! You've guessed the number in " + attempts + " attempts. You scored " + roundScore + " points in this round.");
                }
            }

            totalAttempts += attempts;

            if (round < rounds) {
                System.out.println("Round " + round + " ended. Next round starts!");
            }
        }

        System.out.println("Game Over! You took " + totalAttempts + " attempts in total and scored " + totalScore + " points.");
        scanner.close();
    }
}

