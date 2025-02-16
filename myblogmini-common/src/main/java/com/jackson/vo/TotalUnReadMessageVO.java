package com.jackson.vo;

import java.util.List;
import java.util.Objects;

public class TotalUnReadMessageVO {
    private Integer totalUnReadFollowMessageNumber; // 总共未读关注消息
    private Integer totalUnReadInteractMessageNumber; // 总共未读互动消息
    private List<UnReadMessageVO> unReadMessageVOList; // 总共未读聊天信息

    public TotalUnReadMessageVO() {
    }

    public TotalUnReadMessageVO(Integer totalUnReadFollowMessageNumber, Integer totalUnReadInteractMessageNumber, List<UnReadMessageVO> unReadMessageVOList) {
        this.totalUnReadFollowMessageNumber = totalUnReadFollowMessageNumber;
        this.totalUnReadInteractMessageNumber = totalUnReadInteractMessageNumber;
        this.unReadMessageVOList = unReadMessageVOList;
    }

    public Integer getTotalUnReadFollowMessageNumber() {
        return totalUnReadFollowMessageNumber;
    }

    public void setTotalUnReadFollowMessageNumber(Integer totalUnReadFollowMessageNumber) {
        this.totalUnReadFollowMessageNumber = totalUnReadFollowMessageNumber;
    }

    public Integer getTotalUnReadInteractMessageNumber() {
        return totalUnReadInteractMessageNumber;
    }

    public void setTotalUnReadInteractMessageNumber(Integer totalUnReadInteractMessageNumber) {
        this.totalUnReadInteractMessageNumber = totalUnReadInteractMessageNumber;
    }

    public List<UnReadMessageVO> getUnReadMessageVOList() {
        return unReadMessageVOList;
    }

    public void setUnReadMessageVOList(List<UnReadMessageVO> unReadMessageVOList) {
        this.unReadMessageVOList = unReadMessageVOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalUnReadMessageVO that = (TotalUnReadMessageVO) o;
        return Objects.equals(totalUnReadFollowMessageNumber, that.totalUnReadFollowMessageNumber) && Objects.equals(totalUnReadInteractMessageNumber, that.totalUnReadInteractMessageNumber) && Objects.equals(unReadMessageVOList, that.unReadMessageVOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalUnReadFollowMessageNumber, totalUnReadInteractMessageNumber, unReadMessageVOList);
    }

    @Override
    public String toString() {
        return "TotalUnReadMessageVO{" +
                "totalUnReadFollowMessageNumber=" + totalUnReadFollowMessageNumber +
                ", totalUnReadInteractMessageNumber=" + totalUnReadInteractMessageNumber +
                ", unReadMessageVOList=" + unReadMessageVOList +
                '}';
    }
}
