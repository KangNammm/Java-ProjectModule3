package ra.view;



import ra.controller.UserController;
import ra.model.Role;
import ra.model.RoleName;
import ra.model.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ra.config.Config.scanner;

public class ProfileView {
    UserController userController = new UserController();
    public ProfileView() {
        User user = userController.getUserLogin();
        if(user!=null){
            Navbar navbar = new Navbar();
            Set<Role> roleSet = user.getRoles();
            List<Role> roles = new ArrayList<>(roleSet);
            if(roles.get(0).getName()== RoleName.ADMIN){
                System.out.println("\033[1;92mPHẦN DÀNH CHO ADMIN\033[0m");
                Navbar.generalManager();
            } else if(roles.get(0).getName()==RoleName.USER){
                System.out.println("\033[1;92mPHẦN DÀNH CHO USER\033[0;0m");
                Navbar.cartView();
            }
        }
    }
}

