package com.spring.shorty.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.shorty.entities.LinkEntity;

public interface LinkRepository extends JpaRepository<LinkEntity, UUID> {

    Optional<LinkEntity> findByShortCode(String shortCode);

    List<LinkEntity> findByUserId(Long userId);
}