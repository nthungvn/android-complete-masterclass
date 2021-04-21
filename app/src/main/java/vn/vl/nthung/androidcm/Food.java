package vn.vl.nthung.androidcm;

public class Food {
    private String name;
    private String description;
    private int imageId;

    public static final Food[] foods = {
            new Food("Pizza", "Very tasty and good price", R.drawable.pizza),
            new Food("Burger", "Cannot found any where", R.drawable.burger),
            new Food("Cheese", "Very special and good price", R.drawable.cheese)
    };

    public Food(String name, String description, int imageId) {
        this.name = name;
        this.description = description;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageId() {
        return imageId;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
