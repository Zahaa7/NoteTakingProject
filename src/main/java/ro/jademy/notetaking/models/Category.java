package ro.jademy.notetaking.models;

public enum Category {

    SHOPPING("SHOPPING"),
    TRAVEL("TRAVEL"),
    PERSONAL("PERSONAL"),
    LIFE("LIFE"),
    WORK("WORK"),
    NO_CATEGORY("NO CATEGORY");
    private final String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public static Category lookup(String category, Category defaultCategory) {
        try {
            return Category.valueOf(category);
        } catch (IllegalArgumentException illegalArgumentException) {
            return defaultCategory;
        }
    }
}
