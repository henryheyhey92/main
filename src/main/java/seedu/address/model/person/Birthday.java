package seedu.address.model.person;

//@@author NUSe0032202
public class Birthday {

    public String birthday;

    //Default when adding a new person
    public Birthday(){
        this.birthday="not initialised";
    }

    //Use this when editing a person's birthday
    public Birthday(String input){
        this.birthday = input;
    }

    public String getBirthday (){
        return birthday;
    }

    //Edit via Java date picker
    public void edit(String input){
        this.birthday = input;
    }

    @Override
    public String toString() {
        return birthday;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && this.birthday.equals(((Birthday) other).birthday)); // state check
    }

    @Override
    public int hashCode() {
        return birthday.hashCode();
    }

}
//@@author
