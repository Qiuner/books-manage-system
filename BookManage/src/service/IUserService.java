package service;

import bean.LoginJudge;
import bean.User;

public interface IUserService {
    /* 登录校验 */
    LoginJudge loginVerification(String name, String password);
    /* 增加用户 */
    Boolean addUser(User user);
}
