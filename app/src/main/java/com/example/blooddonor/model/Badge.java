package com.example.blooddonor.model;

public class Badge {
    private String name;
    private String description;
    private String emoji;
    private int pointsRequired;
    private boolean isEarned;

    public Badge() {}

    public Badge(String name, String description, String emoji, int pointsRequired, boolean isEarned) {
        this.name = name;
        this.description = description;
        this.emoji = emoji;
        this.pointsRequired = pointsRequired;
        this.isEarned = isEarned;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getEmoji() { return emoji; }
    public int getPointsRequired() { return pointsRequired; }
    public boolean isEarned() { return isEarned; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public void setPointsRequired(int pointsRequired) { this.pointsRequired = pointsRequired; }
    public void setEarned(boolean earned) { isEarned = earned; }
}
