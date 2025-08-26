import javax.swing.*;
import java.util.Scanner;
public class Pep {
    public static void main(String[] args) {
        String chatbotName = "Pep";
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(" Hello! I'm " + chatbotName);
        System.out.println(" I love gyming");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String userInput =  scanner.nextLine();
        while (!(userInput.equals("bye"))) {
            System.out.println(line);
            System.out.println(" " + userInput);
            System.out.println(line);
            userInput =  scanner.nextLine();
        }
        scanner.close();
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}