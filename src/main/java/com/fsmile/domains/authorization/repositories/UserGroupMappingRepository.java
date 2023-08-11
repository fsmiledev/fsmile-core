package com.fsmile.domains.authorization.repositories;

import com.fsmile.domains.authorization.entities.UserGroupMapping;
import com.fsmile.domains.authorization.entities.UserGroupMappingKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupMappingRepository extends JpaRepository<UserGroupMapping, UserGroupMappingKey> {
}