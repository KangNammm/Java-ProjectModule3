package ra.controller;



import ra.dto.request.SignInDTO;
import ra.dto.request.SignUpDTO;
import ra.dto.response.ResponseMessage;
import ra.model.Role;
import ra.model.RoleName;
import ra.model.User;
import ra.service.role.IRoleService;
import ra.service.role.RoleServiceIMPL;
import ra.service.user.IUserService;
import ra.service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    private IUserService userService = new UserServiceIMPL();
    private IRoleService roleService = new RoleServiceIMPL();

    public ResponseMessage register(SignUpDTO sign) {
        if (userService.existedByUsername(sign.getUsername())) {
            return new ResponseMessage("user_existed");
        }
        if (userService.existedByEmail(sign.getEmail())) {
            return new ResponseMessage("email_existed");
        }
        Set<String> strRole = sign.getStrRole();
        Set<Role> roleSet = new HashSet<>();
        strRole.forEach(role -> {
            switch (role) {
                case "admin":
                    roleSet.add(roleService.findByName(RoleName.ADMIN));
                    break;
                case "pm":
                    roleSet.add(roleService.findByName(RoleName.PM));
                    break;
                default:
                    roleSet.add(roleService.findByName(RoleName.USER));
            }
        });
        User user = new User(sign.getId(), sign.getName(), sign.getUsername(), sign.getEmail(), sign.getPassword(), roleSet);
        userService.save(user);
        return new ResponseMessage("create_success");
    }

    public List<User> getListUser() {
        return userService.findAll();
    }

    public ResponseMessage login(SignInDTO signInDTO){
        if(userService.checkLogin(signInDTO.getUsername(), signInDTO.getPassword())){
            return new ResponseMessage("login_success");
        } else {
            return new ResponseMessage("login_failed");
        }
    }
    public User getUserLogin() {
        return userService.getCurentUser();
    }

    public void logOut(){
        userService.logOut();
    }

    public User findById(int id){
        return userService.findById(id);
    }

    public void changeUserStatus(int id){
        userService.changeUserStatus(id);
    }

    public void update(User user){
        userService.save(user);
    }

    public List<User> searchByName(String searchName) {
        return userService.searchByName(searchName);
    }
    public void paying(User userLogin){
        userService.paying(userLogin);
    }

    public boolean checkAccountExists(String username) {
        // Lấy danh sách tất cả các tài khoản
        UserController userController = new UserController();
        List<User> userList = userController.getListUser();

        // Kiểm tra xem tài khoản có tồn tại trong danh sách không
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }
}




