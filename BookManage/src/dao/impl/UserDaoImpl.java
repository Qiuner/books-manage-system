package dao.impl;

import bean.LoginJudge;
import bean.User;
import dao.IUserDao;
import tool.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements IUserDao {
    @Override
    public LoginJudge loginVerification(String name, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT userId, name, password FROM User WHERE name = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            System.out.println("正在查询");
            if (rs.next()) {
                // 如果查询到匹配的用户名和密码，则创建一个 LoginJudge 对象
                int userId = rs.getInt("userId");
                String userName=rs.getString("name");
                boolean userJudge = true;
                return new LoginJudge(userId, userName,userJudge);
            } else {
                // 如果未查询到匹配的用户名和密码，则创建一个 LoginJudge 对象，但 judge 属性为 false
                return new LoginJudge(null,null,false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, stmt, conn);
        }
        return new LoginJudge(null, null,false);
    }

    @Override
    public Boolean addUser(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO User (name, password, age, gender) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getAge());
            stmt.setInt(4, user.getGender());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            DBUtils.close(stmt, conn);
        }
    }
}
