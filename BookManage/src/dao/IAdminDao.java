package dao;

import bean.Admin;
import bean.LoginJudge;

public interface IAdminDao {
    /* 查询单个管理员 */
    Admin queryAdmin(String name, String password);
    /* 登录校验 */
    LoginJudge loginVerification(String name, String password);
}
