package ro.jademy.notetaking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ro.jademy.notetaking.model.Note;
import ro.jademy.notetaking.model.TimeStamp;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class NoteApp {

    private static Scanner input = new Scanner(System.in);
    private final ObjectMapper objectMapper = new ObjectMapper();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm a");

    public void initiateNoteApp() {
        do {
            displayMainMenu();
            try {
                byte option = input.nextByte();
                switch (option) {
                    case 1: // Create a New Note
                        try {
                            writeJsonFile();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        break;
                    case 2: // View All Notes By
                        displayViewMenu();
                        break;
                    case 3: // View Note Content

                        break;
                    case 4: // Delete Note

                        break;
                    case 5: // Exit Note App
                        System.exit(0);
                        break;
                    default: // For invalid inputs
                        System.out.println("Invalid input. Please, choose between [1-5] only!");
                }
            } catch (InputMismatchException mismatchException) {
                System.out.println("Invalid input. Please, choose only the displayed options!");
                input = new Scanner(System.in); // to break the loop
            }
        } while (true);
    }


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

    private void readJsonFile() throws IOException {
        byte[] jsonDataFile = Files.readAllBytes(Paths.get("notes.json"));
        Note[] note = objectMapper.readValue(jsonDataFile, Note[].class);
        System.out.println("\n" + Arrays.toString(note));
    }

    private Note createNote() {
        Note note = new Note();
        System.out.println("Title: ");
        input.skip("\n");
        note.setTitle(input.nextLine());
        System.out.println("Text: ");
        note.setBody(input.nextLine());
        //TimeStamp timeStamp = new TimeStamp();
        //timeStamp.setCreationDate();
        //timeStamp.setUpdateDate(input.nextLong()); // de schimbat cu data si ora cand il editeaza
        //note.setTimestamp(timeStamp);
        return note;
    }

    private void writeJsonFile() throws IOException {
        Note newNote = createNote();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.writeValue(new FileOutputStream("notes.json"), newNote);
        System.out.println("File wrote successfully!");
    }
}
