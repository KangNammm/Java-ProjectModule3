package ra.service.order;

import ra.config.Config;
import ra.model.Order;
import ra.model.User;
import ra.service.catalog.ICataService;

import java.util.List;

public class OrderserviceIMPL implements IOrderService {

    private static List<Order> orderList = new Config<Order>().readFromFile(Config.PATH_ODER);

    @Override
    public List findAll() {
        return orderList;
    }

    @Override
    public void save(Order order) {
        orderList.add(order);
        new Config<Order>().writeToFile(Config.PATH_ODER, orderList);
    }

    @Override
    public Order findById(int id) {
        for (Order oder : orderList) {
            if (oder.getOrderUser().getId() == id) {
                return oder;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List searchByName(String searchName) {
        return null;
    }

    @Override
    public boolean changeUserStatus(int id) {
        return false;
    }
}
