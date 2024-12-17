package com.jackson.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class UserSignInfoVO implements Serializable {
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate signDate;

    public UserSignInfoVO() {
    }

    public UserSignInfoVO(LocalDate signDate) {
        this.signDate = signDate;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSignInfoVO that = (UserSignInfoVO) o;
        return Objects.equals(signDate, that.signDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(signDate);
    }

    @Override   
    public String toString() {
        return "UserSignInfoVO{" +
                "signDate=" + signDate +
                '}';
    }
}
