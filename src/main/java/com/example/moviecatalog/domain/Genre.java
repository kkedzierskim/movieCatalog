package com.example.moviecatalog.domain;

public enum Genre {

    ACTION("ACTION"),
    ADVENTURE("ADVENTURE"),
    COMEDY("COMEDY"),
    DRAMA("DRAMA"),
    FANTASY("FANTASY"),
    HISTORICAL("HISTORICAL"),
    HORROR("HORROR"),
    POLITICAL("POLITICAL"),
    ROMANCE("ROMANCE"),
    SOCIAL("SOCIAL"),
    THRILLER("THRILLER"),
    WESTERN("WESTERN");

    private final String displayValue;

    private Genre(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
