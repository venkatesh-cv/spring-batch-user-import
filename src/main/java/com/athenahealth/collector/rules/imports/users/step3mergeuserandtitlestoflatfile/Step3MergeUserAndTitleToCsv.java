package com.athenahealth.collector.rules.imports.users.step3mergeuserandtitlestoflatfile;

import com.athenahealth.collector.rules.imports.users.model.User;
import com.athenahealth.collector.rules.imports.base.StepBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "Step3MergeUserAndTitleToCsv")
public class Step3MergeUserAndTitleToCsv implements StepBuilder {
    Step mergeUserAndTitleToCsv;
    public Step3MergeUserAndTitleToCsv(
            StepBuilderFactory stepBuilderFactory,
            JdbcCursorItemReader<User> userDbCursorReader,
            @Qualifier("userNameProcessor") ItemProcessor<User, User> userNameProcessor,
            @Qualifier("userFlatFileWriter") ItemWriter<User> userFlatFileWriter
    ) {
        mergeUserAndTitleToCsv = stepBuilderFactory.get("ETL-Read-User-and-Titles-From-DB-Write-To-Flatfile")
                .<User, User>chunk(10)
                .reader(userDbCursorReader)
                .processor(userNameProcessor)
                .writer(userFlatFileWriter)
                .build();
    }

    @Override
    public Step getStep() {
        return mergeUserAndTitleToCsv;
    }
}
