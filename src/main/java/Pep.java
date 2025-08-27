import javax.swing.*;
import java.util.Scanner;
public class Pep {
    private final String chatbotName;
    private final Ui ui;

    public Pep(String name) {
        this.chatbotName = name;
        this.ui = new Ui();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        ui.showWelcome(chatbotName);

        String userInput = scanner.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            ui.echo(userInput);
            userInput = scanner.nextLine();
        }

        ui.showGoodbye();
        scanner.close();
    }

    public static void main(String[] args) {
        Pep pepBot = new Pep("Pep");
        pepBot.run();
    }
    public static class Ui {
        private final String line;

        public Ui() {
            this.line = "____________________________________________________________";
        }

        public void showWelcome(String name) {
            printLine();
            System.out.println(" Hello! I'm " + name);
            System.out.println(" I love gyming");
            printLine();
        }

        public void echo(String input) {
            printLine();
            System.out.println(" " + input);
            printLine();
        }

        public void showGoodbye() {
            System.out.println(" Bye. Hope to see you again soon!");
            printLine();
        }

        private void printLine() {
            System.out.println(line);
        }
    }

}

