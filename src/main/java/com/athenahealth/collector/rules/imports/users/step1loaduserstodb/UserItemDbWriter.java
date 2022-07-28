package com.athenahealth.collector.rules.imports.users.step1loaduserstodb;

import com.athenahealth.collector.rules.imports.users.model.User;
import com.athenahealth.collector.rules.imports.users.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "UserItemDbWriter")
public class UserItemDbWriter implements ItemWriter<User> {
    Log logger = LogFactory.getLog(UserItemDbWriter.class);
    private UserRepository userRepository;

    @Autowired
    public UserItemDbWriter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void write(List<? extends User> users) throws Exception{
        logger.debug("Data Saved for Users: " + users);
        userRepository.saveAll(users);
    }
}
