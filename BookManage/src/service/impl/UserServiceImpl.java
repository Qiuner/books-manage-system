package service.impl;

import bean.LoginJudge;
import bean.User;
import dao.impl.UserDaoImpl;
import service.IUserService;

public class UserServiceImpl implements IUserService {
    private UserDaoImpl empDaoImpl =new UserDaoImpl();
    @Override
    public LoginJudge loginVerification(String name, String password) {
        LoginJudge judge= empDaoImpl.loginVerification(name,password);
        return judge;
    }

    @Override
    public Boolean addUser(User user) {
        return empDaoImpl.addUser(user);
    }
}
