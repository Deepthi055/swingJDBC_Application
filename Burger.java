package SwingJDBC;

class Burger {
    private final String name;
    private final String imagePath;
    private final double price;

    public Burger(String name, String imagePath, double price) {
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getPrice() {
        return price;
    }
}

