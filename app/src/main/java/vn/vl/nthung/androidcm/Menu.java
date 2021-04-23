package vn.vl.nthung.androidcm;

public class Menu {
    private String name;
    private String description;

    public static Menu[] items = {
            new Menu("Breakfast", "Whole eggs\nBread\nCoffee"),
            new Menu("Lunch", "Whole eggs\nBroccoli"),
            new Menu("Dinner", "Whole eggs\nPotato\nCoffee")
    };

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
