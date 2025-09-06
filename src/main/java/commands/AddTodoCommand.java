package commands;

import tasks.TaskList;
import tasks.Todo;
import Ui;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task task = new Todo(description);
        tasks.addTask(task);
        ui.showAdded(task.toString(), tasks.getCount());
    }
}