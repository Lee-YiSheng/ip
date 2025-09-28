package tasks;

import java.util.ArrayList;
import java.util.List;

import exceptions.PepException;

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

    public int getCount() {
        return tasks.size();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void unmarkTask(int index) throws PepException {
        if (index < 0 || index >= tasks.size()) {
            throw new PepException("Invalid task number. Use 'list' to see valid task numbers.");
        }
        tasks.get(index).markAsNotDone();
    }

    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            return tasks.remove(index);
        }
        return null;
    }

    public List<Task> findTasks(String keyword) {
        List<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }
}