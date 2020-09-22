package ro.jademy.notetaking.services;

import ro.jademy.notetaking.models.Note;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortingService {

    public List<Note> newestNotesList(List<Note> noteList) {
        List<Note> sortedList = new ArrayList<>(noteList);
        sortedList.sort((note, t1) -> (int) (t1.getCreationDate() - note.getCreationDate()));
        return sortedList;
    }

    public List<Note> oldestNotesList(List<Note> noteList) {
        List<Note> sortedList = new ArrayList<>(noteList);
        sortedList.sort((note, t1) -> (int) (note.getCreationDate() - t1.getCreationDate()));
        return sortedList;
    }

    public List<Note> newestEditedNotesList(List<Note> noteList) {
        List<Note> sortedList = new ArrayList<>(noteList);
        sortedList.sort((note, t1) -> (int) (t1.getModificationDate() - note.getModificationDate()));
        return sortedList;
    }

    public List<Note> oldestEditedNotesList(List<Note> noteList) {
        List<Note> sortedList = new ArrayList<>(noteList);
        sortedList.sort((note, t1) -> (int) (note.getModificationDate() - t1.getModificationDate()));
        return sortedList;
    }

    public List<Note> unfinishedNotesList(List<Note> noteList) {
        return noteList.stream().filter(note -> !note.isMarkedAsFinished()).distinct().collect(Collectors.toList());
    }

    public List<Note> favoriteNotesList(List<Note> noteList) {
        return noteList.stream().filter(Note::isPinnedAsFavorite).distinct().collect(Collectors.toList());
    }
}
