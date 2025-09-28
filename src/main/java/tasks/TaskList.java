package tasks;

import java.util.ArrayList;
import java.util.List;

import exceptions.PepException;

/**
 * Represents the list of tasks managed by the chatbot.
 * Provides methods to add, delete, mark, unmark, and search tasks.
 */
public record TaskList(ArrayList<Task> tasks) {
    public TaskList() {
        this(new ArrayList<>());
    }

    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
        }
    }

    public void markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
        }
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

    /**
     * Deletes the task at the specified index from the task list.
     * Marks the task as not done before removal.
     *
     * @param index the zero-based index of the task to delete
     * @return the removed Task, or null if the index is invalid
     */
    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsNotDone();
            return tasks.remove(index);
        }
        return null;
    }
    /**
     * Finds all tasks whose description contains the given keyword.
     *
     * @param keyword the keyword to search for
     * @return a list of matching tasks, or an empty list if none found
     */
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