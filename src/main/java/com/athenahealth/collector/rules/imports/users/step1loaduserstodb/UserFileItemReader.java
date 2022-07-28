package com.athenahealth.collector.rules.imports.users.step1loaduserstodb;

import com.athenahealth.collector.rules.imports.users.model.User;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component(value="UserFileItemReader")
public class UserFileItemReader extends FlatFileItemReader<User> {

    private final String[] csvColumnNames = {"id", "name","lastName", "dept", "salary", "age","titleId"};

    UserFileItemReader(){
        super.setResource(new FileSystemResource("src/main/resources/users.csv"));
        super.setName("users.csv-Reader");
        super.setLinesToSkip(1);
        super.setLineMapper(csvlineMapper());
    }

    private LineMapper<User> csvlineMapper() {

        DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(getcsvLineTokenizer());
        defaultLineMapper.setFieldSetMapper(initUserBeanWrapperFieldSetMapper());
        return defaultLineMapper;
    }

    private BeanWrapperFieldSetMapper<User> initUserBeanWrapperFieldSetMapper() {
        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);
        return fieldSetMapper;
    }

    private DelimitedLineTokenizer getcsvLineTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(csvColumnNames);
        return lineTokenizer;
    }
}
