package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.ui.GoogleAddressEvent;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;

//@@author Labradorites
public class GoogleAddressCommand extends Command {
    public static final String COMMAND_WORD = "gAddress";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches for person's address in GoogleMaps.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Search %1$s on GoogleMaps";

    private final Index targetIndex;

    public GoogleAddressCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() throws CommandException {
        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        ReadOnlyPerson personToSearch = lastShownList.get(targetIndex.getZeroBased());
        EventsCenter.getInstance().post(new GoogleAddressEvent(personToSearch));
        return new CommandResult(String.format(MESSAGE_SUCCESS, personToSearch.getName()));
    }
}
