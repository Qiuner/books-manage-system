package service;

import bean.Admin;
import bean.LoginJudge;

public interface IAdminService {
    Admin queryAdmin(String name, String password);
    /* 登录校验 */
    LoginJudge loginVerification(String name, String password);

}
