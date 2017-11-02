package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;

/**
 * Lists all the commands entered by user from the start of app launch.
 */
public class ListTagsCommand extends Command {

    public static final String COMMAND_WORD = "listTags";
    public static final String MESSAGE_SUCCESS = "List all tags:\n%1$s";
    public static final String MESSAGE_NO_TAGS = "There are no tags available.";

    @Override
    public CommandResult execute() {
        List<String> listOfTags = new ArrayList<>();//history.getHistory();

        listOfTags.add("testing1");
        listOfTags.add("testing2");
        if (listOfTags.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TAGS);
        }

        //Collections.reverse(listOfTags);
        return new CommandResult(String.format(MESSAGE_SUCCESS, String.join("\n", listOfTags)));
    }

    @Override
    public void setData(Model model, CommandHistory history, UndoRedoStack undoRedoStack) {
        requireNonNull(history);
        this.history = history;
    }
}
