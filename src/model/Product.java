package model;

/**
 * Products in Vending Machine (name-value).
 */
public enum Product {
	
    COKE("Coke", 150), PEPSI("Pepsi", 145), WATER("Water", 90);

    private String name;
    private int price;

    private Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }
}

