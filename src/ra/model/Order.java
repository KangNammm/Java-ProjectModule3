package ra.model;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

public class Order implements Serializable {
    private int id;
    private User orderUser;
    private List<CartItem> oderCart;
    private boolean oderStatus = false;

    public Order() {
    }

    public Order(int id, User orderUser, List<CartItem> oderCart, boolean oderStatus) {
        this.id = id;
        this.orderUser = orderUser;
        this.oderCart = oderCart;
        this.oderStatus = oderStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(User orderUser) {
        this.orderUser = orderUser;
    }

    public List<CartItem> getOderCart() {
        return oderCart;
    }

    public void setOderCart(List<CartItem> oderCart) {
        this.oderCart = oderCart;
    }

    public boolean isOderStatus() {
        return oderStatus;
    }

    public void setOderStatus(boolean oderStatus) {
        this.oderStatus = oderStatus;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderUser=" + orderUser +
                ", oderCart=" + oderCart +
                ", oderStatus='" + oderStatus + '\'' +
                '}';
    }
}

