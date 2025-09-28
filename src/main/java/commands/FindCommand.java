package commands;

import java.util.List;

import tasks.TaskList;
import tasks.Task;
import ui.Ui;
import exceptions.PepException;

public class FindCommand extends Command {
    private final String description;
    public FindCommand(String description) {
        this.description = description;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) throws PepException {
        if (description == null || description.trim().isEmpty()) {
            throw new PepException("Find command needs a description. Try: find <keyword>");
        }
        List<Task> matches = tasks.findTasks(description.trim());
        ui.showTasks(matches);
    }
}
