package dao.impl;

import bean.Admin;
import bean.LoginJudge;
import dao.IAdminDao;
import tool.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements IAdminDao {


    /* 查询数据的方法 */
    @Override
    public Admin queryAdmin(String name, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Admin admin = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT * FROM admin WHERE name = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // 创建 Admin 对象并设置属性值
                admin = new Admin();
                admin.setName(rs.getString("name"));
                admin.setPassword(rs.getString("password"));
                admin.setAge(rs.getInt("age"));
                admin.setGender(rs.getInt("gender"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            DBUtils.close(rs, stmt, conn);
        }

        return admin; // 返回封装好数据的 Admin 对
    }

    @Override
    public LoginJudge loginVerification(String name, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT adminId, name, password FROM admin WHERE name = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            System.out.println("正在查询");
            if (rs.next()) {
                // 如果查询到匹配的用户名和密码，则创建一个 LoginJudge 对象
                int userId = rs.getInt("adminId");
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
}
