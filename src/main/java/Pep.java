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
            } else {
                taskList.addTask(new Task(userInput));
                ui.showAdded(userInput);
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

    // ==================== Ui Class ====================
    public static class Ui {
        private final String line;

        public Ui() {
            this.line = "____________________________________________________________";
        }

        public void showWelcome(String name) {
            printLine();
            System.out.println(" Hi im" + name);
            System.out.println(" What you want");
            printLine();
        }

        public void showAdded(String task) {
            printLine();
            System.out.println(" added: " + task);
            printLine();
        }

        public void showTaskList(TaskList taskList) {
            printLine();
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskList.getCount(); i++) {
                Task task = taskList.getTask(i);
                System.out.println(" " + (i + 1) + ".[" + task.getStatusIcon() + "] " + task.getDescription());
            }
            printLine();
        }

        public void showMarked(Task task) {
            printLine();
            System.out.println(" task marked as done:");
            System.out.println("   [" + task.getStatusIcon() + "] " + task.getDescription());
            printLine();
        }

        public void showUnmarked(Task task) {
            printLine();
            System.out.println(" this task marked as not done:");
            System.out.println("   [" + task.getStatusIcon() + "] " + task.getDescription());
            printLine();
        }

        public void showGoodbye() {
            printLine();
            System.out.println(" Bye. please dont come back");
            printLine();
        }

        private void printLine() {
            System.out.println(line);
        }
    }

    // ==================== TaskList Class ====================
    public static class TaskList {
        private final Task[] tasks;
        private int count;

        public TaskList() {
            this.tasks = new Task[100];
            this.count = 0;
        }

        public void addTask(Task task) {
            if (count < tasks.length) {
                tasks[count] = task;
                count++;
            }
        }

        public boolean markTask(int index) {
            if (index >= 0 && index < count) {
                tasks[index].markAsDone();
                return true;
            }
            return false;
        }

        public boolean unmarkTask(int index) {
            if (index >= 0 && index < count) {
                tasks[index].markAsNotDone();
                return true;
            }
            return false;
        }

        public Task getTask(int index) {
            return tasks[index];
        }

        public int getCount() {
            return count;
        }
    }

    // ==================== Task Class ====================
    public static class Task {
        private final String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsNotDone() {
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public String getDescription() {
            return description;
        }
    }
}