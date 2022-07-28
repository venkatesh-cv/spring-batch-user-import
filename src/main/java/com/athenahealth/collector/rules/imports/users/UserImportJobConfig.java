package com.athenahealth.collector.rules.imports.users;

import com.athenahealth.collector.rules.imports.users.model.Title;
import com.athenahealth.collector.rules.imports.users.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class UserImportJobConfig {
    @Bean(name = "UserImportJob")
    public Job SpringBatchConfig(JobBuilderFactory jobBuilderFactory,
                                   StepBuilderFactory stepBuilderFactory,
                                   @Qualifier("UserFileItemReader") ItemReader<User> userFileItemReader,
                                   @Qualifier("UserDepartmentProcessor") ItemProcessor<User, User> userDepartmentProcessor,
                                   @Qualifier("UserItemDbWriter") ItemWriter<User> userItemDbWriter,
                                   JdbcCursorItemReader<User> userDbCursorReader,
                                   @Qualifier("userNameProcessor") ItemProcessor<User, User> userNameProcessor,
                                   @Qualifier("userFlatFileWriter") ItemWriter<User> userFlatFileWriter,
                                 @Qualifier("TitlesFileItemReader") ItemReader<Title> titleItemReader,
                                 @Qualifier("TitleProcessor") ItemProcessor<Title, Title> titleItemProcessor,
                                 @Qualifier("TitleDbWriter") ItemWriter<Title> titleItemWriter
    ) {

        Step step = stepBuilderFactory.get("load.user.csv.to.db")
                .<User, User>chunk(10)
                .reader(userFileItemReader)
                .processor(userDepartmentProcessor)
                .writer(userItemDbWriter)
                .build();

        Step step2 = stepBuilderFactory.get("Load.Title.csv.to.db")
                .<Title, Title>chunk(10)
                .reader(titleItemReader)
                .processor(titleItemProcessor)
                .writer(titleItemWriter)
                .build();

        Step step3 = stepBuilderFactory.get("ETL-Read-User-and-Titles-From-DB-Write-To-Flatfile")
                .<User, User>chunk(10)
                .reader(userDbCursorReader)
                .processor(userNameProcessor)
                .writer(userFlatFileWriter)
                .build();

        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .next(step2)
                .next(step3)
                .build();
    }

}
