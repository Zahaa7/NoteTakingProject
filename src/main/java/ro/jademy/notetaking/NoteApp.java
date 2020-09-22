package ro.jademy.notetaking;

import ro.jademy.notetaking.models.Category;
import ro.jademy.notetaking.models.Note;
import ro.jademy.notetaking.services.IOService;
import ro.jademy.notetaking.services.SortingService;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NoteApp {

    private static Scanner input = new Scanner(System.in);
    private final List<Note> noteList;
    private final File notesFile;
    private final IOService ioService = new IOService();
    private final SortingService sortingService = new SortingService();

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
                    viewMenu();
                    break;
                case 3: // Search a note By
                    searchMenu();
                    break;
                case 4: // Display All Notes
                    printNotes(noteList);
                    break;
                case 5: // Edit Note Content
                    break;
                case 6: //  Delete Note
                    break;
                case 7: // Exit Note App
                    System.exit(0);
                default: // For invalid inputs
                    System.out.println("Invalid input. Please, choose between [1-7] only!");
            }
        } while (true);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Printing Implementation ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    private void displayMainMenu() {
        System.out.println();
        System.out.println("+---------------------------------------------+");
        System.out.println("|           NOTE TAKING APPLICATION           |");
        System.out.println("+---------------------------------------------+");
        System.out.println();
        System.out.println("         +-------  MAIN MENU  -------+         ");
        System.out.println("         |   1. Create a New Note    |         ");
        System.out.println("         |   2. View All Notes By    |         ");
        System.out.println("         |   3. Search a note By     |         ");
        System.out.println("         |   4. Display All Notes    |         ");
        System.out.println("         |   5. Edit Note Content    |         ");
        System.out.println("         |   6. Delete Note          |         ");
        System.out.println("         |   7. Exit Note App        |         ");
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
        System.out.println("         | 5. Unfinished Notes       |         ");
        System.out.println("         | 6. Favorite Notes         |         ");
        System.out.println("         | 7. Return to Main Menu    |         ");
        System.out.println("         +---------------------------+         ");
    }

    private void displaySearchMenu() {
        System.out.println();
        System.out.println("+---------------------------------------------+");
        System.out.println("|           NOTE TAKING APPLICATION           |");
        System.out.println("+---------------------------------------------+");
        System.out.println();
        System.out.println("        +-------  SEARCH MENU  -------+        ");
        System.out.println("        |    1. Title                 |        ");
        System.out.println("        |    2. Hashtag               |        ");
        System.out.println("        |    3. Keyword               |        ");
        System.out.println("        |    4. Return to Main Menu   |        ");
        System.out.println("        +-----------------------------+        ");
    }

    private void printNotes(List<Note> notes) {
        noteList.forEach(System.out::println);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Note Creation Implementation ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
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

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ View Menu Implementation ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    private void viewMenu() {
        displayViewMenu();
        byte option = input.nextByte();
        switch (option) {
            case 1: // Creation Date (Newest)
                printNotes(sortingService.newestNotesList(noteList));
                break;
            case 2: // Creation Date (Oldest)
                printNotes(sortingService.oldestNotesList(noteList));
                break;
            case 3: // Edit Date (Newest)
                printNotes(sortingService.newestEditedNotesList(noteList));
                break;
            case 4: // Edit Date (Oldest)
                printNotes(sortingService.oldestEditedNotesList(noteList));
                break;
            case 5: // Unfinished Notes
                printNotes(sortingService.unfinishedNotesList(noteList));
                break;
            case 6: // Favorite Notes
                printNotes(sortingService.favoriteNotesList(noteList));
                break;
            case 7: // Return to Main Menu
                initiateNoteApp();
                break;
            default: // For invalid inputs
                System.out.println("Invalid input. Please, choose between [1-7] only!");
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Search Menu Implementation ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //
    private List<Note> searchMenu() {
        displaySearchMenu();
        byte option = input.nextByte();
        String keyword;
        switch (option) {
            case 1: // Search by note's title
                System.out.println("Title:");
                keyword = input.nextLine();
                return searchNoteByTitle(keyword);
            case 2: // Search by note's hashtag/s
                System.out.println("Hashtag:");
                keyword = input.nextLine();
                return searchNoteByHashtag(keyword);
            case 3: // Search by a given keyword
                System.out.println("Keyword:");
                keyword = input.nextLine();
                return searchNoteByKeyword(keyword);
            case 4: // Return to Main Menu
                initiateNoteApp();
                break;
            default: // For invalid inputs
                System.out.println("Invalid input. Please, choose between [1-4] only!");
        }
        return null;
    }

    private List<Note> searchNoteByTitle(String keyword) {
        return noteList.stream()
                .filter(note -> note.getTitle().contains(keyword)).distinct().collect(Collectors.toList());
    }

    private List<Note> searchNoteByHashtag(String keyword) {
        List<Note> tempList = new ArrayList<>();
        for (Note note : noteList) {
            for (String hashtag : note.getHashtagList()) {
                if (hashtag.equalsIgnoreCase(keyword)) {
                    tempList.add(note);
                } else {
                    System.out.println("No Notes with those hashtags!");
                }
            }
        }
        return tempList;
    }

    private List<Note> searchNoteByKeyword(String keyword) {
        return noteList.stream()
                .filter(note -> note.getBody().contains(keyword)).distinct().collect(Collectors.toList());
    }
}
