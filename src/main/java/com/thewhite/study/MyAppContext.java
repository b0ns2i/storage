package com.thewhite.study;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("application.properties")
public class MyAppContext {


    @Bean()
    @Scope("singleton")
    public WriterReaderFile getWRF(){
        return new WriterReaderFile();
    }
}
