package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.CopyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class CopyCommandParser implements Parser<CopyCommand> {

    public static final int INDEX_TARGET = 0;
    public static final int INDEX_CHOICE = 2;

    /**
     * Parses the given {@code String} of arguments in the context of the CopyCommand
     * and returns a CopyCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CopyCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;
        String choice;

        try {
            index = ParserUtil.parseIndex(Character.toString(args.trim().charAt(INDEX_TARGET)));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
        }

        try {
            choice = ParserUtil.parseChoice(args);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.POSSIBLE_CHOICES));
        }

        return new CopyCommand(index, choice);
    }
}
