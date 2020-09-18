package ro.jademy.notetaking.model;

import java.util.Date;

public class TimeStamp {

    private Date creationDate;
    private Date updateDate;

    public TimeStamp() {
    }

    public TimeStamp(Date creationDate, Date updateDate) {
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "TimeStamp{" +
                "creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
