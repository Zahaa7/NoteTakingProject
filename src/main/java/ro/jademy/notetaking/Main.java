package ro.jademy.notetaking;

import ro.jademy.notetaking.services.IOService;

import java.io.File;

public class Main {

    public static void main(String[] args)  {

        IOService ioService = new IOService();
        NoteApp noteApp = new NoteApp(ioService.readJson(), new File( "src/main/resources/notes.json"));
        noteApp.initiateNoteApp();
    }
}
