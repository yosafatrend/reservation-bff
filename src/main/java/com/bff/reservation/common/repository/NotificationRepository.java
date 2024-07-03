package com.bff.reservation.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bff.reservation.common.model.Notifications;
import com.bff.reservation.common.model.Users;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, Long> {
    List<Notifications> findByUsers(Users user);

     @Query("SELECT n FROM Notifications n WHERE n.notification_id = :notification_id AND n.users = :users")
    Optional<Notifications> findByIdAndUsers(@Param("notification_id") Long notification_id, @Param("users") Users users);
}
