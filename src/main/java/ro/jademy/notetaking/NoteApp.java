package ro.jademy.notetaking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ro.jademy.notetaking.model.Note;
import ro.jademy.notetaking.model.TimeStamp;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
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

    public void readJsonFile() throws IOException {
        byte[] jsonDataFile = Files.readAllBytes(Paths.get("notes.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        Note[] note = objectMapper.readValue(jsonDataFile, Note[].class);
        System.out.println("Note\n" + note);
        Note newNote = createNote();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, newNote);
        System.out.println("Note JSON is\n" + stringWriter);
    }

    private Note createNote() {
        Note note = new Note();
        note.setTitle(INPUT.nextLine());
        note.setBody(INPUT.nextLine());
        TimeStamp timeStamp = new TimeStamp();
        timeStamp.setCreationDate(LocalDate.now());
        timeStamp.setUpdateDate(LocalDate.now()); // de schimbat cu data si ora cand il editeaza
        note.setTimestamp(timeStamp);
        return note;
    }
}
