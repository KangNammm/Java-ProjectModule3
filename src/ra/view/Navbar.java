package ra.view;


import ra.config.Config;
import ra.controller.UserController;
import ra.model.User;
import ra.service.role.RoleServiceIMPL;

import java.util.HashSet;


public class Navbar {

    static UserController userController = new UserController();

public static void userHomeMenu(){
            User user = userController.getUserLogin();
        if (user != null) {

            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                                 Chào mừng \u001B[33m" + user.getName() + "\u001B[0m  đến với KangNam Shop             ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                              1. Vào trang chính                     |     2. Đăng xuất tài khoản                ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                                           Lựa chọn của bạn:                                    ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");


            int chooseMenu = Config.validateInt();
            switch (chooseMenu) {
                case 1:
            new ProfileView();
                    break;
                case 2:
                    new UserView().logOut();
                    break;
                default:
                    System.err.println("\033[0;31m                                                                     Không hợp lệ. Vui lòng chọn lại.                                \033[0;0m");
                    break;
        }

    } else

    {
        System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
        System.out.println("                                                              Welcome to \u001B[33mKangNam Shop\u001B[0m thế giới thời trang, phụ kiện");
        System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
        System.out.println("                                                   1. Đăng ký tài khoản mới                 |            2. Đăng nhập                    ");
        System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
        System.out.println("                                              Lựa chọn của bạn:                                                                  ");
        int chooseMenu = Config.validateInt();
        switch (chooseMenu) {
            case 1:
                new ra.view.UserView().formRegister();
                break;
            case 2:
                new ra.view.UserView().formLogin();
                break;
        }
    }
    userHomeMenu();
}



    public static void main(String[] args) {
        userHomeMenu();
    }


    public static void generalManager() {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("\u001B[33m                                                                          " + "QUẢN LÝ KANGNAM SHOP" + "                                       \u001B[0m");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                1. Quản lý sản phẩm                                                                       ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                2. Quản lý danh mục                                                                       ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                3. Quản lý người dùng                                                                     ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                4. Quản lý bán hàng                                                                       ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                5. Đăng xuất                                                                              ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                 Lựa chọn của bạn là:                                                                     ");

            choice = Config.validateInt();
            switch (choice) {
                case 1:
                    ProductView.ProductManagement();
                    break;
                case 2:
                    CatalogView.catalogManager();
                    break;
                case 3:
                    UserView.UserManager();
                    break;
                case 4:
                    OrderView.showOderMenu();
                    break;
                case 5:
                    new UserView().logOut();
                    break;
                default:
                    System.out.println("\033[0;31m                                                                         Không hợp lệ. Vui lòng chọn lại.                                \033[0;0m");
                    break;
            }
            userHomeMenu();
        }
    }


    public static void cartView() {
        User user = userController.getUserLogin();
        int choice = 0;
        while (choice != 9) {
            System.out.println("\u001B[33m                                                                DANH SÁCH TẤT CẢ CÁC SẢN PHẨM KANGNAM SHOP ");
            new ProductView().showProduct();

            System.out.println("\u001B[35m                                         ----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("\u001B[33m                                                                               KangNam Shop                                      \u001B[0m");
            System.out.println("\u001B[35m                                         ----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                1.  Giỏ hàng của bạn                                                          ");
            System.out.println("\u001B[35m                                         ----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                2. Thêm sản phẩm vào giỏ hàng                                                   ");
            System.out.println("\u001B[35m                                         ----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                3. Thay đổi số lượng sản phẩm                                                   ");
            System.out.println("\u001B[35m                                         ----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                4. Xóa sản phẩm khỏi giỏ hàng                                                   ");
            System.out.println("\u001B[35m                                         ----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                5. Đặt hàng                                                   ");
            System.out.println("\u001B[35m                                         ----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                6. Thay đổi password                                                            ");
            System.out.println("\u001B[35m                                         ----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                7. Tìm kiếm sản phẩm                                                            ");
            System.out.println("\u001B[35m                                         ----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                8. Hiển thị sản phẩm nổi bật                                                            ");
            System.out.println("\u001B[35m                                         ----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                9. Đăng xuất                                                                    ");
            System.out.println("                                                                                                                ");
            System.out.println("\u001B[35m                                         ----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                 Lựa chọn của bạn là:                                                           ");

            choice = Config.validateInt();
            switch (choice) {
                case 1:
                    new UserView().addToCart(2);
                    break;
                case 2:
                    new UserView().addToCart(1);
                    break;
                case 3:
                    new UserView().changeQuantityItem();
                    break;
                case 4:
                    new UserView().deleteCartItem();
                    break;
                case 5:
                    new UserView().payingCart();
                    break;
                case 6:
                    new UserView().formUpdateUser();
                    break;
                case 7:
                    new ProductView().searchByName();
                    break;
                case 8:
                    new ProductView().featuredProduct();
                    break;
                case 9:
                    new UserView().logOut();
                    userHomeMenu();
                    break;
                default:
                    System.out.println("\033[0;31m                                                                         Không hợp lệ. Vui lòng chọn lại.                                \033[0;0m");
                    break;
            }
        }
    }

}