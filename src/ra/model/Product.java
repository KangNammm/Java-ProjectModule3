package ra.model;

import ra.config.Config;

import java.io.Serializable;
import java.util.StringJoiner;

public class Product implements Serializable {
    private int productId;
    private Catalog productCatalog;
    private String productName;
    private double productPrice;
    private Boolean productStatus;
    private int stock;


    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Catalog getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(Catalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Boolean getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Boolean productStatus) {
        this.productStatus = productStatus;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Product(int productId, Catalog productCatalog, String productName, double productPrice, Boolean productStatus, int stock) {
        this.productId = productId;
        this.productCatalog = productCatalog;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
        this.stock = stock;

    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productCatalog=" + productCatalog +
                ", productName='" + productName + '\'' +
                ", productPrice=" + Config.formatMoney.format(productPrice) +
                ", productStatus=" + productStatus +
                ", stock=" + stock +
                '}';
    }
}
