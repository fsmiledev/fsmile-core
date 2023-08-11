package com.fsmile.domains.authorization.repositories;

import com.fsmile.domains.authorization.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupEntity, String> {

    @Query("SELECT g FROM GroupEntity g JOIN UserGroupMapping ugm ON g.groupId = ugm.groupEntity.groupId JOIN UserEntity u ON ugm.user.userId = u.userId WHERE u.userId = :userId")
    List<GroupEntity> findByUserId(@Param("userId") String userId);
}