package ru.job4j.urlshortcut.shortener;

public interface UrlShortener {
    String encode(int number);

    int decode(String string);
}
