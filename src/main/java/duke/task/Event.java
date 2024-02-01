package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class represents a task that spans a specific duration.
 * It is a subclass of the Task class and includes information about the start and end times.
 */
public class Event extends Task {
    /** The LocalDateTime representing the start time of the event. */
    protected LocalDateTime from;
    /** The string representation of the start time (used when parsing or if LocalDateTime parsing fails). */
    protected String fromString;
    /** The LocalDateTime representing the end time of the event. */
    protected LocalDateTime to;
    /** The string representation of the end time (used when parsing or if LocalDateTime parsing fails). */
    protected String toString;

    /**
     * Constructs an Event object with the specified description, start time string, and end time string.
     *
     * @param description The description of the event.
     * @param fromString   The string representation of the start time.
     * @param toString     The string representation of the end time.
     * @throws DukeException If there are issues with the provided description or duration.
     */
    public Event(String description, String fromString, String toString) throws DukeException {
        super(TaskType.E, description);

        this.fromString = fromString.trim();
        this.toString = toString.trim();

        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            this.from = LocalDateTime.parse(fromString, dateTimeFormatter);
            this.to = LocalDateTime.parse(toString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            this.from = null;
            this.to = null;
        }

        validateInputs();
    }

    /**
     * Gets the representation of the start time (either LocalDateTime or the original string).
     *
     * @return The start time as an Object (either LocalDateTime or String).
     */
    public Object getFrom() {
        return (this.from != null) ? this.from : this.fromString;
    }
    /**
     * Gets the representation of the end time (either LocalDateTime or the original string).
     *
     * @return The end time as an Object (either LocalDateTime or String).
     */
    public Object getTo() {
        return (this.to != null) ? this.to : this.toString;
    }

    /**
     * Validates the inputs to ensure that required information is provided.
     *
     * @throws DukeException If validation fails.
     */
    private void validateInputs() throws DukeException {
        if ((from == null && to == null) && (fromString.isEmpty() && toString.isEmpty())) {
            throw new DukeException("You did not mention the duration! Please re-enter correctly!");
        } else if (from == null && fromString.isEmpty()) {
            throw new DukeException("You did not mention from when! Please re-enter correctly!");
        } else if (to == null && toString.isEmpty()) {
            throw new DukeException("You did not mention till when! Please re-enter correctly!");
        } else if (description.isEmpty()) {
            throw new DukeException("You didn't specify the event!");
        }
    }


    /**
     * Gets the LocalDateTime representation of the start time.
     *
     * @return The start time as a LocalDateTime object.
     */
    public LocalDateTime getFromTime() {
        return this.from;
    }

    /**
     * Gets the LocalDateTime representation of the end time.
     *
     * @return The end time as a LocalDateTime object.
     */
    public LocalDateTime getToTime() {
        return this.to;
    }

    /**
     * Gets the string representation of the start time.
     *
     * @return The start time as a string.
     */
    public String getFromString() {
        return this.fromString;
    }

    /**
     * Gets the string representation of the end time.
     *
     * @return The end time as a string.
     */
    public String getToString() {
        return this.toString;
    }

    /**
     * Overrides the toString method to provide a formatted string representation of the Event task.
     *
     * @return Formatted string representation of the Event task.
     */
    @Override
    public String toString() {
        String fromStringFormatted = (from != null) ?
                " from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) :
                (this.fromString != null ? " from: " + this.fromString : "");

        String toStringFormatted = (to != null) ?
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) :
                (this.toString != null ? " to: " + this.toString : "");

        return "Got it. I've added this task:\n [E][" + getStatusIcon() + "] " + getDescription() +
                fromStringFormatted + toStringFormatted; //+ from.getClass() + to.getClass();
    }
}