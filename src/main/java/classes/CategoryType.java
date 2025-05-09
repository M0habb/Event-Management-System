package classes;

public enum CategoryType {
    MUSIC, SPORTS, THEATER, CONFERENCE, OTHER;

    @Override
    public String toString() {
        return switch (this) {
            case MUSIC -> "Music";
            case SPORTS -> "Sports";
            case CONFERENCE -> "Conference";
            case THEATER -> "Theater";
            case OTHER -> "Other";
        };
    }
}
