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
        }
        throw new IllegalArgumentException("Unknown command");
    }
}