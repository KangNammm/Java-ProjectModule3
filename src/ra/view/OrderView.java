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
        System.out.println("                                                                         \u001B[33mDanh sách đặt hàng");
        System.out.format("                                               +------------------+-----------------+-------------------+-----------------+%n");
        System.out.format("                                               |    ID Đặt Hàng   |    UserName     |     Tổng tiền     |    Trạng Thái   |%n");
        System.out.format("                                               +------------------+-------------------------------------+-----------------+%n");
        String leftAlignFormat = "                                               |  %-15d | %-15s |  %-15s  |  %-6s  |%n";
        for (Order order:orderList) {
            double total = 0;
            for (CartItem cartItem : order.getOderCart()){
                total += cartItem.getProduct().getProductPrice()*cartItem.getQuantity();
            }
            System.out.format(leftAlignFormat,order.getId(),order.getOrderUser().getUsername(),Config.formatMoney.format(total),(!order.isOderStatus()?"Chưa xác nhận":" Đã xác nhận "));
        }
        System.out.format("\u001B[33m                                               +------------------+-----------------+-------------------+-----------------+%n\u001B[0m");

    }
    public static void showOderMenu() {
        int choice = 0;
        while (choice != 4) {
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("\u001B[33m                                                                             " + "QUẢN LÍ BÁN HÀNG" + "                                    \u001B[0m");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                1.  Hiển thị danh sách đặt hàng                                                        ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                2.  Xác nhận đặt hàng                                                        ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                3.  Xem chi tiết sản phẩm                                                        ");
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
                        new OrderView().showProductCart();
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

    private void showProductCart() {
        showOder();
        System.out.println("Nhập id bạn muốn xem chi tiết: ");
        int id = Config.validateInt();
        Order order = controllerOrder.findById(id);
        if (order == null){
            System.out.println("\033[0;31mId không tồn tại!\033[0;0m");
        } else {
        List<CartItem> listCart = order.getOderCart();
        System.out.println("                                                                   \u001B[33mChi tiết đơn hàng theo id vừa nhập: "+ id);
        System.out.format("                                           +-----------------+-----------------+-----------------+-----------------+----------+%n");
        System.out.format("                                           |    ID Sản Phẩm  |  Tên Sản phẩm   |   Tên danh mục  |   Giá tiền      | Số lượng |%n");
        System.out.format("                                           +-----------------+-----------------+-----------------+-----------------+----------+%n");
        String leftAlignFormat = "                                           |     %-10d  | %-15s | %-15s | %-15s |  %-6s  |%n";

        for (CartItem cartItem:order.getOderCart()) {
            System.out.format(leftAlignFormat, cartItem.getId(), cartItem.getProduct().getProductName(), cartItem.getProduct().getProductCatalog().getName(), Config.formatMoney.format(cartItem.getProduct().getProductPrice()), cartItem.getQuantity());
            }
            System.out.format("                                           +-----------------+-----------------+-----------------+-----------------+----------+%n");
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
        showOderMenu();
        }
    }
}
                                                                                         