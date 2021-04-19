package vn.vl.nthung.androidcm;

public class Food {
    private String name;
    private String description;

    public static final Food[] foods = {
            new Food("Pizza", "Very tasty and good price"),
            new Food("Burger", "Cannot found any where"),
            new Food("Cheese", "Very special and good price")
    };

    public Food(String name, String description) {
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
