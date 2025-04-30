package com.example.url_shortner_practice.controller;

import com.example.url_shortner_practice.dtos.ClickEventDTO;
import com.example.url_shortner_practice.dtos.UrlMappingDTO;
import com.example.url_shortner_practice.model.ClickEvent;
import com.example.url_shortner_practice.model.User;
import com.example.url_shortner_practice.service.UrlMappingServcie;
import com.example.url_shortner_practice.service.UserService;
import io.jsonwebtoken.security.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
@CrossOrigin(origins = "http://localhost:5173")
public class UrlMappingController {
    @Autowired
    private UrlMappingServcie urlMappingServcie;

    @Autowired
    private UserService userService;


    // {"originalUrl":"https://example.com"}
    @PostMapping("/shorten")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UrlMappingDTO> createShortUrl(@RequestBody Map<String,String> request,
                                                        Principal principal){
    String originalUrl = request.get("originalUrl");
    User user =  userService.findByUsername(principal.getName());

    //call service
      UrlMappingDTO urlMappingDTO = urlMappingServcie.createShortUrl(originalUrl,user);
      return ResponseEntity.ok(urlMappingDTO);
    }

    @GetMapping("/myurls")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UrlMappingDTO>> getUserUrls(Principal principal){
        User user = userService.findByUsername(principal.getName());
       List<UrlMappingDTO> urls = urlMappingServcie.getUrlByUser(user);
       return ResponseEntity.ok(urls);
    }

    @GetMapping("/analytics/{shortUrl}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ClickEventDTO>> getUrlAnalytics(@PathVariable String shortUrl,
                                                        @RequestParam("startDate") String startDate,
                                                         @RequestParam("endDate") String endDate ){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(startDate,formatter);
        LocalDateTime end = LocalDateTime.parse(endDate,formatter);

       List<ClickEventDTO> clickEventDTOS = urlMappingServcie.getClickEventsByDate(shortUrl,start,end);
       return ResponseEntity.ok(clickEventDTOS);
    }

    @GetMapping("/totalClicks")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Map<LocalDate,Long>> getTotalClicksByDate(Principal principal,
                                                       @RequestParam("startDate") String startDate,
                                                       @RequestParam("endDate") String endDate){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        User user = userService.findByUsername(principal.getName());
        LocalDate start = LocalDate.parse(startDate,formatter);
        LocalDate end = LocalDate.parse(endDate,formatter);

        Map<LocalDate,Long> totalClicks = urlMappingServcie.getTotalClicksByUserAndDate(user,start,end);
        return ResponseEntity.ok(totalClicks);
    }
}


//   /analytics/abc123?startDate=2024-12-01T00:00:00&endDate=2024-12-07T23:59:59
//   /totalClicks?startDate=2024-12-01&endDate=2024-12-07