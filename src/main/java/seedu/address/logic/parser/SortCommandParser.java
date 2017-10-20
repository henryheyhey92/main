package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SortCommandParser implements Parser<SortCommand> {

    public static final int INDEX_OPTION = 0;

    public SortCommand parse (String args) throws ParseException{
        try{
            String option = ParserUtil.parseSortOption(args);
            return new SortCommand(option);
        } catch(IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.POSSIBLE_CHOICES));
        }
    }
}
