import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;

public class Parser {
    public static Command parse(String userInput) {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("mark ")) {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            return new MarkCommand(index);
        } else if (userInput.startsWith("unmark ")) {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            return new UnmarkCommand(index);
        } else if (userInput.startsWith("todo ")) {
            return new AddTodoCommand(userInput.substring(5));
        } else if (userInput.startsWith("deadline ")) {
            String[] parts = userInput.substring(9).split(" /by ", 2);
            return new AddDeadlineCommand(parts[0], parts[1]);
        } else if (userInput.startsWith("event ")) {
            String[] parts = userInput.substring(6).split(" /from ", 2);
            String[] timeParts = parts[1].split(" /to ", 2);
            return new AddEventCommand(parts[0], timeParts[0], timeParts[1]);
        } else if (userInput.startsWith("delete ")) {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            return new DeleteCommand(index);
        }
        throw new IllegalArgumentException("What in the world did you just sent?");
    }
}