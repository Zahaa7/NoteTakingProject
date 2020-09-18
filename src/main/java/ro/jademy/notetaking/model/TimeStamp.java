package ro.jademy.notetaking.model;

public class TimeStamp {

    private Long creationDate;
    private Long updateDate;

    public TimeStamp() {
    }

    public TimeStamp(Long creationDate, Long updateDate) {
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return getCreationDate() + ", " + getUpdateDate();
    }
}
