package ru.alexxsys.transmit_bots_message.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "history")
public class History {
    public static enum STATUS {wait, error, send};

    @Id
    private UUID id;
    private Date dateTime;
    private String patchFrom;
    private String patch;
    private STATUS status;

    public History() {
    }
    public History(UUID id, Date dateTime, String patchFrom, String patch) {
        this.id = id;
        this.dateTime = dateTime;
        this.patchFrom = patchFrom;
        this.patch = patch;
        this.status = STATUS.wait;
    }

    public UUID getId() {
        return id;
    }
    public Date getDateTime() {
        return dateTime;
    }
    public String getPatchFrom() {
        return patchFrom;
    }
    public String getPatch() {
        return patch;
    }
    public STATUS getStatus() {
        return status;
    }
    public void setStatus(STATUS status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        History history = (History) o;

        if (!id.equals(history.id)) return false;
        if (!dateTime.equals(history.dateTime)) return false;
        if (!patchFrom.equals(history.patchFrom)) return false;
        return patch.equals(history.patch);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + patchFrom.hashCode();
        result = 31 * result + patch.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "History{" +
                "dateTime=" + dateTime +
                ", patchFrom='" + patchFrom + '\'' +
                ", patch='" + patch + '\'' +
                '}';
    }
}
