public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}