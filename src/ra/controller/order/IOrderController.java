package ra.controller.order;

import ra.controller.IGenericController;

public interface IOrderController extends IGenericController {
    boolean confirmOrder(int id);
}
