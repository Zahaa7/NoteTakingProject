package ro.jademy.notetaking.model;

public class Note {  // a note's blueprint

    private String title;
    private String body;
    private TimeStamp timestamp;

    public Note() {
    }

    public Note(String title, String body, TimeStamp timestamp) {
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
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

    public TimeStamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(TimeStamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("~~~~~ Employee Details ~~~~~\n");
        sb.append("Title:" + getTitle() + "\n");
        sb.append("Body:" + getBody() + "\n");
        sb.append("Timestamp:" + getTimestamp() + "\n");
        sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ");
        return sb.toString();
    }
}