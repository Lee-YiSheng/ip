public class TaskList {
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