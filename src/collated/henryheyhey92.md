# henryheyhey92
###### /java/seedu/address/ConfirmBox.java
``` java
public class ConfirmBox {
    private static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(150);
        Label label = new Label();
        label.setText(message);

        //Create two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer
        return answer;
    }
```
###### /java/seedu/address/logic/commands/FindCommandLetter.java
``` java
public class FindCommandLetter extends Command {

    public static final String COMMAND_WORD = "Findletter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommandLetter(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredPersonList(predicate);
        return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommandLetter // instanceof handles nulls
                && this.predicate.equals(((FindCommandLetter) other).predicate)); // state check
    }
```
###### /java/seedu/address/logic/parser/FindCommandLetterParser.java
``` java
public class FindCommandLetterParser implements Parser<FindCommandLetter> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommandLetter parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommandLetter(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

```
###### /java/seedu/address/LoginBox.java
``` java
public class LoginBox {
    private static boolean answer;
    private static Stage window = new Stage();
    private static TextField nameInput = new TextField();
    private static TextField passwordInput = new TextField();

    public static boolean display(String title) {

        //create window
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        //create Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(12);

        //create label for name and password
        Label nameLabel = new Label("User name:");
        GridPane.setConstraints(nameLabel, 1, 2);

        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 1, 3);

        // Name and Password input
        //TextField nameInput = new TextField();
        nameInput.setPromptText("Enter name");
        GridPane.setConstraints(nameInput, 2, 2);

        //TextField passwordInput = new TextField();
        passwordInput.setPromptText("Password");
        GridPane.setConstraints(passwordInput, 2, 3);

        //Create login buttons
        Button yesButton = new Button("Login");
        GridPane.setConstraints(yesButton, 2, 4);

        //String name = nameInput.getText().trim();

        yesButton.setOnAction((ActionEvent e) -> {
            if(isInt(nameInput, passwordInput)){
                answer = true;
                window.close();
            }
        });


        //yesButton.setOnAction(this);

        //Add buttons
        grid.getChildren().addAll(nameLabel, nameInput, passwordLabel, passwordInput, yesButton);
        Scene scene = new Scene(grid, 350, 200);
        window.setScene(scene);
        window.showAndWait();

        return answer;
        //Clicking will set answer and cloe window
    }
    private static boolean isInt(TextField name, TextField pass){
        String name2 = name.getText();
        String pass2 = pass.getText();
        try{
            if(name2.compareTo("NUS")==0 ) {
                if (pass2.compareTo("1234") == 0)
                    return true;
            }else
                return false;
        }catch(NumberFormatException e){
           return false;
        }
        return false;
    }

```
###### /java/seedu/address/MainApp.java
``` java
    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting AddressBook " + MainApp.VERSION);

        logInWin = primaryStage;
        boolean unlock = LoginBox.display("AddressBook Login Page");

        if(unlock) {
            ui.start(primaryStage);
            window = primaryStage;
            window.setOnCloseRequest(e -> {
                e.consume();
                stop();
            });
        }

```
###### /java/seedu/address/MainApp.java
``` java
    @Override
    public void stop() {

        boolean answer = ConfirmBox.display("Exit Check Protocol","Confirm on exiting the program?");

        if(answer) {
            //GoodByeBox.display("Title", "Good bye and have a nice day");
        logger.info("============================ [ Stopping Address Book ] =============================");

            ui.stop();
            try {
                storage.saveUserPrefs(userPrefs);
            } catch (IOException e) {
                logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
            }
            Platform.exit();
            System.exit(0);
        }
```
###### /java/seedu/address/ui/PersonCard.java
``` java
    public PersonCard(ReadOnlyPerson person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        initTags(person);
        bindListeners(person);
```
###### /java/seedu/address/ui/PersonCard.java
``` java
    private void initTags(ReadOnlyPerson person) {
        person.getTags().forEach(tag -> {
            Label tagLabel = new Label(tag.tagName);
            tagLabel.setStyle("-fx-background-color: " + getColorForTag(tag.tagName));
            tags.getChildren().add(tagLabel);
        });
```
###### /java/seedu/address/ui/ResultDisplay.java
``` java
    @Subscribe
    private void handleNewResultAvailableEvent(NewResultAvailableEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        /* Platform.runLater(() -> displayed.setValue(event.message)); */
        displayed.setValue(event.message);
```
###### /java/seedu/address/ui/StatusBarFooter.java
``` java
    public StatusBarFooter(String saveLocation, int totalPersons) {
        super(FXML);
        setSyncStatus(SYNC_STATUS_INITIAL);
        setSaveLocation("./" + saveLocation);
        setTotalPersons(totalPersons);
        registerAsAnEventHandler(this);
```
###### /java/seedu/address/ui/StatusBarFooter.java
``` java
    private void setTotalPersons(int totalPersons) {
        this.totalPersons.setText(totalPersons + " person(s) total");
```
