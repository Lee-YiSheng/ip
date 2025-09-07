package commands;

import exceptions.PepException;
import tasks.TaskList;
import tasks.Event;
import ui.Ui;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws PepException {
        Event task = new Event(description, from, to);
        if (description == null || description.trim().isEmpty()) {
            throw new PepException("An event needs a description yo, how bout you Try: event <description>");
        }
        tasks.addTask(task);
        ui.showAdded(task.toString(), tasks.getCount());
    }
}