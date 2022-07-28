package com.athenahealth.collector.rules.imports.users.step3mergeuserandtitlestoflatfile;

import com.athenahealth.collector.rules.imports.users.model.User;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserDbCursorReader extends JdbcCursorItemReader<User> {

    private final String userSelectQuery = "select user.id, age, department, full_name, salary,title_id, name from user inner join title on user.title_id = title.id";
    UserDbCursorReader(DataSource primaryDataSource,UserDbRowMapper userDbRowMapper){
        super.setSql(userSelectQuery);
        super.setDataSource(primaryDataSource);
        super.setRowMapper(userDbRowMapper);
    }
}
