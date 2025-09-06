import java.util.Scanner;

public class Pep {
    private final String chatbotName;
    private final Ui ui;
    private final TaskList taskList;

    public Pep(String name) {
        this.chatbotName = name;
        this.ui = new Ui();
        this.taskList = new TaskList();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome(chatbotName);

        boolean isExit = false;
        while (!isExit) {
            String userInput = scanner.nextLine();
            try {
                Command command = Parser.parse(userInput);
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    private int parseIndex(String input) {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        Pep pepBot = new Pep("Pep");
        pepBot.run();
    }

}