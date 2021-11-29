package ru.job4j.urlshortcut.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.model.UrlStat;
import ru.job4j.urlshortcut.repository.UrlRepository;
import ru.job4j.urlshortcut.repository.UrlStatRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UrlService {
    private UrlRepository urlRepository;
    private UrlStatRepository urlStatRepository;

    public UrlService(UrlRepository urlRepository, UrlStatRepository urlStatRepository) {
        this.urlRepository = urlRepository;
        this.urlStatRepository = urlStatRepository;
    }

    public void addNewUrl(Url urlObj) {
        urlRepository.save(urlObj);
    }

    public Optional<Url> findUrlById(int id) {
        return urlRepository.findById(id);
    }

    public List<UrlStat> findAllUrlsStats() {
        return StreamSupport
                .stream(urlStatRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public UrlStat findUrlStatByUrl(Url url) {
        return urlStatRepository.findByUrl(url);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateStats(Url url) {
        UrlStat urlStat = urlStatRepository.findByUrl(url);
        urlStat.setNumOfHits(urlStat.getNumOfHits() + 1);
    }

    public void createUrlStats(Url urlObj) {
        UrlStat urlStat = new  UrlStat();
        urlStat.setUrl(urlObj);
        urlStatRepository.save(urlStat);
    }
}
