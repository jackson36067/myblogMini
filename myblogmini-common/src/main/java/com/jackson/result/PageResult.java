package com.jackson.result;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class PageResult implements Serializable {
    private Long total;
    private Integer totalPages;
    private List list;

    public PageResult() {
    }

    public PageResult(Long total, Integer totalPages, List list) {
        this.total = total;
        this.totalPages = totalPages;
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageResult that = (PageResult) o;
        return Objects.equals(total, that.total) && Objects.equals(totalPages, that.totalPages) && Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, totalPages, list);
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", totalPages=" + totalPages +
                ", list=" + list +
                '}';
    }
}
