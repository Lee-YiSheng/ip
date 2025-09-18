package tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public boolean markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            return true;
        }
        return false;
    }

    public boolean unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            return true;
        }
        return false;
    }

    public int getCount() {
        return tasks.size();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }
}