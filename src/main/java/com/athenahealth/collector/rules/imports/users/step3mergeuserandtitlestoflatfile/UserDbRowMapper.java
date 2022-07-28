package com.athenahealth.collector.rules.imports.users.step3mergeuserandtitlestoflatfile;

import com.athenahealth.collector.rules.imports.users.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDbRowMapper  implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user  = new User();
        user.setId(rs.getInt("id"));
        user.setFullName(rs.getString("full_name"));
        user.setAge(rs.getInt("age"));
        user.setDept(rs.getString("department"));
        user.setSalary(rs.getInt("salary"));
        user.setTitleId(rs.getInt("title_id"));
        user.setTitle(rs.getString("name"));
        return user;
    }
}
