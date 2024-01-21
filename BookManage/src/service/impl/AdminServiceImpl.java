package service.impl;

import bean.Admin;
import bean.LoginJudge;
import dao.impl.AdminDaoImpl;
import service.IAdminService;

public class AdminServiceImpl implements IAdminService {
    private AdminDaoImpl adminDao =new AdminDaoImpl();
    @Override
    public Admin queryAdmin(String name, String password) {
        Admin judge= adminDao.queryAdmin(name,password);
        return judge;
    }

    @Override
    public LoginJudge loginVerification(String name, String password) {
        LoginJudge judge= adminDao.loginVerification(name,password);
        return judge;
    }
}
