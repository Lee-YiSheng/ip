package commands;

import tasks.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        if (tasks.unmarkTask(index)) {
            ui.showUnmarked(tasks.getTask(index));
        }
    }
}