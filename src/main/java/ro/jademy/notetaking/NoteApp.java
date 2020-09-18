package ro.jademy.notetaking;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class NoteApp {

    private final Path path = Paths.get("notes.json");
    private static final Scanner INPUT = new Scanner(System.in);

    private void displayMainMenu() {
        System.out.println();
        System.out.println("+---------------------------------------------+");
        System.out.println("|           NOTE TAKING APPLICATION           |");
        System.out.println("+---------------------------------------------+");
        System.out.println();
        System.out.println("         +-------  MAIN MENU  -------+         ");
        System.out.println("         |   1. Create a New Note    |         ");
        System.out.println("         |   2. View All Notes By    |         ");
        System.out.println("         |   3. View Note Content    |         ");
        System.out.println("         |   4. Delete Note          |         ");
        System.out.println("         |   5. Exit Note App        |         ");
        System.out.println("         +---------------------------+         ");
    }

    private void displayViewMenu() {
        System.out.println();
        System.out.println("+---------------------------------------------+");
        System.out.println("|           NOTE TAKING APPLICATION           |");
        System.out.println("+---------------------------------------------+");
        System.out.println();
        System.out.println("         +-------  VIEW MENU  -------+         ");
        System.out.println("         | 1. Creation Date (Newest) |         ");
        System.out.println("         | 2. Creation Date (Oldest) |         ");
        System.out.println("         | 3. Edit Date (Newest)     |         ");
        System.out.println("         | 4. Edit Date (Oldest)     |         ");
        System.out.println("         | 5. Return to Main Menu    |         ");
        System.out.println("         +---------------------------+         ");
    }

    public void readJson() {
        System.out.println();
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("notes.json"));
            for (Object o : jsonArray) {
                System.out.println(o.toString());
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
