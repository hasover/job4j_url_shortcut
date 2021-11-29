package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.model.UrlStat;

public interface UrlStatRepository extends CrudRepository<UrlStat, Integer> {
    UrlStat findByUrl(Url url);
}
