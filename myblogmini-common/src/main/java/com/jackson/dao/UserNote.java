package com.jackson.dao;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_note")
public class UserNote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String note;
    private Long userId;
    private Long userNoteId;

    public UserNote() {
    }

    public UserNote(Long id, String note, Long userId, Long userNoteId) {
        this.id = id;
        this.note = note;
        this.userId = userId;
        this.userNoteId = userNoteId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserNoteId() {
        return userNoteId;
    }

    public void setUserNoteId(Long userNoteId) {
        this.userNoteId = userNoteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNote userNote = (UserNote) o;
        return Objects.equals(id, userNote.id) && Objects.equals(note, userNote.note) && Objects.equals(userId, userNote.userId) && Objects.equals(userNoteId, userNote.userNoteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, note, userId, userNoteId);
    }

    @Override
    public String toString() {
        return "UserNote{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", userId=" + userId +
                ", userNoteId=" + userNoteId +
                '}';
    }
}
