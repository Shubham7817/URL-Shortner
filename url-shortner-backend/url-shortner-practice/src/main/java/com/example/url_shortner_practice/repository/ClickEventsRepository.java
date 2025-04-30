package com.example.url_shortner_practice.repository;

import com.example.url_shortner_practice.model.ClickEvent;
import com.example.url_shortner_practice.model.UrlMapping;
import com.example.url_shortner_practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClickEventsRepository extends JpaRepository<ClickEvent,Long> {
    List<ClickEvent> findByUrlMappingAndClickDateBetween(UrlMapping mapping, LocalDateTime startDate,LocalDateTime endDate);
    List<ClickEvent> findByUrlMappingInAndClickDateBetween(List<UrlMapping> urlMappings , LocalDateTime startDate,LocalDateTime endDate);
}
