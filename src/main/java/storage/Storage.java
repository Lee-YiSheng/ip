package storage;

import exceptions.PepException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    public TaskList load() throws PepException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            System.out.println("[DEBUG] Loading from: " + filePath.toAbsolutePath());

            // If file doesn't exist, create folder + file, return empty list
            if (!Files.exists(filePath)) {
                createFileAndDirectory();
                return new TaskList(tasks);
            }

            // Read file line-by-line
            try (Scanner scanner = new Scanner(filePath.toFile())) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (line.isEmpty()) {
                        continue; // skip blank lines
                    }
                    try {
                        Task task = parseTask(line);
                        if (task != null) {
                            tasks.add(task);
                        }
                    } catch (PepException e) {
                        // Skip corrupted line but log it
                        System.err.println("[WARN] Skipping corrupted line: " + line);
                    }
                }
            }

            return new TaskList(tasks);

        } catch (IOException e) {
            throw new PepException("Error loading tasks: " + e.getMessage());
        }
    }

    public void save(TaskList tasks) throws PepException {
        try {
            createFileAndDirectory();
            FileWriter fw = new FileWriter(filePath.toFile());
            for (Task task : tasks.getTasks()) {
                fw.write(formatTask(task) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new PepException("Error saving tasks: " + e.getMessage());
        }
    }

    private void createFileAndDirectory() throws IOException {
        File file = filePath.toFile();
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private Task parseTask(String line) throws PepException {
        // Example format: T | 1 | read book
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new PepException("Corrupted task data: " + line);
        }
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            Task todo = new Todo(description);
            if (isDone) todo.markAsDone();
            return todo;
        case "D":
            if (parts.length < 4) throw new PepException("Missing deadline date in: " + line);
            Task deadline = new Deadline(description, parts[3]);
            if (isDone) deadline.markAsDone();
            return deadline;
        case "E":
            if (parts.length < 5) throw new PepException("Missing event times in: " + line);
            Task event = new Event(description, parts[3], parts[4]);
            if (isDone) event.markAsDone();
            return event;
        default:
            throw new PepException("Unknown task type: " + type);
        }
    }

    private String formatTask(Task task) {
        String status = task.isDone() ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + status + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            return "D | " + status + " | " + task.getDescription() + " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return "E | " + status + " | " + task.getDescription() + " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
        }
        return "";
    }
}