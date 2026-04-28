package com.spring.shorty.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shorty.entities.LinkEntity;
import com.spring.shorty.services.LinkService;

@RestController
@RequestMapping("/links")
@CrossOrigin(origins = "*")
public class LinkController {

    private final LinkService service;

    public LinkController(LinkService service) {
        this.service = service;
    }

    @PostMapping
    public LinkEntity create(@RequestBody LinkEntity link) {
        return service.create(link);
    }

    @GetMapping("/user/{userId}")
    public List<LinkEntity> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }

    @PutMapping("/{id}")
    public LinkEntity update(@PathVariable Long id, @RequestBody LinkEntity link) {
        return service.update(id, link);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{code}")
    public String redirect(@PathVariable String code) {
        LinkEntity link = service.getByShortCode(code)
                .orElseThrow(() -> new RuntimeException("Link not found"));

        if (!link.getIsActive()) {
            throw new RuntimeException("Link inativo");
        }

        if (link.getExpiresAt() != null && link.getExpiresAt().isBefore(java.time.LocalDateTime.now())) {
            throw new RuntimeException("Link expirado");
        }

        service.incrementClick(link);

        return link.getOriginalUrl();
    }
}