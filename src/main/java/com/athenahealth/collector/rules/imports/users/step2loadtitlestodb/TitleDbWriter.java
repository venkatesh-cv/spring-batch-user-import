package com.athenahealth.collector.rules.imports.users.step2loadtitlestodb;

import com.athenahealth.collector.rules.imports.users.model.Title;
import com.athenahealth.collector.rules.imports.users.repository.TitleRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import java.util.List;

@Component(value = "TitleDbWriter")
public class TitleDbWriter implements ItemWriter<Title> {

    Log log = LogFactory.getLog(TitleDbWriter.class);
    TitleRepository titleRepository;

    TitleDbWriter(TitleRepository titleRepository){
        this.titleRepository = titleRepository;
    }

    @Override
    public void write(List<? extends Title> list){
        log.debug(String.format("Writing to Db records %s", list.size()) );
        titleRepository.saveAll(list);
    }
}
