package ro.jademy.notetaking;

import java.io.IOException;

public class Main {

    public static void main(String[] args)  {

        NoteApp noteApp = new NoteApp();
        try {
            noteApp.readJsonFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
