package ra.controller.order;

import ra.model.Order;
import ra.service.order.IOrderService;
import ra.service.order.OrderserviceIMPL;

import java.util.List;

public class OrderControllerIMPL implements IOrderController {
    private static IOrderService iOrderService = new OrderserviceIMPL();

    @Override
    public List findAll() {
        return iOrderService.findAll();
    }

    @Override
    public void save(Object o) {

    }

    public Order findById(int id){
        return (Order) iOrderService.findById(id);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List searchByName(String searchName) {
        return null;
    }

}