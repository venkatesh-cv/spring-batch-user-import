package com.athenahealth.collector.rules.imports.users.step1loaduserstodb;

import com.athenahealth.collector.rules.imports.users.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.event.Level;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Component(value = "UserDepartmentProcessor")
public class UserDepartmentProcessor implements ItemProcessor<User, User> {
    private final Log logger = LogFactory.getLog(UserDepartmentProcessor.class);
    private static final Map<String, String> DEPT_NAMES =
            new HashMap<>();

    public UserDepartmentProcessor() {
        DEPT_NAMES.put("1", "Technology");
        DEPT_NAMES.put("2", "Operations");
        DEPT_NAMES.put("3", "Accounts");
    }

    @Override
    public User process(User user) throws Exception {
        String deptCode = user.getDept();
        String dept = DEPT_NAMES.get(deptCode);
        user.setDept(dept);
        user.setTime(new Date());
        logger.debug(String.format("Converted from [%s] to [%s]", deptCode, dept));
        user.setFullName(user.getName() + " " + user.getLastName());
        return user;
    }
}
