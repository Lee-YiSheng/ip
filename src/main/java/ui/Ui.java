package ui;

import tasks.Task;
import tasks.TaskList;

public class Ui {
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

    public void showAdded(String task,  int totalTasks) {
        printLine();
        System.out.println(" added: " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        printLine();


    }

    public void showTaskList(TaskList taskList) {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.getCount(); i++) {
            Task task = taskList.getTask(i);
            System.out.println(" " + (i + 1) + task);
        }
        printLine();
    }

    public void showMarked(Task task) {
        printLine();
        System.out.println(" task marked as done:");
        System.out.println("   " + task);
        printLine();
    }

    public void showUnmarked(Task task) {
        printLine();
        System.out.println(" this task marked as not done:");
        System.out.println("   " + task);
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

    public void showError(String message) {
        System.err.println(message);
    }
}
