package ru.job4j.urlshortcut.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "urlstats")
public class UrlStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numOfHits;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_id")
    private Url url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfHits() {
        return numOfHits;
    }

    public void setNumOfHits(int numOfHits) {
        this.numOfHits = numOfHits;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UrlStat urlStat = (UrlStat) o;
        return id == urlStat.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
