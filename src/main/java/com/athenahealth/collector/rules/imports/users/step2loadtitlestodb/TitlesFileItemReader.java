package com.athenahealth.collector.rules.imports.users.step2loadtitlestodb;

import com.athenahealth.collector.rules.imports.users.model.Title;
import com.athenahealth.collector.rules.imports.users.model.User;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component(value = "TitlesFileItemReader")
public class TitlesFileItemReader extends FlatFileItemReader<Title> {
    private final String[] csvColumnNames = {"id", "name"};

    TitlesFileItemReader(){
        super.setResource(new FileSystemResource("src/main/resources/titles.csv"));
        super.setName("Title.csv-Reader");
        super.setLinesToSkip(1);
        super.setLineMapper(csvlineMapper());
    }

    private LineMapper<Title> csvlineMapper() {

        DefaultLineMapper<Title> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(getcsvLineTokenizer());
        defaultLineMapper.setFieldSetMapper(initUserBeanWrapperFieldSetMapper());
        return defaultLineMapper;
    }

    private BeanWrapperFieldSetMapper<Title> initUserBeanWrapperFieldSetMapper() {
        BeanWrapperFieldSetMapper<Title> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Title.class);
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
