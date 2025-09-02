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

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                ui.showTaskList(taskList);
            } else if (userInput.startsWith("mark ")) {
                int index = parseIndex(userInput);
                if (index != -1 && taskList.markTask(index)) {
                    ui.showMarked(taskList.getTask(index));
                }
            } else if (userInput.startsWith("unmark ")) {
                int index = parseIndex(userInput);
                if (index != -1 && taskList.unmarkTask(index)) {
                    ui.showUnmarked(taskList.getTask(index));
                }
            } else if (userInput.startsWith("todo ")) {
                String description = userInput.substring(5);
                Task task = new Todo(description);
                taskList.addTask(task);
                ui.showAdded(String.valueOf(task), taskList.getCount());
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ", 2);
                if (parts.length == 2) {
                    Task task = new Deadline(parts[0], parts[1]);
                    taskList.addTask(task);
                    ui.showAdded(String.valueOf(task), taskList.getCount());
                }
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from ", 2);
                if (parts.length == 2) {
                    String[] timeParts = parts[1].split(" /to ", 2);
                    if (timeParts.length == 2) {
                        Task task = new Event(parts[0], timeParts[0], timeParts[1]);
                        taskList.addTask(task);
                        ui.showAdded(String.valueOf(task), taskList.getCount());
                    }
                }
            }

            userInput = scanner.nextLine();
        }

        ui.showGoodbye();
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