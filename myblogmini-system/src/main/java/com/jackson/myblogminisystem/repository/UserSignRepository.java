package com.jackson.myblogminisystem.repository;

import com.jackson.dao.UserSign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserSignRepository extends JpaRepository<UserSign, Long>, JpaSpecificationExecutor<UserSign> {

    @Query("SELECT s.signDate FROM UserSign s WHERE s.user.id = :userId AND s.signDate <= :startDate ORDER BY s.signDate DESC")
    List<LocalDate> findRecentSignDates(@Param("userId") Long userId, @Param("startDate") LocalDate startDate);

    List<UserSign> findAllByUser_Id(Long userId);
}
