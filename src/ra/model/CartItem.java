package ra.model;

import ra.config.Config;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int id;
    private Product product;
    private int quantity;
    private double total = 0;

    public CartItem() {
    }

    public CartItem(int id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return String.valueOf(total += product.getProductPrice()*quantity);
    }
}
