package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Role;

public class RoleDB {
        public List<Role> getAll(String owner) throws Exception {
        List<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM role WHERE role_name=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, owner);
            rs = ps.executeQuery();
            while (rs.next()) {
                int roleID = rs.getInt(1);
                String roleName = rs.getString(2);
                Role role = new Role(roleID, roleName);
                roles.add(role);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return roles;
    }
}
