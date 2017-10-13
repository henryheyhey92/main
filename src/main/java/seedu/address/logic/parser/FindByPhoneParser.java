package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindByPhone;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindByPhone object
 */
public class FindByPhoneParser implements Parser<FindByPhone> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindByPhone
     * and returns an FindByPhone object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindByPhone parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByPhone.MESSAGE_USAGE));
        }

        String[] phoneKeywords = trimmedArgs.split("\\s+");

        return new FindByPhone(new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords)));
    }

}
