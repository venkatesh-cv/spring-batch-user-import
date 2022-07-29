package com.athenahealth.collector.rules.imports.users.step1loaduserstodb;

import com.athenahealth.collector.rules.imports.users.model.User;
import com.athenahealth.collector.rules.imports.base.StepBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("Step1LoadUserCsvToDb")
public class Step1LoadUserCsvToDb implements StepBuilder {
    Step loadUsersCsvToDb;
    public Step1LoadUserCsvToDb(StepBuilderFactory stepBuilderFactory,
        @Qualifier("UserFileItemReader") ItemReader<User> userFileItemReader,
        @Qualifier("UserDepartmentProcessor") ItemProcessor<User, User> userDepartmentProcessor,
        @Qualifier("UserItemDbWriter") ItemWriter<User> userItemDbWriter
    ) {
                this.loadUsersCsvToDb =  stepBuilderFactory.get("load.user.csv.to.db")
                .<User, User>chunk(10)
                .reader(userFileItemReader)
                .processor(userDepartmentProcessor)
                .writer(userItemDbWriter)
                .build();



    }

    @Override
    public Step getStep() {
        return loadUsersCsvToDb;
    }
}
