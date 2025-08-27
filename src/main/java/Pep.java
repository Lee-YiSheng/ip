import javax.swing.*;
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
            } else {
                taskList.addTask(userInput);
                ui.showAdded(userInput);
            }
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
            System.out.println(" What can I do for you?");
            printLine();
        }

        public void showAdded(String task) {
            printLine();
            System.out.println(" added: " + task);
            printLine();
        }

        public void showTaskList(TaskList taskList) {
            printLine();
            for (int i = 0; i < taskList.getCount(); i++) {
                System.out.println(" " + (i + 1) + ". " + taskList.getTask(i));
            }
            printLine();
        }

        public void showGoodbye() {
            printLine();
            System.out.println(" Bye. Hope to see you again soon!");
            printLine();
        }

        private void printLine() {
            System.out.println(line);
        }
    }

    public static class TaskList {
        private final String[] tasks;
        private int count;

        public TaskList() {
            this.tasks = new String[100];
            this.count = 0;
        }

        public void addTask(String task) {
            if (count < tasks.length) {
                tasks[count] = task;
                count++;
            }
        }

        public String getTask(int index) {
            return tasks[index];
        }

        public int getCount() {
            return count;
        }
    }
}