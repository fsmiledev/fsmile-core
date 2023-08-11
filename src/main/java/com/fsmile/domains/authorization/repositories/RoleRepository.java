package com.fsmile.domains.authorization.repositories;

import com.fsmile.domains.authorization.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
}