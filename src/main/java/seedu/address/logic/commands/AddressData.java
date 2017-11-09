package seedu.address.logic.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Utility class for sorting the addressbook by address
 */
public class AddressData {

    private static final String dataFilePath = "src\\main\\resources\\tables\\data.csv";
    private static BufferedReader br;
    private static String line;
    private static ArrayList<String> table = new ArrayList<>();

    public static void initTable() throws IOException {
        br = new BufferedReader(new FileReader(dataFilePath));
        while ((line = br.readLine()) != null) {
            table.add(line.trim());
        }
    }

    public static ArrayList<String> getTable() {
        return table;
    }
}
