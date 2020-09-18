package ro.jademy.notetaking;

public class NoteApp {

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
}
