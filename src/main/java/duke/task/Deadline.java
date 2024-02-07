package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class represents a task with a specific deadline.
 * It is a subclass of the Task class and includes information about the deadline.
 */
public class Deadline extends Task {

    /** The LocalDateTime representing the deadline. */
    protected LocalDateTime by;
    /** The string representation of the deadline (used when parsing or if LocalDateTime parsing fails). */
    protected String byString;
    private Ui ui;

    /**
     * Constructs a Deadline object with the specified description and deadline string.
     *
     * @param description The description of the deadline task.
     * @param byString    The string representation of the deadline.
     * @throws DukeException If there are issues with the provided description or deadline.
     */
    public Deadline(String description, String byString, Ui ui) throws DukeException {
        super(TaskType.D, description);
        this.byString = byString.trim();
        this.ui = ui;

        try {
            if (!this.byString.isEmpty()) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                this.by = LocalDateTime.parse(byString, dateTimeFormatter);
            }
        } catch (DateTimeParseException e) {
            this.by = null;
        }

        if (this.by == null && this.byString.isEmpty()) {
            throw new DukeException("By when? You forgot to enter \"/by\"");
        } else if (description.isEmpty()) {
            throw new DukeException("You forgot to enter the task for which you have to complete it by");
        }
    }

    /**
     * Gets the representation of the deadline (either LocalDateTime or the original string).
     *
     * @return The deadline as an Object (either LocalDateTime or String).
     */
    public Object getBy() {
        return (this.by != null) ? this.by : this.byString;
    }
    /**
     * Gets the LocalDateTime representation of the deadline.
     *
     * @return The deadline as a LocalDateTime object.
     */
    public LocalDateTime getByTime() {
        return this.by;
    }

    /**
     * Gets the string representation of the deadline.
     *
     * @return The deadline as a string.
     */
    public String getByString() {
        return this.byString;
    }
    /**
     * Overrides the toString method to provide a formatted string representation of the Deadline task.
     *
     * @return Formatted string representation of the Deadline task.
     */
    public String getMessage() {
        String byStringFormatted = (by != null) ?
                " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")" :
                (this.byString != null ? " (by: " + this.byString + ")" : "");

        return ui.printMessage("Got it. I've added this task:\n [D][" + getStatusIcon() + "] " + getDescription() + byStringFormatted); // + by.getClass();
    }
}