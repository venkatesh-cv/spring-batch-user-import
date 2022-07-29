package com.athenahealth.collector.rules.imports.users.step2loadtitlestodb;

import com.athenahealth.collector.rules.imports.base.StepBuilder;
import com.athenahealth.collector.rules.imports.users.model.Title;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "Step2LoadTitlesCsvToDb")
public class Step2LoadTitlesCsvToDb implements StepBuilder {
    Step loadTitlesCsvToDb;
    public Step2LoadTitlesCsvToDb(StepBuilderFactory stepBuilderFactory,
                                     @Qualifier("TitlesFileItemReader") ItemReader<Title> titleItemReader,
                                     @Qualifier("TitleProcessor") ItemProcessor<Title, Title> titleItemProcessor,
                                     @Qualifier("TitleDbWriter") ItemWriter<Title> titleItemWriter
    ){
        loadTitlesCsvToDb = stepBuilderFactory.get("Load.Title.csv.to.db")
                .<Title, Title>chunk(10)
                .reader(titleItemReader)
                .processor(titleItemProcessor)
                .writer(titleItemWriter)
                .build();
    }

    @Override
    public Step getStep(){
        return loadTitlesCsvToDb;
    }
}
