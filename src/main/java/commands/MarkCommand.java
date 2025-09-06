package commands;

import tasks.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        if (tasks.markTask(index)) {
            ui.showMarked(tasks.getTask(index));
        }
    }
}