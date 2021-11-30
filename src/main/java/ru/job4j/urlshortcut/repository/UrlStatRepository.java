package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.model.UrlStat;

public interface UrlStatRepository extends CrudRepository<UrlStat, Integer> {
    UrlStat findByUrl(Url url);

    @Modifying
    @Query("update UrlStat u set u.numOfHits = u.numOfHits + 1 where u.url =:url")
    void updateNumberOfHits(Url url);
}
