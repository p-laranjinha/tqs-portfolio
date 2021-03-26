public class Stock {
    String name;
    int quantity;

    Stock(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getQuantity() {
        return quantity;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
