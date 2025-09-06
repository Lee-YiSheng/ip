package commands;

import tasks.TaskList;
import Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showTaskList(tasks);
    }
}