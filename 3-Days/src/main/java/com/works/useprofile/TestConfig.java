package com.works.useprofile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
@PropertySource("classpath:application.properties")
public class TestConfig implements IConfig {

    @Value("${data.apiKey}")
    private String apiKey;

    @Override
    public Config call() {
        Config c = new Config();
        c.setApiKey(apiKey);
        c.setIp("192.121.12.12");
        c.setRowCount(15);
        return c;
    }

}
