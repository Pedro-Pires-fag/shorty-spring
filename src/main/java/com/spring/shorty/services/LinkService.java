package com.spring.shorty.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.shorty.entities.LinkEntity;
import com.spring.shorty.repository.LinkRepository;

@Service
public class LinkService {

    private final LinkRepository repository;

    public LinkService(LinkRepository repository) {
        this.repository = repository;
    }


    public LinkEntity create(LinkEntity link) {
        link.setCreatedAt(LocalDateTime.now());
        link.setUpdatedAt(LocalDateTime.now());
        return repository.save(link);
    }

    public List<LinkEntity> getByUser(Long userId) {
        return repository.findByUserId(userId);
    }


    public Optional<LinkEntity> getByShortCode(String code) {
        return repository.findByShortCode(code);
    }

    public LinkEntity update(Long id, LinkEntity updated) {
        LinkEntity link = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Link not found"));

        link.setOriginalUrl(updated.getOriginalUrl());
        link.setTitle(updated.getTitle());
        link.setIsActive(updated.getIsActive());
        link.setExpiresAt(updated.getExpiresAt());
        link.setUpdatedAt(LocalDateTime.now());

        return repository.save(link);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void incrementClick(LinkEntity link) {
        link.setClicks(link.getClicks() + 1);
        repository.save(link);
    }
}