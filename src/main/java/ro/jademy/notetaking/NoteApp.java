package ro.jademy.notetaking;

import ro.jademy.notetaking.models.Category;
import ro.jademy.notetaking.models.Note;
import ro.jademy.notetaking.services.IOService;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NoteApp {

    private static Scanner input = new Scanner(System.in);
    private List<Note> noteList;
    private File notesFile;
    private IOService ioService = new IOService();

    public NoteApp(List<Note> noteList, File notesFile) {
        this.noteList = noteList;
        this.notesFile = notesFile;
    }

    public void initiateNoteApp() {
        do {
            displayMainMenu();
            byte option = input.nextByte();
            switch (option) {
                case 1: // Create a New Note
                    createNewNote();
                    ioService.writeJson(noteList, notesFile);
                    break;
                case 2: // View All Notes By
                    displayViewMenu();
                    break;
                case 3: // View Note Content
                   displayNotes();
                    break;
                case 4: // Edit Note
                    break;
                case 5: // Delete Note
                    break;
                case 6: // Exit Note App
                    System.exit(0);
                    break;
                default: // For invalid inputs
                    System.out.println("Invalid input. Please, choose between [1-6] only!");
            }
        } while (true);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Menu Printing ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
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
        System.out.println("         |   4. Edit Note Content    |         ");
        System.out.println("         |   5. Delete Note          |         ");
        System.out.println("         |   6. Exit Note App        |         ");
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

    private void createNewNote() {
        System.out.println("Title:");
        input.skip("\n");
        String noteTitle = input.nextLine();
        System.out.println("Body:");
        String noteBody = input.nextLine();
        System.out.println("Hash-tag/s:");
        String[] hashtags = input.nextLine().replace(" ", "").split(",");
        for (int i = 0; i < hashtags.length; i++) {
            hashtags[i] = "#" + hashtags[i];
        }
        noteList.add(new Note(noteTitle, noteBody, System.currentTimeMillis(), System.currentTimeMillis(),
                false, false, Category.NO_CATEGORY, Arrays.asList(hashtags)));
    }

    private void displayNotes() {
        noteList.forEach(System.out::println);
    }


}
