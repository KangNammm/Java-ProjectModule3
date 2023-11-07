package ra.model;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

public class Order implements Serializable {
    private User orderUser;
    private List<CartItem> oderCart;
    private String oderStatus;

    public Order() {
    }

    public Order(User orderUser, List<CartItem> oderCart, String oderStatus) {
        this.orderUser = orderUser;
        this.oderCart = oderCart;
        this.oderStatus = oderStatus;
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

    public String getOderStatus() {
        return oderStatus;
    }

    public void setOderStatus(String oderStatus) {
        this.oderStatus = oderStatus;
    }



    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("oderUser=" + orderUser)
                .add("oderCart=" + oderCart)
                .add("oderStatus='" + oderStatus + "'")
                .toString();
    }
}

