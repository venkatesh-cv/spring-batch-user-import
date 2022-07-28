package com.athenahealth.collector.rules.imports.users.step2loadtitlestodb;

import com.athenahealth.collector.rules.imports.users.model.Title;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component(value = "TitleProcessor")
public class TitleProcessor implements ItemProcessor<Title, Title> {
    Log log = LogFactory.getLog(TitleProcessor.class);
    @Override
    public Title process(Title title) throws Exception {
        log.debug(title.toString());
        return title;
    }
}
