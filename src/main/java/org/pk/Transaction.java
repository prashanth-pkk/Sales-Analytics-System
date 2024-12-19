package org.pk;

public class Transaction {
    private Product product;
    private int quantity;

    public Transaction(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return product.getName() + " X " + quantity + "- Total: $" + getTotal();
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }
}
