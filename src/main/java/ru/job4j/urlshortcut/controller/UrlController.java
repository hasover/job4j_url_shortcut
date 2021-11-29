package ru.job4j.urlshortcut.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.model.UrlStat;
import ru.job4j.urlshortcut.model.UrlStatDTO;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.service.UrlService;
import ru.job4j.urlshortcut.service.UserService;
import ru.job4j.urlshortcut.shortener.UrlShortener;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
public class UrlController {
    private UrlShortener urlShortener;
    private UserService userService;
    private UrlService urlService;

    public UrlController(UrlShortener urlShortener, UserService userService,
                         UrlService urlService) {
        this.urlShortener = urlShortener;
        this.userService = userService;
        this.urlService = urlService;
    }

    @PostMapping(value = "/convert")
    public String convertUrl(@RequestBody Map<String, String> url) {
        String originalUrl = url.get("url");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.loadUserByUsername(username);
        Url urlObj = Url.of(originalUrl, user);
        try {
            urlService.addNewUrl(urlObj);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "URL has already been converted");
        }
        urlService.createUrlStats(urlObj);
        return urlShortener.encode(urlObj.getId());
    }

    @GetMapping(value = "/redirect/{shortUrl}")
    public ResponseEntity<?> redirectToOriginal(@PathVariable String shortUrl)
            throws URISyntaxException {

        int urlId = urlShortener.decode(shortUrl);
        Url url = urlService.findUrlById(urlId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such url"));

        urlService.updateStats(url);
        URI uri = new URI(url.getValue());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
    }

    @GetMapping(value = "/statistic")
    public UrlStatDTO getTotalStats() {
        int total = urlService.findAllUrlsStats()
                .stream()
                .mapToInt(UrlStat::getNumOfHits)
                .reduce(0, Integer::sum);
        return UrlStatDTO.of("All urls", total);
    }

    @GetMapping(value = "/statistic/{shortUrl}")
    public UrlStatDTO getSiteStats(@PathVariable String shortUrl) {
        int urlId = urlShortener.decode(shortUrl);
        Url url = urlService.findUrlById(urlId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such url"));
        UrlStat urlStat = urlService.findUrlStatByUrl(url);
        return UrlStatDTO.of(url.getValue(), urlStat.getNumOfHits());
    }
}
