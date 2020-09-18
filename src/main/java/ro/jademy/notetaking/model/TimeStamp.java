package ro.jademy.notetaking.model;

import java.time.LocalDate;

public class TimeStamp {

    private LocalDate creationDate;
    private LocalDate updateDate;

    public TimeStamp() {
    }

    public TimeStamp(LocalDate creationDate, LocalDate updateDate) {
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return getCreationDate() + ", " + getUpdateDate();
    }
}
