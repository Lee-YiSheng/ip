package commands;

import tasks.TaskList;
import ui.Ui;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The TaskList to operate on.
     * @param ui The Ui for displaying output.
     */
    public abstract void execute(TaskList tasks, Ui ui);

    /**
     * Whether this command should cause the program to exit.
     */
    public boolean isExit() {
        return false;
    }
}