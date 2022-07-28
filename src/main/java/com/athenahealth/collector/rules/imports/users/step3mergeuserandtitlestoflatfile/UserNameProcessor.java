package com.athenahealth.collector.rules.imports.users.step3mergeuserandtitlestoflatfile;

import com.athenahealth.collector.rules.imports.users.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserNameProcessor implements ItemProcessor<User, User> {
    @Override
    public User process(User user) throws Exception {
        String[] names = user.getFullName().split(" ");
        user.setName(names[0]);
        user.setLastName(names[1]);
        return user;
    }
}
