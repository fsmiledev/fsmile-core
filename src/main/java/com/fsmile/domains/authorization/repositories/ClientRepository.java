package com.fsmile.domains.authorization.repositories;

import com.fsmile.domains.authorization.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, String> {
}