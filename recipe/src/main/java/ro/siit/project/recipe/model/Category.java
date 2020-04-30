package ro.siit.project.recipe.model;

public enum Category {

    SOUP("Soup"),
    DESSERT("Dessert"),
    MAINDISH("Main Dish"),
    SALAD("Salad"),
    MISCELLANEOUS("Miscellaneous");

    private final String displayValue;

    Category(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
