package duke.ui;

import duke.exception.DukeException;
import duke.task.*;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The Ui class handles user interface-related operations in the Duke application.
 */
public class Ui {
    ArrayList < Task > arr;
    /**
     * Displays an error message indicating a problem with loading tasks.
     */
    public static String showLoadingError() {
       return "Error loading tasks. Initializing with an empty task list."; //fix?
    }

    /**
     * Displays a welcome message when the Duke application is launched.
     */
    public static String showWelcomeMessage() {
        return "Hello! I'm Tango. \nWhat can I do for you today?";
//        System.out.println("-------------------------------");
//        System.out.println("Hello! I'm Tango. \nWhat can I do for you today?");
//        System.out.println("-------------------------------");
    }

    /**
     * Prints the number of tasks in the task list.
     *
     * @param tasks The task list.
     */
    public static String printNumberOfTasks(TaskList tasks) {
        return (tasks.size() == 1 ? " Now you have 1 task in the list." :
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints a message about the deleted task.
     *
     * @param task The deleted task.
     */
    public static String printDeletedTaskMessage(Task task) {
        String taskDetails = "Noted. I've removed this task:\n" +
                "[" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription();
        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            taskDetails += " (by: " + deadlineTask.getBy() + ")";
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            taskDetails += " (from: " + eventTask.getFrom() + " to: " + eventTask.getTo() + ")";
        }

        return taskDetails;
    }

    /**
     * Displays a goodbye message when the user exits the Duke application.
     */
    public static String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to be displayed.
     * @throws DukeException DukeException is thrown to indicate an error.
     */
    public static String showError(String errorMessage) throws DukeException {
        return errorMessage;
    }

    /**
     * Displays the task list.
     *
     * @param tasks The task list to be displayed.
     */
    public static String showTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "You have no tasks in your list!";
        } else {
            return (tasks.size() == 1 ? "Here is the task in your list:" : "Here are the tasks in your list:");
        }
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task marked as done.
     */
    public static String markTask(Task task) {
        return "Nice! I've marked this as done: \n " + "[" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription();
    }

    /**
     * Displays a separator line.
     */
    public static String printDashes() {
        return "-------------------------------";
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task marked as not done.
     */
    public static String unmarkTask(Task task) {
        return "OK, I've marked this task as not done yet: \n " + "[" + task.getType() + "][" + task.getStatusIcon() + "] " + task.getDescription();
    }

    /**
     * Displays a message indicating tasks that match a specified search string.
     *
     * @param task The search string to match tasks against.
     */
    public static String showFoundTasks(String task) {
        return "Here are the tasks matching with \"" + task + "\" in your list: ";
    }

    /**
     * Displays a message indicating no tasks match a specified search string.
     *
     * @param task The search string to match tasks against.
     */
    public static String showNoTasksFound(String task) {
        return "Sorry, you have no items in your tasklist matching with \"" + task + "\"";
    }

    public static String printMessage(String text) {
        return text;
    }
}