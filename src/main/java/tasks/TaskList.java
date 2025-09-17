package tasks;

import java.util.ArrayList;

import exceptions.PepException;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getCount() {
        return tasks.size();
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTask(int index) throws PepException {
        if (index < 0 || index >= tasks.size()) {
            throw new PepException("Invalid task number. Use 'list' to see valid task numbers.");
        }
        tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) throws PepException {
        if (index < 0 || index >= tasks.size()) {
            throw new PepException("Invalid task number. Use 'list' to see valid task numbers.");
        }
        tasks.get(index).markAsNotDone();
    }

}