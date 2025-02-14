package com.jackson.myblogminisystem.repository;

import com.jackson.dao.ChatMessage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query("SELECT cm FROM ChatMessage cm WHERE (cm.senderId = :senderId AND cm.receiverId = :receiverId) " +
            "OR (cm.senderId = :receiverId AND cm.receiverId = :senderId) ORDER BY cm.createTime ASC")
    List<ChatMessage> findAllBySenderIdAndReceiverId(Long senderId, Long receiverId);

    /**
     * 获取未读信息
     *
     * @param senderId
     * @param receiverId
     * @param status
     * @return
     */
    @Query("select cm from ChatMessage cm where cm.senderId = :senderId and cm.receiverId = :receiverId and cm.status = :status")
    List<ChatMessage> findAllBySenderIdAndReceiverIdAndStatus(Long senderId, Long receiverId, Boolean status);

    @Modifying
    @Transactional
    @Query("update ChatMessage cm set cm.status = :status where cm.id in :ids")
    void updateStatusByIds(@Param("status") Boolean status, @Param("ids") List<Long> ids);

    @Query("select cm.senderId from ChatMessage cm where cm.receiverId = :receiverId")
    List<Long> findSenderIdByReceiverId(@Param("receiverId") Long receiverId);

    @Query("select cm.receiverId from ChatMessage cm where cm.senderId = :senderId")
    List<Long> findReceiverIdBySenderId(@Param("senderId") Long senderId);
}
