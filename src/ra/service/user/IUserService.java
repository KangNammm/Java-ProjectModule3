package ra.service.user;

import ra.model.User;
import ra.service.IGenericService;

public interface IUserService extends IGenericService<User> {
    boolean existedByUsername(String username);
    boolean existedByEmail(String email);
    boolean checkLogin(String username, String password);
    User getCurentUser();

    void logOut();
    User findById(int id);
    boolean changeUserStatus(int id);
    void paying(User userLogin);
}
