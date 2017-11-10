package seedu.address.commons.events.ui;

import seedu.address.commons.events.BaseEvent;

//@@author kikanng
/**
 * Chanage the color theme og the GUI
 */
public class ChangeThemeEvent extends BaseEvent {

    private String theme;

    public String getTheme() {
        return theme;
    }

    public ChangeThemeEvent(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
