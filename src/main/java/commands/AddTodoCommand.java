package commands;

import tasks.TaskList;
import tasks.Todo;
import ui.Ui;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {

        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        ui.showAdded(todo.toString(), tasks.getCount());
    }
}