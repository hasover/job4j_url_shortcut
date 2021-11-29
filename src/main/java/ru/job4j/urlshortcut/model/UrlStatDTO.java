package ru.job4j.urlshortcut.model;

public class UrlStatDTO {
    private String name;
    private int totalHits;

    public static UrlStatDTO of(String name, int total) {
        UrlStatDTO urlStatDTO = new UrlStatDTO();
        urlStatDTO.name = name;
        urlStatDTO.totalHits = total;
        return urlStatDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }
}
