package ro.jademy.notetaking;

import ro.jademy.notetaking.models.Category;
import ro.jademy.notetaking.models.Note;
import ro.jademy.notetaking.services.IOService;
import ro.jademy.notetaking.services.SortingService;
import java.io.File;
import java.util.*;
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
            System.out.println("Enter an option:");
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
                    editNote();
                    ioService.writeJson(noteList, notesFile);
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

    private void displayEditMenu() {
        System.out.println();
        System.out.println("+---------------------------------------------+");
        System.out.println("|           NOTE TAKING APPLICATION           |");
        System.out.println("+---------------------------------------------+");
        System.out.println();
        System.out.println("        +-------  EDIT MENU  -------+        ");
        System.out.println("        |  1. Title                 |        ");
        System.out.println("        |  2. Body                  |        ");
        System.out.println("        |  3. Hashtag/s             |        ");
        System.out.println("        |  4. Category              |        ");
        System.out.println("        |  5. Return to Main Menu   |        ");
        System.out.println("        +---------------------------+        ");
    }

    private List<Note> printNotes(List<Note> noteList) {
        noteList.forEach(System.out::println);
        return noteList;
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
        System.out.println("Enter an option:");
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
        System.out.println("Enter an option:");
        byte option = input.nextByte();
        String keyword;
        switch (option) {
            case 1: // Search by note's title
                System.out.println("Title:");
                input.skip("\n");
                keyword = input.nextLine();
                return printNotes(searchNoteByTitle(keyword));
            case 2: // Search by note's hashtag/s
                System.out.println("Hashtag:");
                input.skip("\n");
                keyword = input.nextLine();
                return printNotes(searchNoteByHashtag(keyword));
            case 3: // Search by a given keyword
                System.out.println("Keyword:");
                input.skip("\n");
                keyword = input.nextLine();
                return printNotes(searchNoteByKeyword(keyword));
            case 4: // Return to Main Menu
                initiateNoteApp();
                break;
            default: // For invalid inputs
                System.out.println("Invalid input. Please, choose between [1-4] only!");
        }
        return null;
    }

    private List<Note> searchNoteByTitle(String keyword) {
        return new ArrayList<>((noteList.stream().filter(note -> note.getTitle().toLowerCase()
                .contains(keyword.toLowerCase()))
                .collect(Collectors.toList())));
    }

    private List<Note> searchNoteByHashtag(String keyword) {
        List<Note> tempList = new ArrayList<>();
        for (Note note : noteList) {
            for (String hashtag : note.getHashtagList()) {
                if (hashtag.toLowerCase().contains(keyword.toLowerCase())) {
                    if (!tempList.contains(note)) {
                        tempList.add(note);
                    }
                }
            }
        }
        return tempList;
    }

    private List<Note> searchNoteByKeyword(String keyword) {
        return noteList.stream().filter(note -> note.getBody().toLowerCase()
                .contains(keyword.toLowerCase())).collect(Collectors.toList());
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Edit Menu Implementation ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

    private void editNote() {
        Note currentNote = selectNote(Objects.requireNonNull(searchMenu()));
        System.out.println("Enter an option:");
        displayEditMenu();
        byte option = input.nextByte();
        String keyword;
        switch (option) {
            case 1: // Edit note's title
                System.out.println("New Title:");
                input.skip("\n");
                keyword = input.nextLine();
                editNoteTitle(currentNote, keyword);
                break;
            case 2: // Edit note's body
                System.out.println("New Body:");
                input.skip("\n");
                keyword = input.nextLine();
                editNoteBody(currentNote, keyword);
                break;
            case 3: // Edit note's hashtag List
                System.out.println("New hashtag/s:");
                input.skip("\n");
                keyword = input.nextLine();
                editHashtag(currentNote, keyword);
                break;
            case 4: // Edit note's category
                //TODO implement change category
                break;
            case 5: // Return to Main Menu
                initiateNoteApp();
                break;
            default: // For invalid inputs
                System.out.println("Invalid input. Please, choose between [1-4] only!");
        }
    }

    private Note selectNote(List<Note> notes) {
        for (int i = 0; i < notes.size(); i++) {
            System.out.println((i + 1) + " - " + notes.get(i).getTitle());
        }
        System.out.println("Select the number of a single note:");
        byte selectedNote = input.nextByte();
        return notes.get(selectedNote - 1);
    }

    private void editNoteTitle(Note note, String title) {
        note.setTitle(title);
        note.setModificationDate(System.currentTimeMillis());
    }

    private void editNoteBody(Note note, String body) {
        note.setBody(body);
        note.setModificationDate(System.currentTimeMillis());
    }

    private void editHashtag(Note note, String hashtag) {
        List<String> hashtags = new ArrayList<>(Arrays.asList(hashtag.replace(" ", "")
                .split(",")));
        for (int i = 0; i < hashtags.size(); i++) {
            hashtags.set(i, "#" + hashtags.get(i));
        }
        note.setHashtagList(hashtags);
        note.setModificationDate(System.currentTimeMillis());
    }
}
