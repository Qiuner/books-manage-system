package dao;

import bean.LoginJudge;
import bean.User;

public interface IUserDao {
    /* 查询单个用户 */
    LoginJudge loginVerification(String name, String password);

    Boolean addUser(User user);
}
