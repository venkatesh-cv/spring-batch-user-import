package com.athenahealth.collector.rules.imports.users;

import com.athenahealth.collector.rules.imports.base.StepBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class UserImportJobConfig {
    @Bean(name = "UserImportJob")
    public Job SpringBatchConfig(JobBuilderFactory jobBuilderFactory,
                                 @Qualifier("Step1LoadUserCsvToDb") StepBuilder step1LoadUserCsvToDb,
                                   @Qualifier("Step2LoadTitlesCsvToDb")  StepBuilder step2LoadTitlesCsvToDb,
                                 @Qualifier("Step3MergeUserAndTitleToCsv") StepBuilder step3MergeUserAndTitleToCsv
    ) {

        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step1LoadUserCsvToDb.getStep())
                .next(step2LoadTitlesCsvToDb.getStep())
                .next(step3MergeUserAndTitleToCsv.getStep())
                .build();
    }




}
