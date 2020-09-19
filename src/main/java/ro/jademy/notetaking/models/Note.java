package ro.jademy.notetaking.models;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class Note {

    private String title;
    private String body;
    private long creationDate;
    private long modificationDate;
    private boolean markedAsFinished;
    private boolean pinnedAsFavorite;
    private Category category;
    private List<String> hashtagList;

    public Note(String title, String body, long creationDate, long modificationDate, boolean markedAsFinished,
                boolean pinnedAsFavorite, Category category, List<String> hashtagList) {
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.markedAsFinished = markedAsFinished;
        this.pinnedAsFavorite = pinnedAsFavorite;
        this.category = category;
        this.hashtagList = hashtagList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(long modificationDate) {
        this.modificationDate = modificationDate;
    }

    public boolean isMarkedAsFinished() {
        return markedAsFinished;
    }

    public void setMarkedAsFinished(boolean markedAsFinished) {
        this.markedAsFinished = markedAsFinished;
    }

    public boolean isPinnedAsFavorite() {
        return pinnedAsFavorite;
    }

    public void setPinnedAsFavorite(boolean pinnedAsFavorite) {
        this.pinnedAsFavorite = pinnedAsFavorite;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getHashtagList() {
        return hashtagList;
    }

    public void setHashtagList(List<String> hashtagList) {
        this.hashtagList = hashtagList;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd/MMM/yyyy HH:mm a", Locale.ENGLISH);
        return "\n" + title + "\n" +
                body + "\n" +
                "Created on: " + sdf.format(creationDate) + "\n" +
                "Last modified on: " + sdf.format(modificationDate) + "\n" +
                "Note is marked as done: " + markedAsFinished + "\n" +
                "Note is pinned to favorite: " + pinnedAsFavorite + "\n" +
                "Hashtags " + hashtagList.toString();
    }
}
