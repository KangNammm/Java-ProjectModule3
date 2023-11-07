package ra.view;

import ra.config.Config;
import ra.controller.UserController;
import ra.controller.product.ProductControllerIMPL;
import ra.dto.request.SignInDTO;
import ra.dto.request.SignUpDTO;
import ra.dto.response.ResponseMessage;
import ra.model.*;
import ra.service.user.IUserService;
import ra.service.user.UserServiceIMPL;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class UserView {
    UserController userController = new UserController();
    User userLogin = userController.getUserLogin();
    ProductControllerIMPL productcontroller = new ProductControllerIMPL();

    List<User> userList = userController.getListUser();
    IUserService userService = new UserServiceIMPL();
    public void formRegister() {

        int id ;
        if (userList.isEmpty()) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }

        boolean validateName;
        String name;
        while (true) {
//            System.out.println("Nhập tên bạn: ");
//            name = Config.scanner().nextLine();
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                                         Nhập tên của bạn:                                 ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.print("                                                                           ");  name = Config.scanner().nextLine();

            validateName = Pattern.matches("[A-Z][a-zA-Z[\\\\s]]{1,10}", name);
            if (validateName) {
                break;
            } else {
                System.out.println("\033[0;31mTên không đúng định dạng\033[0;0m");
            }
        }

        boolean validateUsername;
        String username;
        while (true) {
//            System.out.println("Nhập username của bạn: ");
////            username = Config.scanner().nextLine();
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                                         Nhập username của bạn:                                ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.print("                                                                           ");  username = Config.scanner().nextLine();

            validateUsername = Pattern.matches("^[a-zA-Z0-9]{1,40}$", username);
            if (validateUsername) {
                break;
            } else {
                System.out.println("\033[0;31mUsername không đúng định dạng\033[0;0m");
            }
        }

        boolean validateEmail;
        String email;
        while (true) {
//            System.out.println("Nhập email của bạn: ");
//            email = Config.scanner().nextLine();
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                                         Nhập email của bạn:                                ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.print("                                                                           ");  email = Config.scanner().nextLine();

            validateEmail = Pattern.matches("^(.+)@(.+)$", email);
            if (validateEmail) {
                break;
            } else {
                System.out.println("\033[0;31mEmail không đúng định dạng\033[0;0m");
            }
        }
        boolean validatePassword;
        String password;
        while (true) {
//            System.out.println("Nhập password của bạn: ");
//            password = Config.scanner().nextLine();
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                                         Nhập mật khẩu của bạn:                                ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.print("                                                                           ");  password = Config.scanner().nextLine();

            validatePassword = Pattern.matches("[a-zA-Z0-9]{1,40}", password);
            if (validatePassword) {
                break;
            } else {
                System.out.println("\033[0;31mPassword không đúng định dạng\033[0;0m");
            }
        }
//        System.out.println("Enter the role: ");
//        String role = Config.scanner().nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add("user");

        SignUpDTO sign = new SignUpDTO(id, name, username, email, password, strRole);
        while (true) {
            ResponseMessage responseMessage = userController.register(sign);
            if (responseMessage.getMessage().equals("user_existed")) {
                System.out.println("\033[0;31mTên người dùng đã tồn tại!\033[0;0m");
                username = Config.scanner().nextLine();
                sign.setUsername(username);
            } else if (responseMessage.getMessage().equals("email_existed")) {
                System.out.println("\033[0;31mEmail đã tồn tại!\033[0;0m");
                email = Config.scanner().nextLine();
                sign = new SignUpDTO(id, name, username, email, password, strRole);
            } else if (responseMessage.getMessage().equals("create_success")) {
                System.out.println("\033[0;33m                                                                   Tạo tài khoản thành công!\033[0;0m");
                break;
            }
        }
    }

    public void formLogin() {
        boolean validateUsername;
        String username;
        while (true) {
            System.out.println("\033[4;36mForm Login!\033[0m");
//            System.out.println("Nhập username: ");
//            username = Config.scanner().nextLine();
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                                         NHẬP TÊN ĐĂNG NHẬP                                 ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.print("                                                                           ");  username = Config.scanner().nextLine();
            validateUsername = Pattern.matches("^[a-zA-Z0-9]{1,40}$", username);
            if (validateUsername) {
                break;
            } else {
                System.out.println("\033[0;31m                                                                       Username không đúng định dạng                                 \033[0;0m");
                System.out.println("\033[0;31m                                                                            Vui lòng nhập lại                                \033[0;0m");
            }
        }

        boolean validatePassword;
        String password;
        while (true) {
//            System.out.println("Nhập mật khẩu của bạn: ");
//            password = Config.scanner().nextLine();
            System.out.println("                                                                         NHẬP MẬT KHẨU ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.print("                                                                            "); password = Config.scanner().nextLine();

            validatePassword = Pattern.matches("[a-zA-Z0-9]{1,40}", password);
            if (validatePassword) {
                break;
            } else {
                System.out.println("\033[0;31m                                                                       Password không đúng định dạng                                \033[0;0m");
                System.out.println("\033[0;31m                                                                            Vui lòng nhập lại                                \033[0;0m");
            }
        }

        SignInDTO signInDTO = new SignInDTO(username, password);
        while (true) {
            ResponseMessage responseMessage = userController.login(signInDTO);
            if (responseMessage.getMessage().equals("login_failed")) {
                System.out.println("\033[0;31m                                                        Đăng nhập thất bại! Vui lòng kiểm tra lại tài khoản hoặc mật khẩu!\033[0;0m");
                boolean accountExists = userController.checkAccountExists(username);
                if (!accountExists) {
                    System.out.println("\033[0;31m                                                                   Tài khoản không tồn tại!\033[0;0m");
                    break;
                } else {
                    // Yêu cầu người dùng nhập lại password
                    System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
                    System.out.println("                                                                                NHẬP MẬT KHẨU ");
                    System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
                    System.out.print("                                                                            "); password = Config.scanner().nextLine();

                }
//                System.out.println("Enter your username: ");
//                username = Config.scanner().nextLine();
//                System.out.println("Enter your password: ");
//                password = Config.scanner().nextLine();

                System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
                System.out.println("                                                                         NHẬP TÊN ĐĂNG NHẬP                                ");
                System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
                System.out.print("                                                                           ");  username = Config.scanner().nextLine();
                System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
                System.out.println("                                                                                NHẬP MẬT KHẨU ");
                System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
                System.out.print("                                                                            "); password = Config.scanner().nextLine();

                signInDTO.setUsername(username);
                signInDTO.setPassword(password);
            } else {
//                System.out.println("LOGIN SUCCESS!");
                System.out.println("\u001B[33m                                                                    Đăng nhập thành công!\u001B[0m");
                Navbar.userHomeMenu();
                break;
            }
        }
    }

    public void showListUser() {
        System.out.println("                                                                             \u001B[33mDanh sách người dùng");
        System.out.format("           +---------+-----------------+-----------------+------------------------+--------------+--------------+-------------------+---------------------+%n");
        System.out.format("           | User ID |       Name      |    Username     |        Email           |  Password    |    Avatar    |      Status       |        Role         |%n");
        System.out.format("           +---------+-----------------+-----------------+------------------------+--------------+--------------+-------------------+---------------------+%n");
        String leftAlignFormat = "           | %-7d | %-15s | %-15s | %-22s | %-12s | %-12s | %-17s | %-19s |%n";

        for (User list:userController.getListUser()
             ) {
            System.out.format(leftAlignFormat, list.getId(),list.getName(),list.getUsername(),list.getEmail(),list.getPassword(),list.getAvatar(),list.isStatus(),list.getRoles());
        }
    }

    public void logOut() {
        userController.logOut();
        new Navbar();
    }

    public void changeUserStatus() {
        showListUser();
        System.out.println("Nhập id: ");
        int id = Config.validateInt();
        User user = userController.findById(id);
        if (user == null) {
            System.out.println("\033[0;31mId không tồn tại!\033[0;0m");
        } else {
            userController.changeUserStatus(id);
            System.out.println("\033[0;33m                                                                Cập nhật trạng thái thành công.                                        \033[0;33m");
        }
    }

    public boolean checkCartItemExits(List<CartItem> cart,int idCartItem) {
        for (CartItem item : cart) {
            if (item.getId() == idCartItem){
                return true;
            }
        }
        return false;
    }

    public void showListCart(){
        new UserView().addToCart(2);
    }

    public void addToCart(int choice){
        User user = userController.findById(userLogin.getId());
        List<CartItem> listCart = user.getCart();
        new ProductView().showProduct();
        switch (choice){
            case 1:
                System.out.println("Nhập id sản phẩm:");
                int id = Config.validateInt();
                if(productcontroller.findById(id) == null){
                    System.out.println("\033[0;31mId không tồn tại!\033[0;0m");
                }else {
                    Product product =productcontroller.findById(id);
                    System.out.println("Nhập số lượng sản phẩm:");
                    int quantity;
                    while (true){
                        quantity = Config.validateInt();
                        if (quantity > product.getStock()){
                            System.out.println("\033[0;31mSố lượng vượt quá số lượng trong kho, vui lòng nhập lại!!!!\033[0;0m");
                        } else {
                            break;
                        }
                    }
                    int idNew = (listCart == null)? 1 : (listCart.get(listCart.size()-1).getId()+1);
                    CartItem newCartItem = new CartItem(idNew,product,quantity);
                    if (listCart == null){
                        // giò hàng trống
                        listCart = new ArrayList<>();
                        listCart.add(newCartItem);
                    }else {
                        if (checkCartItemExits(listCart,id)){
//                    sản phẩm trùng
                            for (CartItem item: listCart) {
                                if (item.getProduct().getProductId()==id){
                                    item.setQuantity(item.getQuantity()+quantity);
                                    break;
                                }
                            }
                        }else {
//                    sản phâm ko bị trùng
                            listCart.add(newCartItem);
                        }

                    }
                    System.out.println("\033[0;33mThêm vào giỏ thành công!!!\033[0;0m");
                    user.setCart(listCart);
                    userController.update(user);
                }
                break;
            case 2:
                System.out.println(userService.findById(userLogin.getId()));
                System.out.println("                                                                            \u001B[33mGiỏ Hàng Của Bạn");
                System.out.format("                                               +---------+-----------------+-----------------+-----------------+----------+%n");
                System.out.format("                                               |    ID   |  Tên danh mục   |   Tên Sản Phẩm  |   Giá tiền      | Số lượng |%n");
                System.out.format("                                               +---------+-----------------+-----------------+-----------------+----------+%n");
                String leftAlignFormat = "                                               | %-7d | %-15s | %-15s | %-15s |  %-6s  |%n";
                double total = 0;

                for (CartItem cartItem:user.getCart()){
                    System.out.format(leftAlignFormat,cartItem.getId(),cartItem.getProduct().getProductCatalog().getName(),cartItem.getProduct().getProductName(),Config.formatMoney.format(cartItem.getProduct().getProductPrice()),cartItem.getQuantity());
                    total += cartItem.getProduct().getProductPrice()*cartItem.getQuantity();
//            System.out.println(cartItem);
                }
                System.out.format("                                               +---------+-----------------+-----------------+-----------------+----------+%n");
                System.out.println("                                                                 \u001B[35mTổng giá trị đơn hàng của bạn là: \u001B[31m" + Config.formatMoney.format(total )+ "\u001B[0m");
                System.out.println();
                break;
        }

    }
    public void changeQuantityItem(){
        User user = userController.findById(userLogin.getId());
        List<CartItem> cart = user.getCart();
        new UserView().addToCart(2);
        System.out.println("Nhập id của sản phẩm cần thay đổi số lượng: ");
        int idCartItem = Config.validateInt();
        if (checkCartItemExits(cart,idCartItem)){
            for (CartItem item : cart) {
                if (item.getId()==idCartItem){
                    System.out.println("Nhập số lượng mới , số lượng cũ là: "+item.getQuantity());
                    int checkQuantity;
                    while (true){
                        Product product =productcontroller.findById(idCartItem);
                        checkQuantity = Config.validateInt();
                        if (checkQuantity > product.getStock()){
                            System.out.println("\033[0;31mSố lượng vượt quá số lượng trong kho, vui lòng nhập lại!!!!\033[0;0m");
                        } else {
                            break;
                        }
                    }
                    item.setQuantity(checkQuantity);
                    user.setCart(cart);
                    userController.update(user);
                    System.out.println("\033[0;33mCập nhật thành công!\033[0;0m");
                    break;
                }
            }
        }else {
            System.out.println("\033[0;31mId giỏ hàng không tồn tại!\033[0;0m");
        }
    }

    public void deleteCartItem(){
        User user = userController.findById(userLogin.getId());
        List<CartItem> cart = user.getCart();
        showListCart();
        System.out.println("Nhập id cart cần xóa: ");
        int idCartItem = Config.validateInt();
        if (checkCartItemExits(cart,idCartItem)) {
            for (CartItem item : cart) {
                if (item.getId()==idCartItem){
                    cart.remove(item);
                    user.setCart(cart);
                    userController.update(user);
                    System.out.println("\033[0;33m                                                            Xóa sản phẩm thành công.                                        \033[0;33m");
                    break;
                }
            }
        }else {
            System.out.println("\033[0;31mId không tồn tại\033[0;0m");
        }
    }

    public void searchByUsername(){
        System.out.println("Nhập tên username bạn muốn tìm kiếm:");
        String searchName= Config.scanner().nextLine();
//        boolean searchCheck = false;
        System.out.format("\u001B[33m           +---------+-----------------+-----------------+------------------------+--------------+--------------+-------------------+---------------------+%n\u001B[0m");
        System.out.format("\u001B[33m           | User ID |       Name      |    Username     |        Email           |  Password    |    Avatar    |      Status       |        Role         |%n\u001B[0m");
        System.out.format("\u001B[33m           +---------+-----------------+-----------------+------------------------+--------------+--------------+-------------------+---------------------+%n\u001B[0m");
        String leftAlignFormat = "\u001B[33m           | %-7d | %-15s | %-15s | %-22s | %-12s | %-12s | %-17s | %-19s |%n\u001B[0m";

        for (User p:userController.searchByName(searchName)) {
            System.out.format(leftAlignFormat, p.getId(),p.getName(),p.getUsername(),p.getEmail(),p.getPassword(),p.getAvatar(),p.isStatus(),p.getRoles());
        }
        System.out.format("\u001B[33m           +---------+-----------------+-----------------+------------------------+--------------+--------------+-------------------+---------------------+%n\u001B[0m");

    }

    public void formUpdateUser(){
        while (true) {
            System.out.print("Nhập mật khẩu cũ: ");
            String oldPassword = Config.scanner().nextLine();
            //Kiểm tra mật khẩu cũ
            if (!userLogin.getPassword().equals(oldPassword)) {
                System.out.println("\033[0;31mMật khẩu không khớp, vui lòng nhập lại!!!!\033[0;0m");
                continue;
            }
            System.out.print("Nhập mật khẩu mới: ");
            String newPassWord = Config.scanner().nextLine();
            for (User u : userList) {
                if (u.getId() == userLogin.getId()) {
                    u.setPassword(newPassWord);
                    userController.update(u);
                    break;
                }
            }
            System.out.println("\033[0;33mCập nhật mật khẩu thành công!!!\033[0;0m");
            break;
        }
        }

    public void payingCart(){
        userController.paying(userLogin);
    }


    public static void UserManager() {
        int choice = 0;
        while (choice != 4) {

            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("\u001B[33m                                                                          " + "Quản Lý Người Dùng" + "                                  \u001B[0m");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                1. Hiển thị danh sách người dùng                                                        ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                2. Thay đổi trạng thái người dùng                                                   ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                3. Tìm kiếm tài khoản theo tên đăng nhập                                                  ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                4. Thoát                                                  ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
           System.out.println("                                                 Lựa chọn của bạn là:                                                           ");

            choice = Config.validateInt();
            switch (choice) {
                case 1:
                    new ra.view.UserView().showListUser();
                    break;
                case 2:
                    new ra.view.UserView().changeUserStatus();
                    break;
                case 3:
                    new ra.view.UserView().searchByUsername();
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
}
