package com.jackson.dto;

import java.io.Serializable;
import java.util.Objects;

public class UserNoteDTO implements Serializable {
    private Long id;
    private String note;

    public UserNoteDTO() {
    }

    public UserNoteDTO(Long id, String note) {
        this.id = id;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNoteDTO that = (UserNoteDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, note);
    }

    @Override
    public String toString() {
        return "UserNoteDTO{" +
                "id=" + id +
                ", note='" + note + '\'' +
                '}';
    }
}
