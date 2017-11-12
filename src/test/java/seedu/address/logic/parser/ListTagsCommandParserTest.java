package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.Test;

import seedu.address.logic.commands.ListTagsCommand;

//@@author Labradorites
public class ListTagsCommandParserTest {
    private ListTagsCommandParser parser = new ListTagsCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " e", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListTagsCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " 222", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListTagsCommand.MESSAGE_USAGE));
    }
}
