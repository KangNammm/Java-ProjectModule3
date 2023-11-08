package ra.view;

import ra.config.Config;
import ra.controller.order.OrderControllerIMPL;
import ra.model.CartItem;
import ra.model.Order;
import ra.model.Product;
import ra.model.User;

import java.util.List;

import static ra.config.Config.scanner;
public class OrderView {
    private static OrderControllerIMPL controllerOrder = new OrderControllerIMPL();
    List<Order> orderList = controllerOrder.findAll();
    private static User userlogin = new UserView().userLogin;
    public void showOder() {
        double total = 0;
        System.out.println("                                                                         \u001B[33mDanh sách đặt hàng");
        System.out.format("                                                    +---------+-----------------+-------------------+-----------------+%n");
        System.out.format("                                                    | User ID |    UserName     |     Tổng tiền     |      Status     |%n");
        System.out.format("                                                    +---------+-------------------------------------+-----------------+%n");
        String leftAlignFormat = "                                                    | %-7d | %-15s | %-30s   | %-6s |%n";
        for (Order order:orderList) {
//            total = order.getOderCart();
            System.out.format(leftAlignFormat,order.getOrderUser().getId(),order.getOrderUser().getUsername(),order.getOderCart(),(order.isOderStatus()?" Chưa xác nhận ":" Đã xác nhận "));
        }
        System.out.format("\u001B[35m                                                    +---------+-----------------+-------------------+-----------------+%n\u001B[0m");

    }
    public static void showOderMenu() {
        int choice = 0;
        while (choice != 2) {
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("\u001B[33m                                                                             " + "QUẢN LÍ BÁN HÀNG" + "                                    \u001B[0m");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                1.  Hiển thị danh sách đặt hàng                                                        ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
             System.out.println("                                               2.  Xác nhận đặt hàng                                                        ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
             System.out.println("                                               3.  Xem chi tiết sản phẩm                                                        ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                4. Thoát                                                ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
             choice = Config.validateInt();
             switch (choice) {
                    case 1:
                        new OrderView().showOder();
                        break;
                    case 2:
                        new OrderView().confirmOrder();
                        break;
                    case 3:
//                        new OrderView().showOder();
                        break;
                    case 4:
                        new Navbar();
                        Navbar.generalManager();
                        break;
                    default:
                        System.out.println("\033[0;31m                                                                         Không hợp lệ. Vui lòng chọn lại.                                \033[0;0m");
                        break;
            }
        }
    }

    private void confirmOrder() {
        showOder();
        System.out.println("Nhập id bạn muốn xác nhận: ");
        int id = Config.validateInt();
        Order order = controllerOrder.findById(id);
        if (order == null){
            System.out.println("\033[0;31mId không tồn tại!\033[0;0m");
        } else {
            controllerOrder.confirmOrder(id);
            System.out.println("\033[0;33m                                                                 Xác nhận đơn hàng thành công.                                        \033[0;33m");
        }
    }
}
                                                                                         