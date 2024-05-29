package com.screenmatch.screenmatch.model;

public enum Category {
    ACTION("Action"),
    ROMANCE("Romance"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime"),
    ANIMATION("Animation");


    private String CategoryOmdb;

    Category(String categoryOmdb) {
        this.CategoryOmdb=categoryOmdb;
    }

    public static Category fromString(String text) {
        for (Category Category : Category.values()) {
            if (Category.CategoryOmdb.equalsIgnoreCase(text)) {
                return Category;
            }
        }
        throw new IllegalArgumentException("Any category found: " + text);
    }

}
