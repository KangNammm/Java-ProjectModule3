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
        System.out.println("                                                                             \u001B[33mOrder sản phẩm");
        System.out.format("                                            +---------+-----------------+----------------------------------+-----------------+%n");
        System.out.format("                                            | User ID |    UserName     |     Tên và số lượng sản phẩm     |      Status     |%n");
        System.out.format("                                            +---------+-----------------+----------------------------------+-----------------+%n");
        String leftAlignFormat = "                                            | %-7d | %-15s | %-30s   | %-6s |%n";
        for (Order order:orderList) {
            System.out.format(leftAlignFormat,order.getOrderUser().getId(),order.getOrderUser().getUsername(),order.getOderCart(),order.getOderStatus());
        }
        System.out.format("                                            +---------+-----------------+----------------------------------+-----------------+%n");

    }
    public static void showOderMenu() {
        int choice = 0;
        while (choice != 2) {
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("\u001B[33m                                                                             " + "QUẢN LÍ BÁN HÀNG" + "                                    \u001B[0m");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                1.  Hiển thị danh sách order                                                        ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                2. Thoát                                                ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
             choice = Config.validateInt();
            switch (choice) {
                case 1:
                    new OrderView().showOder();
                    break;
                    case 2:
                        new Navbar();
                        Navbar.generalManager();
                        break;
                    default:
                        System.out.println("\033[0;31m                                                                         Không hợp lệ. Vui lòng chọn lại.                                \033[0;0m");
                        break;
            }
        }
    }
}
                                                                                         