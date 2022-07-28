package com.athenahealth.collector.rules.imports.users.step3mergeuserandtitlestoflatfile;

import com.athenahealth.collector.rules.imports.users.model.User;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class UserFlatFileWriter extends FlatFileItemWriter<User> {
    UserFlatFileWriter() {
        super.setName("UsersWithTitle.csv");
        super.setResource(new FileSystemResource("src/main/resources/usersWithTitle.csv"));
        super.setShouldDeleteIfEmpty(true);
        super.setShouldDeleteIfExists(true);
        super.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("Id,Full Name, Age, Department, Salary, Title");
            }
        });
        super.setLineAggregator(new PassThroughLineAggregator<>());
    }
}
